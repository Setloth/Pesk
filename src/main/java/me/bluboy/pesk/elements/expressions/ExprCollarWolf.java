package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprCollarWolf extends SimplePropertyExpression<LivingEntity, Color> {

    static {
        if (Skript.classExists("org.bukkit.entity.Wolf")) {
            register(ExprCollarWolf.class, Color.class, "collar [color]", "livingentities");
        }
    }

    @Override
    @Nullable
    public Color convert(final LivingEntity e) {
        if (!(e instanceof Wolf)) return null;
        return SkriptColor.fromDyeColor(((Wolf) e).getCollarColor());
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Color.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Wolf)entity).setCollarColor(null);
            }
        } else {
            final Color color = (Color) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Wolf)entity).setCollarColor(color.asDyeColor());
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "collar color";
    }

    @Override
    public Class<? extends Color> getReturnType() {
        return Color.class;
    }

}
