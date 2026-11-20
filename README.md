# EMI Corrections

<p align="center">
  <img src="icon.png" alt="EMI Corrections" width="160" height="160">
</p>

Mod **Forge 1.20.1** (somente cliente) que corrige incompatibilidades entre a camada
**JEMI** do EMI (1.1.24) e a API do **JEI 15.x** (testado com 15.74.0.228).

## Visão geral

Mods como o **Chipped 3.0.7** chamam métodos novos do JEI (ex.:
`IRecipeSlotBuilder.setPosition(x, y, w, h, HorizontalAlignment, VerticalAlignment)`).
O EMI exibe receitas de plugins JEI via JEMI, mas as classes dele não implementam esses
métodos. Cada receita quebra com:

```
[EMI/]: Exception thrown adding adding JEI recipe
java.lang.AbstractMethodError: Receiver class dev.emi.emi.jemi.impl.JemiRecipeSlotBuilder
does not define or inherit an implementation of ... setPosition(...)
```

Este mod adiciona as implementações faltantes via Mixin, sem alterar o EMI em disco.

## Estrutura

```
src/main/java/com/toothifruti/emicorrections/
├── EmiCorrections.java              # Entrada @Mod (Forge)
├── EmiCorrectionsMixinPlugin.java   # Aplica mixins só se a classe-alvo do EMI existir
├── mixin/jemi/                      # Mixins nas classes JEMI
└── util/Placement.java              # Cálculo de alinhamento (igual ao JEI)

src/main/resources/
├── logo.png                         # Ícone do mod (lista de mods do Forge)
├── emicorrections.mixins.json       # Lista de mixins + plugin
├── META-INF/mods.toml               # Metadados do mod
└── pack.mcmeta

icon.png                             # Mesmo ícone (GitHub / README)
libs/                                # Jars EMI/JEI só para compilar (não versionados)
```

## Métodos adicionados (Mixin)

| Classe do EMI | Métodos |
|---|---|
| `JemiRecipeSlotBuilder` | `setPosition(6 args)`, `setHoverPadding` (no-op), `setFluidRenderer(..., TilingDirection)` |
| `JemiPlaceable` | `setPosition(6 args)` |
| `JemiTextWidget` | `setPosition` (2 e 6 args) → `ITextWidget`, `setTooltip(...)` (no-op) |
| `JemiScrollGridWidget` | `setPosition(6 args)`, `setScrollbarVisibility` (no-op), `setSlots` |
| `JemiScrollBoxWidget` | `setScrollbarVisibility` (no-op) |

Alinhamento: `x = availableX + alignment.getXPos(availableWidth, width)` (mesma conta do JEI).

Todos usam `@Unique`: se uma versão futura do EMI implementar o método, o Mixin descarta a
nossa versão. O plugin pula tudo se o EMI não estiver instalado.

### Ainda não coberto (não afetava o Chipped)

`JemiTooltipBuilder`, `JemiRecipeLayoutBuilder.createFocusLink`, vários
`JemiRecipeExtrasBuilder.add*`, `JemiRecipeSlot(Drawable).getTagKey` / ingredientes.
Se aparecer um `AbstractMethodError` desses no log, o padrão de mixin é o mesmo.

## Pré-requisitos

- **JDK 17** (o Gradle pode baixar via toolchain Foojay se necessário)
- Forge MDK / Gradle Wrapper (já incluídos: `./gradlew`)
- Jars do EMI e do JEI em `libs/` — ver [libs/README.md](libs/README.md)

## Compilar (produção)

```bash
# 1. Coloque os jars em libs/ (nomes em gradle.properties: emi_jar, jei_jar)
# 2. Build
./gradlew build
```

Artefato: `build/libs/emicorrections-1.0.0.jar`

Copie o jar para a pasta `mods` da instância Forge 1.20.1 (cliente). O mod é
`displayTest="IGNORE_ALL_VERSION"` — o servidor não precisa tê-lo.

## Desenvolvimento

```bash
./gradlew runClient   # cliente com mixins carregados
./gradlew clean build
```

Versões e nomes dos jars: edite `gradle.properties` (`minecraft_version`, `forge_version`,
`mod_version`, `emi_jar`, `jei_jar`). Não há variáveis de ambiente sensíveis — este mod não
usa API keys nem `.env`.

## Verificar se funcionou

No `latest.log` da instância, procure:

```
[EMI Corrections/]: Correção aplicada em dev.emi.emi.jemi.impl.JemiRecipeSlotBuilder
```

e confira se as linhas `Exception thrown adding adding JEI recipe` sumiram.

## Licença

O metadado do mod declara **MIT** (`mod_license` em `gradle.properties` / `mods.toml`).
O arquivo `LICENSE.txt` na raiz ainda é o texto do Forge MDK (LGPL) — ver pendência de alinhamento no repositório.
