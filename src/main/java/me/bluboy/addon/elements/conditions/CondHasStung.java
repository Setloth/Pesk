package me.bluboy.addon.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CondHasStung extends Condition {

    static {
        if (Skript.classExists("org.bukkit.entity.Bee")) {
            Skript.registerCondition(CondHasStung.class, "%livingentities% (have|has) stung");
        }
    }

    private Expression<LivingEntity> entities;

    @Override
    public boolean check(Event event) {
        for (LivingEntity entity : entities.getArray(event)) {
            if (!(entity instanceof Bee) || !((Bee) entity).hasStung()) continue;
            else return true;
        }
        return false;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        this.entities = (Expression<LivingEntity>) expressions[0];
        return true;
    }
}
