package me.bluboy.addon.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class ExprTeamSize extends SimplePropertyExpression<Team, Number> {

    static {
        register(ExprTeamSize.class, Number.class, "[team] (size|entry (size|amount)|amount)", "teams");
    }

    @Override
    protected String getPropertyName() {
        return "team prefix";
    }

    @Nullable
    @Override
    public Number convert(Team team) {
        return team.getSize();
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}
