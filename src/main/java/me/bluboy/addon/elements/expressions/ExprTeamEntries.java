package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExprTeamEntries extends SimpleExpression<String> {

    static {
            Skript.registerExpression(ExprTeamEntries.class, String.class, ExpressionType.COMBINED, "entries of %teams%", "%teams%'[s] entries");

    }

    private Expression<Team> teams;

    @Nullable
    @Override
    protected String[] get(Event event) {
        Team t = teams.getSingle(event);
        if (t == null) {
            Skript.error("Cannot get entries because the team does not exist");
            return null;
        }
        List<String> ss = new ArrayList<>();
        for (String ent : t.getEntries()) {
            ss.add(ent);
        }
        String[] array = ss.toArray(new String[0]);
        return array;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "entries " + (teams != null ? " of "+teams.toString(event, b) : "");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.teams = (Expression<Team>)expressions[0];
        return true;
    }
}
