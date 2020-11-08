package me.bluboy.addon.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Strider;

public class CondIsDerp extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Snowman")){
            register(CondIsDerp.class, "derp", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {
        if (!(e instanceof Snowman)) return false;
        return ((Snowman)e).isDerp();
    }

    @Override
    protected String getPropertyName() {
        return "derp";
    }
}
