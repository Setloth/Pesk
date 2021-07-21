package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CondHasStung extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.classExists("org.bukkit.entity.Bee")) {
            register(CondHasStung.class, PropertyType.HAVE, "stung", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (!(entity instanceof Bee)) return false;
        return ((Bee)entity).hasStung();
    }

    @Override
    public String getPropertyName() {
        return "stung";
    }

}
