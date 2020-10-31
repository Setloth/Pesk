package me.bluboy.addon.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.entity.TropicalFish;
import org.jetbrains.annotations.Nullable;

public class ExprBeaconTier extends SimplePropertyExpression<Block, Number> {

    static {
        register(ExprBeaconTier.class, Number.class, "tier", "blocks");
    }

    @Override
    @Nullable
    public Number convert(final Block b) {
        if (!(b.getState() instanceof Beacon)) return null;
        return ((Beacon)b.getState()).getTier();
    }

    @Override
    protected String getPropertyName() {
        return "tier";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

}
