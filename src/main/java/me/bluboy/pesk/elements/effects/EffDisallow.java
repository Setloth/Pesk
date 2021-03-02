package me.bluboy.pesk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.jetbrains.annotations.Nullable;

public class EffDisallow extends Effect {

    static {
        Skript.registerEffect(EffDisallow.class, "(disallow|deny) [connect[ion]] [with] message %string%");
    }

    private Expression<String> string;

    @Override
    protected void execute(Event event) {

        if (!(event instanceof PlayerLoginEvent)) {
            Skript.error("Disallow effect can only be used in on connect event.");
            return;
        }

        String s = string.getSingle(event);

        if (s == null) {
            return;
        }

        ((PlayerLoginEvent) event).disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text(s));

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "allow connection";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.string = (Expression<String>) expressions[0];
        return true;
    }
}
