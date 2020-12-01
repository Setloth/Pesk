package me.bluboy.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class EffTeamDelete extends Effect {

    static {
        Skript.registerEffect(EffTeamDelete.class, "(delete|unregister) team %teams%");
    }

    private Expression<Team> teams;

    @Override
    protected void execute(Event event) {
        Team team = teams.getSingle(event);
        if (team == null) {
            Skript.error("This team does not exist");
            return;
        }
        team.unregister();
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "delete team "+teams.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        teams = (Expression<Team>) expressions[0];
        return true;
    }
}
