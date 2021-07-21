package me.echo.pesk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffZombieStopDrowning extends Effect {

    static {
        Skript.registerEffect(EffZombieStopDrowning.class, "(force|make) [zombie] %livingentities% stop (drown|convert)[ing]");
    }

    private Expression<LivingEntity> entity;
    private Expression<Location> location;

    @Override
    protected void execute(Event event) {
        LivingEntity ent = entity.getSingle(event);

        if (!(ent instanceof Zombie)) return;

        ((Zombie)ent).stopDrowning();

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "make "+entity.toString(event, b)+" stop drowning";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.entity = (Expression<LivingEntity>) expressions[0];
        return true;

    }
}
