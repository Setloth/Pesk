package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Strider;

public class CondIsShivering extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Strider")){
            register(CondIsShivering.class, "shivering", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {
        if (!(e instanceof Strider)) return false;
        return ((Strider)e).isShivering();
    }

    @Override
    protected String getPropertyName() {
        return "shivering";
    }
}
