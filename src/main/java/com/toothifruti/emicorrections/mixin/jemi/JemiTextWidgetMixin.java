package com.toothifruti.emicorrections.mixin.jemi;

import java.util.Collection;

import com.toothifruti.emicorrections.util.Placement;

import dev.emi.emi.jemi.impl.extras.JemiPlaceable;
import dev.emi.emi.jemi.impl.extras.JemiTextWidget;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeWidgetTooltipCallback;
import mezz.jei.api.gui.widgets.ITextWidget;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * {@link ITextWidget} redeclara {@code setPosition} retornando {@code ITextWidget} e ganhou
 * {@code setTooltip(...)}; o EMI 1.1.24 não implementa nenhum deles.
 * Os tooltips são aceitos sem efeito (o widget continua aparecendo, só sem tooltip).
 */
@Mixin(value = JemiTextWidget.class, remap = false)
public abstract class JemiTextWidgetMixin {

    @Unique
    public ITextWidget setPosition(int x, int y) {
        JemiPlaceable<?> self = (JemiPlaceable<?>) (Object) this;
        self.x = x;
        self.y = y;
        return (ITextWidget) (Object) this;
    }

    @Unique
    public ITextWidget setPosition(int availableX, int availableY, int availableWidth, int availableHeight,
                                   HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        JemiPlaceable<?> self = (JemiPlaceable<?>) (Object) this;
        self.x = Placement.x(availableX, availableWidth, self.getWidth(), horizontalAlignment);
        self.y = Placement.y(availableY, availableHeight, self.getHeight(), verticalAlignment);
        return (ITextWidget) (Object) this;
    }

    @Unique
    public ITextWidget setTooltip(FormattedText tooltip) {
        return (ITextWidget) (Object) this;
    }

    @Unique
    public ITextWidget setTooltip(Collection<? extends FormattedText> tooltip) {
        return (ITextWidget) (Object) this;
    }

    @Unique
    public ITextWidget setTooltip(TooltipComponent tooltip) {
        return (ITextWidget) (Object) this;
    }

    @Unique
    public ITextWidget setTooltip(IRecipeWidgetTooltipCallback callback) {
        return (ITextWidget) (Object) this;
    }
}
