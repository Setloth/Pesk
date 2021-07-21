package me.bluboy.pesk.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.inventory.ItemStack;

public class CondMarker extends PropertyCondition<ItemStack> {

    static {
        register(CondMarker.class, "marker", "itemstacks");
    }

    @Override
    public boolean check(ItemStack item) {
        return ((ArmorStandMeta) item.getItemMeta()).isMarker();
    }

    @Override
    protected String getPropertyName() {
        return "marker";
    }
}
