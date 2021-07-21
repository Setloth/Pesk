package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprTrimmed extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprTrimmed.class, String.class, ExpressionType.PROPERTY,"trim[med] %strings%");
    }

    private Expression<String> string;

    @Nullable
    @Override
    protected String[] get(Event event) {
        String str = string.getSingle(event);
        if (str == null) return null;
        return new String[] {str.trim()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "trim[med] "+string.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        string = (Expression<String>) expressions[0];
        return true;
    }
}
