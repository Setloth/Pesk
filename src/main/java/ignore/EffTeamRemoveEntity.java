package ignore;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class EffTeamRemoveEntity extends Effect {

    static {
        if (Skript.classExists("org.bukkit.scoreboard.Team")) {
            Skript.registerEffect(EffTeamRemoveEntity.class, "remove %livingentities% from [the] team [named] %teams%");
        }


    }

    private Expression<LivingEntity> entities;
    private Expression<Team> teams;

    @Override
    protected void execute(Event event) {
        LivingEntity[] entity = entities.getArray(event);
        Team team = teams.getSingle(event);
        for (LivingEntity lEntity : entity) {
            if (!team.getEntries().contains(lEntity)) {
                Skript.error(lEntity.getName()+" is not in the team "+team.getName());
                return;
            }
            team.removeEntry(lEntity.getName());
        }

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "remove "+entities.toString(event, b)+" from team "+teams.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entities = (Expression<LivingEntity>) expressions[0];
        teams = (Expression<Team>) expressions[1];
        return true;
    }
}
