package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

public class CondIsPlayerCreated extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.IronGolem")) {
            register(CondIsPlayerCreated.class, "player[( |-)]creat(e|ed)", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {

        if (!(e instanceof IronGolem)) return false;
        return ((IronGolem)e).isPlayerCreated();
        
    }

    @Override
    protected String getPropertyName() {
        return "player created";
    }
}
