package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprBeeFlower extends SimplePropertyExpression<LivingEntity, Location> {

    static {
        if (Skript.classExists("org.bukkit.entity.Bee")) {
            register(ExprBeeFlower.class, Location.class, "flower [location]", "livingentities");
        }
    }

    @Override
    @Nullable
    public Location convert(final LivingEntity e) {
        if (!(e instanceof Bee)) return null;
        return ((Bee)e).getFlower();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Location.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Bee)entity).setFlower(null);
            }
        } else {
            final Location loc = (Location) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Bee)entity).setFlower(loc);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "flower";
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

}
