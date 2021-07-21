package me.bluboy.pesk.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.papermc.paper.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ExprItemRarity extends SimplePropertyExpression<ItemStack, ItemRarity> {

    static {
        register(ExprItemRarity.class, ItemRarity.class, "[item] rarity", "itemstacks");
    }

    @Override
    protected String getPropertyName() {
        return "item rarity";
    }

    @Override
    public @Nullable ItemRarity convert(ItemStack itemStack) {
        return itemStack.getRarity();
    }

    @Override
    public Class<? extends ItemRarity> getReturnType() {
        return ItemRarity.class;
    }
}
