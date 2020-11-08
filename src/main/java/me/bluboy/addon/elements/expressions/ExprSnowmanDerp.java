package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowman;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprSnowmanDerp extends SimplePropertyExpression<LivingEntity, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.entity.Snowman")) {
            register(ExprSnowmanDerp.class, Boolean.class, "derp [state]", "livingentities");
        }
    }

    @Override
    @Nullable
    public Boolean convert(final LivingEntity e) {
        if (!(e instanceof Snowman)) return null;
        return ((Snowman)e).isDerp();
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
                ((Snowman)entity).setDerp(false);
            }
        } else {
            final Boolean bool = (Boolean) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Snowman)entity).setDerp(bool);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "derp state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

}
