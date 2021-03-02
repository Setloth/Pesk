package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class CondTeamFriendlyInvis extends Condition {

    static {
        Skript.registerCondition(CondTeamFriendlyInvis.class, "%teams%'[s] [can] [see] friendly invis[ibles] [state]", "[can] [see] friendly invis[ibles] [state] [of] %teams%");

    }

    private Expression<Team> teams;

    @Override
    public boolean check(Event event) {
        for (Team t : teams.getArray(event)) {
            if (!t.canSeeFriendlyInvisibles()) return true;
            continue;
        }
        return false;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Friendly Invisibles of "+teams.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        this.teams = (Expression<Team>) expressions[0];
        return true;
    }
}
