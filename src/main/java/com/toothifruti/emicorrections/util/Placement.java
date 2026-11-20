package com.toothifruti.emicorrections.util;

import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;

/**
 * Mesma conta que o JEI faz em
 * {@code IPlaceable#setPosition(int, int, int, int, HorizontalAlignment, VerticalAlignment)}:
 * posiciona um elemento de tamanho {@code size} dentro de uma área disponível.
 */
public final class Placement {
    private Placement() {
    }

    public static int x(int availableX, int availableWidth, int width, HorizontalAlignment alignment) {
        return availableX + alignment.getXPos(availableWidth, width);
    }

    public static int y(int availableY, int availableHeight, int height, VerticalAlignment alignment) {
        return availableY + alignment.getYPos(availableHeight, height);
    }
}
