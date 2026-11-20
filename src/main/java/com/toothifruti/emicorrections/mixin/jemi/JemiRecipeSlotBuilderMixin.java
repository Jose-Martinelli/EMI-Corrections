package com.toothifruti.emicorrections.mixin.jemi;

import com.toothifruti.emicorrections.util.Placement;

import dev.emi.emi.jemi.impl.JemiRecipeSlotBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.TilingDirection;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * Corrige o erro do log:
 * <pre>
 * AbstractMethodError: Receiver class dev.emi.emi.jemi.impl.JemiRecipeSlotBuilder does not define
 * or inherit an implementation of ... setPosition(int, int, int, int, HorizontalAlignment, VerticalAlignment)
 *   at earth.terrarium.chipped.common.compat.jei.ChippedRecipeCategory.setRecipe
 * </pre>
 * O JEI 15.x adicionou esses métodos em {@link IRecipeSlotBuilder}, mas o EMI 1.1.24 não os implementa.
 */
@Mixin(value = JemiRecipeSlotBuilder.class, remap = false)
public abstract class JemiRecipeSlotBuilderMixin {

    @Unique
    public IRecipeSlotBuilder setPosition(int availableX, int availableY, int availableWidth, int availableHeight,
                                          HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        JemiRecipeSlotBuilder self = (JemiRecipeSlotBuilder) (Object) this;
        return self.setPosition(
                Placement.x(availableX, availableWidth, self.getWidth(), horizontalAlignment),
                Placement.y(availableY, availableHeight, self.getHeight(), verticalAlignment));
    }

    /** O EMI não tem "hover padding"; aceitamos a chamada sem efeito em vez de quebrar a receita. */
    @Unique
    public IRecipeSlotBuilder setHoverPadding(int top, int right, int bottom, int left) {
        return (IRecipeSlotBuilder) (Object) this;
    }

    /** Ignora a direção de preenchimento e usa o renderizador de tanque que o EMI já tem. */
    @Unique
    public IRecipeSlotBuilder setFluidRenderer(long capacity, boolean showCapacity, int width, int height,
                                               TilingDirection tilingDirection) {
        JemiRecipeSlotBuilder self = (JemiRecipeSlotBuilder) (Object) this;
        return self.setFluidRenderer(capacity, showCapacity, width, height);
    }
}
