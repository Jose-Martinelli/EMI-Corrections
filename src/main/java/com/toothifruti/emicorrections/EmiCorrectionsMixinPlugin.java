package com.toothifruti.emicorrections;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.service.MixinService;

/**
 * Só aplica um mixin se a classe-alvo do EMI existir. Assim o mod pode ficar
 * instalado mesmo sem EMI (ou num servidor) sem gerar erros.
 *
 * Os métodos adicionados usam {@code @Unique}: se uma versão futura do EMI já
 * implementar algum deles, o Mixin descarta a nossa versão e mantém a do EMI.
 */
public class EmiCorrectionsMixinPlugin implements IMixinConfigPlugin {
    private static final Logger LOGGER = LoggerFactory.getLogger("EMI Corrections");

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        try {
            MixinService.getService().getBytecodeProvider().getClassNode(targetClassName);
            return true;
        } catch (Throwable t) {
            LOGGER.info("Classe {} não encontrada (EMI ausente?); pulando {}", targetClassName, mixinClassName);
            return false;
        }
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        LOGGER.info("Correção aplicada em {}", targetClassName);
    }
}
