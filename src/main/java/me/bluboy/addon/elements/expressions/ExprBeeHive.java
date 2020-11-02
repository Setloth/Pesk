package me.bluboy.addon.elements.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprBeeHive extends SimplePropertyExpression<LivingEntity, Location> {

    static {
        register(ExprBeeHive.class, Location.class, "hive [location]", "livingentities");
    }

    @Nullable
    @Override
    public Location convert(LivingEntity entity) {
        if (!(entity instanceof Bee)) return null;
        return ((Bee)entity).getHive();
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.RESET || mode == ChangeMode.DELETE || mode == ChangeMode.SET) {
                return CollectionUtils.array(Location.class);
        }
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
        Location changeValue = (delta == null ? null : ((Location) delta[0]));
        if (mode == ChangeMode.SET) {
            for (LivingEntity entity : getExpr().getArray(e)) {
                if (!(entity instanceof Bee)) continue;
                ((Bee) entity).setHive(changeValue);
            }
        }
        if (mode == ChangeMode.RESET || mode == ChangeMode.DELETE) {
            for (LivingEntity entity : getExpr().getArray(e)) {
                if (!(entity instanceof Bee)) continue;
                ((Bee) entity).setHive(null);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "hive";
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

}
