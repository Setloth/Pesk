package ignore;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class CondTeamFriendlyFire extends Condition {

    static {
        if (Skript.classExists("org.bukkit.scoreboard.Team")) {

            Skript.registerCondition(CondTeamFriendlyFire.class, "%teams%'[s] [allow] friendly fire [state]", "[allow] friendly fire [state] [of] %teams%");
        }
    }

    private Expression<Team> teams;

    @Override
    public boolean check(Event event) {
        for (Team t : teams.getArray(event)) {
            if (!t.allowFriendlyFire()) return true;
            continue;
        }
        return false;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Friendly Fire of "+teams.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        this.teams = (Expression<Team>) expressions[0];
        return true;
    }
}
