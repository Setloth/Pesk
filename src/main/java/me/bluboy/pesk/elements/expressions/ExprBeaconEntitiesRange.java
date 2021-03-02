package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
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

public class ExprBeaconEntitiesRange extends SimpleExpression<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.block.Beacon")) {
            Skript.registerExpression(ExprBeaconEntitiesRange.class, LivingEntity.class, ExpressionType.PROPERTY, "entit(ys|ies) in range of %blocks%", "%blocks%'[s] entit(ys|ies) in range");
        }
    }

    private Expression<Block> block;

    @Nullable
    @Override
    protected LivingEntity[] get(Event event) {
        Block b = block.getSingle(event);
        if (b == null || !(b.getState() instanceof Beacon)) return null;
        List<LivingEntity> ents = new ArrayList<>();
        for (LivingEntity ent : ((Beacon)b.getState()).getEntitiesInRange()) {
            ents.add(ent);
        }
        LivingEntity[] array = ents.toArray(new LivingEntity[0]);
        return array;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends LivingEntity> getReturnType() {
        return LivingEntity.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "entities in range" + (block != null ? " of "+block.toString(event, b) : "");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.block = (Expression<Block>)expressions[0];
        return true;
    }
}
