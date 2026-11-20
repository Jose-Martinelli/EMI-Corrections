# Dependências de compilação

Coloque aqui os jars do EMI e do JEI usados só em `compileOnly`.
Eles **não** entram no jar do mod e **não** devem ser commitados (ver `.gitignore`).

Nomes esperados (definidos em `gradle.properties`):

| Arquivo | Origem típica |
|---|---|
| `emi-1.1.24+1.20.1+forge.jar` | pasta `mods` da instância Forge 1.20.1 |
| `jei-1.20.1-forge-15.74.0.228.jar` | pasta `mods` da instância Forge 1.20.1 |

Se os nomes dos jars forem outros, atualize `emi_jar` e `jei_jar` em `gradle.properties`.
