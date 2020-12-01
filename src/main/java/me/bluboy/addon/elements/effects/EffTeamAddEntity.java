package me.bluboy.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class EffTeamAddEntity extends Effect {

    static {
        Skript.registerEffect(EffTeamAddEntity.class, "add %livingentities% to [the] team [named] %teams%");
    }

    private Expression<LivingEntity> entities;
    private Expression<Team> teams;

    @Override
    protected void execute(Event event) {
        LivingEntity entity = entities.getSingle(event);
        Team team = teams.getSingle(event);
        if (team.getEntries().contains(entity)) {
            Skript.error(entity.getName()+" is already in the team "+team.getName());
            return;
        }
        team.addEntry(entity.getName());
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "add "+entities.toString(event, b)+" to team "+teams.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entities = (Expression<LivingEntity>) expressions[0];
        teams = (Expression<Team>) expressions[1];
        return true;
    }
}
