package me.echo.pesk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffClearTitle extends Effect {

    static {
        Skript.registerEffect(EffClearTitle.class, "(clear|reset) title [(for|of)] %livingentities%");
    }

    private Expression<LivingEntity> entity;

    @Override
    protected void execute(Event event) {
        LivingEntity[] entities = entity.getArray(event);

        for (LivingEntity lEntity : entities) {
            if (lEntity == null) continue;
            lEntity.resetTitle();
        }

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "clear title of "+entity.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.entity = (Expression<LivingEntity>) expressions[0];
        return true;

    }
}
