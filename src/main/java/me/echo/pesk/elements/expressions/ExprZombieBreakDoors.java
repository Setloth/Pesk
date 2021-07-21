package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprZombieBreakDoors extends SimplePropertyExpression<LivingEntity, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.entity.Zombie")) {
            register(ExprZombieBreakDoors.class, Boolean.class, "break door[s] [state]", "livingentities");
        }
    }

    @Override
    @Nullable
    public Boolean convert(final LivingEntity e) {
        if (!(e instanceof Zombie)) return null;
        return ((Zombie)e).isArmsRaised();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET) return new Class[] {Boolean.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) return;
        final Boolean bool = (Boolean) delta[0];
        for (final LivingEntity entity : getExpr().getArray(e)) {
            ((Zombie)entity).setCanBreakDoors(bool);
        }

    }

    @Override
    protected String getPropertyName() {
        return "break doors state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

}
