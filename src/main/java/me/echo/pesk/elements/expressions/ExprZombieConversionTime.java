package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprZombieConversionTime extends SimplePropertyExpression<LivingEntity, Number> {

    static {
        if (Skript.classExists("org.bukkit.entity.Zombie")) {
            register(ExprZombieConversionTime.class, Number.class, "conversion time", "livingentities");
        }
    }

    @Override
    @Nullable
    public Number convert(final LivingEntity e) {
        if (!(e instanceof Zombie)) return null;
        return ((Zombie)e).getConversionTime();
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
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Zombie)entity).setConversionTime(0);
            }
        } else {
            final Number num = (Number) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Zombie)entity).setConversionTime(num.intValue());
            }
        }

    }

    @Override
    protected String getPropertyName() {
        return "conversion time";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

}
