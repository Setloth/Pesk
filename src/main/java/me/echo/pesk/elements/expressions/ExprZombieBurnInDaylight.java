package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprZombieBurnInDaylight extends SimplePropertyExpression<LivingEntity, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.entity.Zombie")) {
            register(ExprZombieBurnInDaylight.class, Boolean.class, "burn [in] day[( |-|)light] [state]", "livingentities");
        }
    }

    @Override
    @Nullable
    public Boolean convert(final LivingEntity e) {
        if (!(e instanceof Zombie)) return null;
        return ((Zombie)e).shouldBurnInDay();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Boolean.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Zombie)entity).setShouldBurnInDay(true);
            }
        } else {
            final Boolean bool = (Boolean) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Zombie)entity).setShouldBurnInDay(bool);
            }
        }

    }

    @Override
    protected String getPropertyName() {
        return "burn in daylight state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

}
