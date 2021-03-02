package me.bluboy.pesk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.Nullable;

public class EffAllow extends Effect {

    static {
        Skript.registerEffect(EffAllow.class, "allow [connect[ion]]");
    }

    @Override
    protected void execute(Event event) {
        if (!(event instanceof PlayerLoginEvent)) {
            Skript.error("Allow effect can only be used in on connect event.");
            return;
        }
        ((PlayerLoginEvent) event).allow();
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "allow connection";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {

        return true;
    }
}
