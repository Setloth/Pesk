package me.echo.pesk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffVillagerSleep extends Effect {

    static {
        Skript.registerEffect(EffVillagerSleep.class, "(force|make) [villager] %livingentities% [to] sleep [at] %locations%");
    }

    private Expression<LivingEntity> entity;
    private Expression<Location> location;

    @Override
    protected void execute(Event event) {
        LivingEntity ent = entity.getSingle(event);
        Location loc = location.getSingle(event);

        if (!(ent instanceof Villager)) {
            return;
        }

        ((Villager)ent).sleep(loc);

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "make "+entity.toString(event, b)+" sleep at "+location.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.entity = (Expression<LivingEntity>) expressions[0];
        this.location = (Expression<Location>) expressions[1];
        return true;
    }
}
