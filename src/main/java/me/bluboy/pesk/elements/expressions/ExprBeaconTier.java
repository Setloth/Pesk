package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;

public class ExprBeaconTier extends SimplePropertyExpression<Block, Number> {

    static {
        if (Skript.classExists("org.bukkit.block.Beacon")) {
            register(ExprBeaconTier.class, Number.class, "tier", "blocks");
        }
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
