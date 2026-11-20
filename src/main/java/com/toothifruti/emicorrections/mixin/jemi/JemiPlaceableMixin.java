package com.toothifruti.emicorrections.mixin.jemi;

import com.toothifruti.emicorrections.util.Placement;

import dev.emi.emi.jemi.impl.extras.JemiPlaceable;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.IPlaceable;
import mezz.jei.api.gui.placement.VerticalAlignment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * Base dos widgets "extras" do JEMI (ex.: {@code JemiWidgetBuilder}, {@code JemiTextWidget}).
 * Falta a sobrecarga de {@code setPosition} com alinhamento de {@link IPlaceable}.
 */
@Mixin(value = JemiPlaceable.class, remap = false)
public abstract class JemiPlaceableMixin {

    @Unique
    public IPlaceable<?> setPosition(int availableX, int availableY, int availableWidth, int availableHeight,
                                     HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        JemiPlaceable<?> self = (JemiPlaceable<?>) (Object) this;
        self.x = Placement.x(availableX, availableWidth, self.getWidth(), horizontalAlignment);
        self.y = Placement.y(availableY, availableHeight, self.getHeight(), verticalAlignment);
        return (IPlaceable<?>) (Object) this;
    }
}
