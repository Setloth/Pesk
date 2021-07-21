package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class CondCanBreakDoors extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Zombie")) {
            register(CondCanBreakDoors.class, PropertyType.CAN, "break door[s]", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (!(entity instanceof Zombie)) return false;
        return ((Zombie)entity).canBreakDoors();
    }

    @Override
    public String getPropertyName() {
        return "break doors";
    }

}
