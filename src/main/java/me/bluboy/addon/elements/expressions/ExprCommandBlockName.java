package me.bluboy.addon.elements.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprCommandBlockName extends SimplePropertyExpression<Block, String> {

    static {
        register(ExprCommandBlockName.class, String.class, "(executor|name)", "blocks");
    }

    @Override
    @Nullable
    public String convert(final Block b) {
        if (!(b.getState() instanceof CommandBlock)) return null;
        return ((CommandBlock)b.getState()).getName();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {String.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final Block block : getExpr().getArray(e)) {
                ((CommandBlock)block).setName(null);
            }
        } else {
            final String text = (String) delta[0];
            for (final Block block : getExpr().getArray(e)) {
                ((CommandBlock)block).setName(text);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "executor";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

}
