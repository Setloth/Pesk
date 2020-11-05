package me.bluboy.addon.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.event.entity.BatToggleSleepEvent;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.awt.print.Book;

public class SimpleEvents extends SimpleEvent {

    static {
        if (Skript.classExists("org.bukkit.event.entity.BatToggleSleepEvent")) {
            Skript.registerEvent("Bat Toggle Sleep", SimpleEvent.class, BatToggleSleepEvent.class, "[bat] toggle sleep");
        }
        if (Skript.classExists("org.bukkit.event.player.PlayerTakeLecternBookEvent")) {
            Skript.registerEvent("Lectern Take Event", SimpleEvent.class, PlayerTakeLecternBookEvent.class, "[player] lectern take [book]");
            EventValues.registerEventValue(PlayerTakeLecternBookEvent.class, Block.class, new Getter<Block, PlayerTakeLecternBookEvent>() {
                @Nullable
                @Override
                public Block get(PlayerTakeLecternBookEvent e) {
                    return e.getLectern().getBlock();
                }
            }, 0);

            EventValues.registerEventValue(PlayerTakeLecternBookEvent.class, ItemStack.class, new Getter<ItemStack, PlayerTakeLecternBookEvent>() {
                @Nullable
                @Override
                public ItemStack get(PlayerTakeLecternBookEvent e) {
                    return e.getBook();
                }
            }, 0);
        }
    }

}