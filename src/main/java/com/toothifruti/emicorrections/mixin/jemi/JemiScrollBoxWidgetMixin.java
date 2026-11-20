package com.toothifruti.emicorrections.mixin.jemi;

import dev.emi.emi.jemi.impl.extras.JemiScrollBoxWidget;
import mezz.jei.api.gui.widgets.IScrollBoxWidget;
import mezz.jei.api.gui.widgets.ScrollbarVisibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = JemiScrollBoxWidget.class, remap = false)
public abstract class JemiScrollBoxWidgetMixin {

    /** O EMI desenha a barra de rolagem do jeito dele; a preferência é ignorada. */
    @Unique
    public IScrollBoxWidget setScrollbarVisibility(ScrollbarVisibility visibility) {
        return (IScrollBoxWidget) (Object) this;
    }
}
