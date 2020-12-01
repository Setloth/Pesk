package me.bluboy.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffTeamCreate extends Effect {

    static {
        Skript.registerEffect(EffTeamCreate.class, "(create|make|register) [a] [new] team [named] %strings%");
    }

    private Expression<String> name;

    @Override
    protected void execute(Event event) {
        String str = name.getSingle(event);
        if (Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(str) != null) {
            Skript.error("This team name is already in use");
            return;
        }
        Bukkit.getServer().getScoreboardManager().getMainScoreboard().registerNewTeam(str);
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "create team "+name.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) expressions[0];
        return true;
    }
}
