package me.echo.pesk.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;

public class CondIsPollinated extends PropertyCondition<LivingEntity> {

    static {
        register(CondIsPollinated.class, "pollinated", "livingentities");
    }

    @Override
    public boolean check(LivingEntity e) {
        if (!(e instanceof Bee)) return false;
        return ((Bee)e).hasNectar();
    }

    @Override
    protected String getPropertyName() {
        return "pollination";
    }
}
