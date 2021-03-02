package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprRestockAmount extends SimplePropertyExpression<LivingEntity, Number> {

    static {
        if (Skript.classExists("org.bukkit.entity.Villager")) {
            register(ExprRestockAmount.class, Number.class, "restock[s] [amount] [today]", "livingentities");
        }
    }

    @Nullable
    @Override
    public Number convert(LivingEntity entity) {
        if (!(entity instanceof Villager)) return null;
        return ((Villager)entity).getRestocksToday();
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.RESET || mode == ChangeMode.DELETE || mode == ChangeMode.SET) {
                return CollectionUtils.array(Number.class);
        }
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
        Number changeValue = (Number) delta[0];
        if (mode == ChangeMode.SET) {
            for (LivingEntity entity : getExpr().getArray(e)) {
                if (!(entity instanceof Villager)) continue;
                ((Villager) entity).setRestocksToday(changeValue.intValue());
            }
        }
        if (mode == ChangeMode.RESET || mode == ChangeMode.DELETE) {
            for (LivingEntity entity : getExpr().getArray(e)) {
                if (!(entity instanceof Villager)) continue;
                ((Villager) entity).setRestocksToday(0);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "restocks";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

}
