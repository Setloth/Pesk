package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class ExprIndexOf extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprIndexOf.class, Number.class, ExpressionType.PROPERTY,"index of %object% in %objects%");
    }

    private Expression<Object> object, in;

    @Nullable
    @Override
    protected Number[] get(Event event) {
        Object o = object.getSingle(event);
        Object[] i = in.getArray(event);
        ArrayList<Object> os = new ArrayList<>();
        for (Object o1 : i) {
            os.add(o1);
        }
        return new Number[] {os.indexOf(o)};
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
        return "index of "+object.toString(event, b)+" in "+in.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        object = (Expression<Object>) expressions[0];
        in = (Expression<Object>) expressions[1];

        return true;
    }
}
