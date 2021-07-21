package me.echo.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;

public class CondIsBeehiveSedated extends PropertyCondition<Block> {

    static {
        if (Skript.classExists("org.bukkit.block.Beehive")) {
            register(CondIsBeehiveSedated.class, "sedated", "blocks");
        }
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
