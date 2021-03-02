package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ExprBanList extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprBanList.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] ban[ ]list", "[all] [the] banned players");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event event) {
        ArrayList<OfflinePlayer> list = new ArrayList<>();
        for (OfflinePlayer p : Bukkit.getBannedPlayers()) {
            list.add(p);
        }
        OfflinePlayer[] array = list.toArray(new OfflinePlayer[0]);
        return array;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "all banned players";
    }
}
