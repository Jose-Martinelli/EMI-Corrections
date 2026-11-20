package com.toothifruti.emicorrections.mixin.jemi;

import java.util.List;

import com.toothifruti.emicorrections.util.Placement;

import dev.emi.emi.jemi.impl.extras.JemiScrollGridWidget;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IScrollGridWidget;
import mezz.jei.api.gui.widgets.ScrollbarVisibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * Usado pelo Chipped ({@code addScrollGridWidget(...).setPosition(..., alinhamento)}),
 * logo depois do slot builder.
 */
@Mixin(value = JemiScrollGridWidget.class, remap = false)
public abstract class JemiScrollGridWidgetMixin {

    @Shadow
    public List<IRecipeSlotDrawable> slots;

    @Unique
    public IScrollGridWidget setPosition(int availableX, int availableY, int availableWidth, int availableHeight,
                                         HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        JemiScrollGridWidget self = (JemiScrollGridWidget) (Object) this;
        return self.setPosition(
                Placement.x(availableX, availableWidth, self.getWidth(), horizontalAlignment),
                Placement.y(availableY, availableHeight, self.getHeight(), verticalAlignment));
    }

    /** O EMI desenha a barra de rolagem do jeito dele; a preferência é ignorada. */
    @Unique
    public IScrollGridWidget setScrollbarVisibility(ScrollbarVisibility visibility) {
        return (IScrollGridWidget) (Object) this;
    }

    @Unique
    public IScrollGridWidget setSlots(List<IRecipeSlotDrawable> newSlots) {
        this.slots = newSlots;
        return (IScrollGridWidget) (Object) this;
    }
}
