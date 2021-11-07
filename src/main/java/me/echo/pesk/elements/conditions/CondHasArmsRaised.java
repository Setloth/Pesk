package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class CondHasArmsRaised extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Zombie")) {
            register(CondHasArmsRaised.class, PropertyType.HAVE,"arm[s] (raised|up|held)", "livingentities");
        }
    }

    @Override
    public boolean check(final LivingEntity e) {
        if (!(e instanceof Zombie)) return false;
        return ((Zombie) e).isArmsRaised();
    }

    @Override
    protected String getPropertyName() {
        return "arms raised";
    }
}
