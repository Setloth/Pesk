package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

public class ExprBeaconSecondaryEffect extends SimplePropertyExpression<Block, PotionEffectType> {

    static {
        if (Skript.classExists("org.bukkit.block.Beacon")) {
            register(ExprBeaconSecondaryEffect.class, PotionEffectType.class, "secondary [potion] (e|a)ffect", "blocks");
        }
    }


    @Override
    @Nullable
    public PotionEffectType convert(final Block b) {
        if (!(b.getState() instanceof Beacon)) return null;
        if (((Beacon)b.getState()).getSecondaryEffect() == null) return null;
        return ((Beacon)b.getState()).getSecondaryEffect().getType();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return new Class[] {PotionEffectType.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final Changer.ChangeMode mode) {
        if (delta == null) {
            for (final Block block : getExpr().getArray(e)) {
                ((Beacon)block).setSecondaryEffect(null);
            }
        } else {
            final PotionEffectType potion = (PotionEffectType) delta[0];
            for (final Block block : getExpr().getArray(e)) {
                ((Beacon)block).setSecondaryEffect(potion);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "secondary effect";
    }

    @Override
    public Class<? extends PotionEffectType> getReturnType() {
        return PotionEffectType.class;
    }

}
