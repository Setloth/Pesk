package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.LivingEntity;

public class CondIsPlayerSpawned extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Endermite")) {
            register(CondIsPlayerSpawned.class, "player[( |-)]spawn[ed]", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity e) {

        if (!(e instanceof Endermite)) return false;
        return ((Endermite)e).isPlayerSpawned();

    }

    @Override
    protected String getPropertyName() {
        return "player spawned";
    }
}
