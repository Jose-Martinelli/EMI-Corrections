package com.toothifruti.emicorrections;

import net.minecraftforge.fml.common.Mod;

/**
 * Mod de correções de compatibilidade para o EMI (camada JEMI) com versões
 * mais novas da API do JEI. Todo o trabalho é feito pelos mixins em
 * {@code com.toothifruti.emicorrections.mixin}; esta classe só existe para o
 * Forge reconhecer o mod.
 */
@Mod(EmiCorrections.MOD_ID)
public final class EmiCorrections {
    public static final String MOD_ID = "emicorrections";

    public EmiCorrections() {
    }
}
