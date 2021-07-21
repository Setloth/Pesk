package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;

public class CondIsCharged extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Wither")) {
            register(CondIsCharged.class, "charged [wither]", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {
        if (!(e instanceof Wither)) return false;
        return ((Wither)e).isCharged();
    }

    @Override
    protected String getPropertyName() {
        return "charged";
    }
}
