package me.echo.pesk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import org.bukkit.event.Event;
import org.bukkit.event.block.FluidLevelChangeEvent;
import org.jetbrains.annotations.Nullable;

public class EvtFluidLevelChange extends SkriptEvent {
    
    static {
        if (Skript.classExists("org.bukkit.event.block.FluidLevelChangeEvent")) {
            Skript.registerEvent("Fluid Level Change", EvtFluidLevelChange.class, FluidLevelChangeEvent.class, "[block] fluid [level] chang(e|ing) [(of|for) %-itemtypes%]");
        }
    }

    @Nullable
    private Literal<ItemType> types;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
        types = (Literal<ItemType>) args[0];
        return true;
    }

    @Override
    public boolean check(Event e) {
        if (types != null) {
            for (ItemType type : types.getAll()) {
                if (new ItemType(((FluidLevelChangeEvent) e).getBlock()).equals(type))
                    return true;
            }
            return false;
        }

        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "fluid level change";
    }

}
