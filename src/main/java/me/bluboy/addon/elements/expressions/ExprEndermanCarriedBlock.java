package me.bluboy.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExprEndermanCarriedBlock extends SimplePropertyExpression<LivingEntity, Material> {

    static {
        if (Skript.classExists("org.bukkit.entity.Enderman")) {
            register(ExprEndermanCarriedBlock.class, Material.class, "carried[( |-)](block|material)", "livingentities");
        }
    }

    @Override
    @Nullable
    public Material convert(final LivingEntity e) {
        if (!(e instanceof Enderman)) return null;
        return ((Enderman) e).getCarriedBlock().getMaterial();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Material.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Enderman)entity).setCarriedBlock(null);
            }
        } else {

            final Material block = (Material) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Enderman)entity).setCarriedBlock(block.createBlockData());
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "carried block";
    }

    @Override
    public Class<? extends Material> getReturnType() {
        return Material.class;
    }
}
