package me.bluboy.pesk.elements.classes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import io.papermc.paper.inventory.ItemRarity;

public class ItemRarityInfo {

    static {
        final EnumUtils<ItemRarity> rarityTypes = new EnumUtils<>(ItemRarity.class, "item rarity");
        Classes.registerClass(new ClassInfo<>(ItemRarity.class, "itemrarity")
                .user("(item)? ?rarity")
                .name("Item Rarity")
                .usage(rarityTypes.getAllNames())
                .parser(new Parser<ItemRarity>(){

                    @Override
                    public String toString(ItemRarity itemRarity, int i) {
                        return itemRarity.name().toLowerCase().replaceAll("_", " ");
                    }

                    @Override
                    public String toVariableNameString(ItemRarity itemRarity) {
                        return "rarity:"+itemRarity.name();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "rarity:(.*)";
                    }
                })
        );
    }

}
