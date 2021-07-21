package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;

public class CondIsCrouching extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Fox")) {
            register(CondIsCrouching.class, "[fox] (crouching|sitting)", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {
        if (!(e instanceof Fox)) return false;
        return ((Fox)e).isCrouching();
    }

    @Override
    protected String getPropertyName() {
        return "crouching";
    }
}
