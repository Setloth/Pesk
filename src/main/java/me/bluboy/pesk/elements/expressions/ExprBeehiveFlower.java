package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Location;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprBeehiveFlower extends SimplePropertyExpression<Block, Location> {

    static {
        if (Skript.classExists("org.bukkit.block.Beehive")) {
            register(ExprBeehiveFlower.class, Location.class, "flower [location]", "blocks");
        }
    }

    @Override
    @Nullable
    public Location convert(final Block b) {
        if (!(b.getState() instanceof Beehive)) return null;

        return ((Beehive)b.getState()).getFlower();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Number.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final Block block : getExpr().getArray(e)) {
                ((Beehive)block).setFlower(null);
            }
        } else {
            final Location loc = (Location) delta[0];
            for (final Block block : getExpr().getArray(e)) {
                ((Beehive)block).setFlower(loc);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "flower location";
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

}
