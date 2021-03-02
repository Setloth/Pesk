package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CondInsideBorder extends Condition {

    static {
        Skript.registerCondition(CondInsideBorder.class, "%locations% (1¦is|2¦is(n't| not)) inside %worlds%['[s] [world[ ]]border]", "%locations% (1¦is|2¦is(n't| not)) inside [[world[ ]]border of] %worlds%");
    }

    Expression<Location> locations;
    Expression<World> borders;

    @Override
    public boolean check(Event event) {
        World wo = borders.getSingle(event);
        if (wo == null) return false;
        WorldBorder w = wo.getWorldBorder();
        Location l = locations.getSingle(event);

        if (w == null || l == null) return false;

        return w.isInside(l);
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return locations.toString(event, b)+" inside border of "+ borders.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.locations = (Expression<Location>) expressions[0];
        this.borders = (Expression<World>) expressions[1];
        return true;
    }
}
