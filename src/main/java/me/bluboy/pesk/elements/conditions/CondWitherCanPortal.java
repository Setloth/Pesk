package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;

public class CondWitherCanPortal extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Wither")) {
            register(CondWitherCanPortal.class, PropertyType.CAN, "(travel|pass) [through] portal[s]", "livingentities");
        }
    }

    @Override
    public boolean check(final LivingEntity e) {
        if (!(e instanceof Wither)) return false;
        return ((Wither) e).canTravelThroughPortals();
    }

    @Override
    protected String getPropertyName() {
        return "travel through portals";
    }
}
