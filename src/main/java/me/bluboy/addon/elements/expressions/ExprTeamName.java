package me.bluboy.addon.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class ExprTeamName extends SimplePropertyExpression<Team, String> {

    static {
        register(ExprTeamName.class, String.class, "[team] name", "teams");
    }

    @Override
    protected String getPropertyName() {
        return "team name";
    }

    @Nullable
    @Override
    public String convert(Team team) {
        return team.getName();
    }


    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
