package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Chunk;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprChunkForceLoaded extends SimplePropertyExpression<Chunk, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.Chunk")) {
            register(ExprChunkForceLoaded.class, Boolean.class, "force[ ]loaded [state]", "chunks");
        }
    }

    @Override
    @Nullable
    public Boolean convert(final Chunk c) {
        return c.isForceLoaded();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Boolean.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final Chunk c : getExpr().getArray(e)) {
                c.setForceLoaded(false);
            }
        } else {
            final Boolean bool = (Boolean) delta[0];
            for (final Chunk c : getExpr().getArray(e)) {
                c.setForceLoaded(bool);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "force loaded state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

}
