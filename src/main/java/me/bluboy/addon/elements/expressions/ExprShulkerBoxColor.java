package me.bluboy.addon.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.SkriptColor;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.TropicalFish;
import org.jetbrains.annotations.Nullable;

public class ExprShulkerBoxColor extends SimplePropertyExpression<Block, SkriptColor> {

    static {
        register(ExprShulkerBoxColor.class, SkriptColor.class, "box colo[u]r", "blocks");
    }

    @Override
    @Nullable
    public SkriptColor convert(final Block b) {
        if (!(b.getState() instanceof ShulkerBox)) return null;
        return SkriptColor.fromDyeColor(((ShulkerBox)b.getState()).getColor());
    }

    @Override
    protected String getPropertyName() {
        return "box color";
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
    }

}
