package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;

public class CondHasStung extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Bee")) {
            register(CondHasStung.class, PropertyType.HAVE, "stung", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (!(entity instanceof Bee)) return false;
        return ((Bee)entity).hasStung();
    }

    @Override
    public String getPropertyName() {
        return "stung";
    }

}
