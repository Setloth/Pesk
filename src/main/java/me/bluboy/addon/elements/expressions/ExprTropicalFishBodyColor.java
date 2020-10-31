package me.bluboy.addon.elements.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.SkriptColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprTropicalFishBodyColor extends SimplePropertyExpression<LivingEntity, SkriptColor> {

    static {
        register(ExprTropicalFishBodyColor.class, SkriptColor.class, "body color", "livingentities");
    }

    @Override
    @Nullable
    public SkriptColor convert(final LivingEntity e) {
        if (!(e instanceof TropicalFish)) return null;
        return SkriptColor.fromDyeColor(((TropicalFish)e).getBodyColor());
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {SkriptColor.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {

                ((TropicalFish)entity).setBodyColor(null);
            }
        } else {
            final SkriptColor color = (SkriptColor) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((TropicalFish)entity).setBodyColor(color.asDyeColor());
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "body color";
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
    }

}
