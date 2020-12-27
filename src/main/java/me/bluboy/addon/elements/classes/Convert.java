package me.bluboy.addon.elements.classes;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class Convert {

    static {
        Converters.registerConverter(String.class, Team.class, new Converter<String, Team>() {
            @Nullable
            @Override
            public Team convert(final String s) {
                return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(s);
            }
        });

    }

}
