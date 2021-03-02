package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;

public class CondIsAwake extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Bat")) {
            register(CondIsAwake.class, "awake", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {
        if (!(e instanceof Bat)) return false;
        return ((Bat)e).isAwake();
    }

    @Override
    protected String getPropertyName() {
        return "awake";
    }
}
