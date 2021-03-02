package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExprChunkEntities extends SimpleExpression<Entity> {

    static {
            Skript.registerExpression(ExprChunkEntities.class, Entity.class, ExpressionType.PROPERTY, "entities (of|in) %chunks%", "%chunks%'[s] entities");

    }

    private Expression<Chunk> chunks;

    @Nullable
    @Override
    protected Entity[] get(Event event) {
        Chunk chunk = chunks.getSingle(event);
        if (chunk == null) {
            Skript.error("Cannot get entities because the chunk does not exist");
            return null;
        }
        List<Entity> entities = new ArrayList<>();
        for (Entity ent : chunk.getEntities()) {
            entities.add(ent);
        }
        Entity[] array = entities.toArray(new Entity[0]);
        return array;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "entities " + (chunks != null ? " of "+chunks.toString(event, b) : "");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.chunks = (Expression<Chunk>)expressions[0];
        return true;
    }
}
