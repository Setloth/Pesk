package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprTicksPerAnimalSpawns extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprTicksPerAnimalSpawns.class, Number.class, ExpressionType.SIMPLE, "[the] animal spawn ticks");
    }

    @Nullable
    @Override
    protected Number[] get(Event event) {
        return new Number[] {Bukkit.getTicksPerAmbientSpawns()};
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
        return "the ambient spawn ticks";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }

}
