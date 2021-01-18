package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprSpawnRadius extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprSpawnRadius.class, Number.class, ExpressionType.SIMPLE, "[the] spawn[ ]radius");
    }

    @Nullable
    @Override
    protected Number[] get(Event event) {
        return new Number[] {Bukkit.getSpawnRadius()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "the spawn radius";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return new Class[] {Number.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final Changer.ChangeMode mode) {
        if (delta == null) {
            Bukkit.setSpawnRadius(-1);

        } else {
            final Number numb = (Number) delta[0];
            Bukkit.setSpawnRadius((int)numb);

        }
    }
}
