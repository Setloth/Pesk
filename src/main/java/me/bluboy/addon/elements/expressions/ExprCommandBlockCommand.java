package me.bluboy.addon.elements.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprCommandBlockCommand extends SimplePropertyExpression<Block, String> {

    static {
        register(ExprCommandBlockCommand.class, String.class, "command", "blocks");
    }

    @Override
    @Nullable
    public String convert(final Block b) {
        if (!(b.getState() instanceof CommandBlock)) return null;
        return ((CommandBlock)b.getState()).getCommand();
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
                ((CommandBlock)block).setCommand(null);
            }
        } else {
            final String text = (String) delta[0];
            for (final Block block : getExpr().getArray(e)) {
                ((CommandBlock)block).setCommand(text);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "command";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

}
