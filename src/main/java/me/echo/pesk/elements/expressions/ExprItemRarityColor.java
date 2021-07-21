package me.echo.pesk.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.SkriptColor;
import io.papermc.paper.inventory.ItemRarity;
import org.bukkit.Color;
import org.jetbrains.annotations.Nullable;

public class ExprItemRarityColor extends SimplePropertyExpression<ItemRarity, SkriptColor> {

    static {
        register(ExprItemRarityColor.class, SkriptColor.class, "[item] rarity color", "itemrarity");
    }

    @Override
    protected String getPropertyName() {
        return "item rarity color";
    }

    @Override
    public @Nullable SkriptColor convert(ItemRarity itemRarity) {
        return SkriptColor.fromBukkitColor(Color.fromRGB(itemRarity.getColor().red(), itemRarity.getColor().green(), itemRarity.getColor().blue()));
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
    }
}
