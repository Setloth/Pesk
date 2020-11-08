package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExprEndermanCarriedBlock extends SimplePropertyExpression<LivingEntity, BlockData> {

    static {
        if (Skript.classExists("org.bukkit.entity.Enderman")) {
            register(ExprEndermanCarriedBlock.class, BlockData.class, "carried[( |-)]block[data]", "livingentities");
        }
    }

    @Override
    @Nullable
    public BlockData convert(final LivingEntity e) {
        if (!(e instanceof Enderman)) return null;
        return ((Enderman)e).getCarriedBlock();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {BlockData.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Enderman)entity).setCarriedBlock(null);
            }
        } else {

            final BlockData block = (BlockData) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Enderman)entity).setCarriedBlock(block);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "carried block";
    }

    @Override
    public Class<? extends BlockData> getReturnType() {
        return BlockData.class;
    }
}
