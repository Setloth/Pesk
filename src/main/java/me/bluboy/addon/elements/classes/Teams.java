package me.bluboy.addon.elements.classes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;

public class Teams {

    static {
        Classes.registerClass(new ClassInfo<>(Team.class, "team")
            .user("teams?")
            .name("Team")
                .description("Represents a scoreboard team")
                .defaultExpression(new EventValueExpression<>(Team.class))
                .parser(new Parser<Team>() {
                    @Override
                    public String toString(Team team, int i) {
                        return toVariableNameString(team);
                    }

                    @Override
                    public String toVariableNameString(Team t) {

                        return t.getName();

                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "(.)";
                    }

                    @Nullable
                    @Override
                    public Team parse(String s, ParseContext context) {
                        return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(s);
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return true;
                    }

                })
        );
    }

}
