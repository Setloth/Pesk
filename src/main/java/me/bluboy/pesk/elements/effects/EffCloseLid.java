package me.bluboy.pesk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.block.Lidded;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffCloseLid extends Effect {

    static {
        Skript.registerEffect(EffCloseLid.class, "close [lid] [of] %blocks%");
    }

    private Expression<Block> block;

    @Override
    protected void execute(Event event) {
        Block b = block.getSingle(event);

        if (!(b.getState() instanceof Lidded)) {
            return;
        }

        ((Lidded)b.getState()).close();

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "close lid of "+block.toString(event, b)+" ";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.block = (Expression<Block>) expressions[0];
        return true;
    }
}
