package me.bluboy.addon.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.jetbrains.annotations.Nullable;

public class ExprObjectClass extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprObjectClass.class, String.class, "class [name]", "objects");
    }

    @Override
    protected String getPropertyName() {
        return "class name";
    }

    @Nullable
    @Override
    public String convert(Object o) {
        return o.getClass().getSimpleName().trim();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
