package me.bluboy.addon.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.entity.TropicalFish;

public class CondIsBeehiveSedated extends PropertyCondition<Block> {

    static {
        register(CondIsBeehiveSedated.class, "sedated", "blocks");
    }

    @Override
    public boolean check(final Block b) {
        if (!(b.getState() instanceof Beehive)) return false;
        return ((Beehive)b.getState()).isSedated();
    }

    @Override
    protected String getPropertyName() {
        return "sedated";
    }
}
