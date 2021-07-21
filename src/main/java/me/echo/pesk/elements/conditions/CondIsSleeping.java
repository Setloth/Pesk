package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.LivingEntity;

public class CondIsSleeping extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.LivingEntity")) {
            register(CondIsSleeping.class, "sleeping", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {
        return e.isSleeping();
    }

    @Override
    protected String getPropertyName() {
        return "sleeping";
    }
}
