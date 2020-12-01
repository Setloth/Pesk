package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExprBool extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprBool.class, Boolean.class, ExpressionType.SIMPLE,"!%booleans%");
    }

    private Expression<Boolean> bool;

    @Nullable
    @Override
    protected Boolean[] get(Event event) {
        Boolean b = bool.getSingle(event);
        if (b == null) return null;
        return new Boolean[] {!b};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "!"+bool.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        bool = (Expression<Boolean>) expressions[0];
        return true;
    }
}
