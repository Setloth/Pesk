package me.bluboy.addon.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.Chunk;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;

public class CondIsForceLoaded extends PropertyCondition<Chunk> {

    static {
        if (Skript.classExists("org.bukkit.Chunk")) {
            register(CondIsForceLoaded.class, "force[ ]loaded", "chunks");
        }
    }

    @Override
    public boolean check(Chunk c) {
        return c.isForceLoaded();
    }

    @Override
    protected String getPropertyName() {
        return "force loaded";
    }
}
