package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerSpawned extends SimplePropertyExpression<LivingEntity, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.entity.Endermite")) {
            register(ExprPlayerSpawned.class, Boolean.class, "player[( |-)]spawn[ed] [value]", "livingentities");
        }
    }

    @Override
    @Nullable
    public Boolean convert(final LivingEntity e) {
        if (!(e instanceof Endermite)) return null;
        return ((Endermite)e).isPlayerSpawned();
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
                ((Endermite)entity).setPlayerSpawned(false);
            }
        } else {
            final Boolean bool = (Boolean) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Endermite)entity).setPlayerSpawned(bool);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "player spawned value";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

}
