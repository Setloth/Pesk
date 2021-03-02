package me.bluboy.pesk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.event.server.WhitelistToggleEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.BatToggleSleepEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerQuitEvent.*;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

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
        if (Skript.classExists("org.bukkit.event.block.CauldronLevelChangeEvent")) {
            Skript.registerEvent("Cauldron Level Change", SimpleEvent.class, CauldronLevelChangeEvent.class, "cauldron [water] level [change]");
            EventValues.registerEventValue(CauldronLevelChangeEvent.class, Number.class, new Getter<Number, CauldronLevelChangeEvent>() {
                @Nullable
                @Override
                public Number get(CauldronLevelChangeEvent e) {
                    return e.getNewLevel();
                }
            }, 0);
            EventValues.registerEventValue(CauldronLevelChangeEvent.class, Number.class, new Getter<Number, CauldronLevelChangeEvent>() {
                @Nullable
                @Override
                public Number get(CauldronLevelChangeEvent e) {
                    return e.getOldLevel();
                }
            }, -1);
            EventValues.registerEventValue(CauldronLevelChangeEvent.class, LivingEntity.class, new Getter<LivingEntity, CauldronLevelChangeEvent>() {
                @Nullable
                @Override
                public LivingEntity get(CauldronLevelChangeEvent e) {
                    return (LivingEntity) e.getEntity();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.inventory.TradeSelectEvent")) {
            Skript.registerEvent("Trade Select", SimpleEvent.class, TradeSelectEvent.class, "[inventory] trade select");
            EventValues.registerEventValue(TradeSelectEvent.class, Inventory.class, new Getter<Inventory, TradeSelectEvent>() {
                @Nullable
                @Override
                public Inventory get(TradeSelectEvent e) {
                    return e.getInventory();
                }
            }, 0);
            EventValues.registerEventValue(TradeSelectEvent.class, Number.class, new Getter<Number, TradeSelectEvent>() {
                @Nullable
                @Override
                public Number get(TradeSelectEvent e) {
                    return e.getIndex();
                }
            }, 0);
            EventValues.registerEventValue(TradeSelectEvent.class, Player.class, new Getter<Player, TradeSelectEvent>() {
                @Nullable
                @Override
                public Player get(TradeSelectEvent e) {
                    return (Player)e.getWhoClicked();
                }
            },0);
        }
        //1.5
        if (Skript.classExists("org.bukkit.event.entity.PlayerLeashEntityEvent")) {
            Skript.registerEvent("Leash", SimpleEvent.class, PlayerLeashEntityEvent.class, "[player] leash");
            EventValues.registerEventValue(PlayerLeashEntityEvent.class, Entity.class, new Getter<Entity, PlayerLeashEntityEvent>() {
                @Nullable
                @Override
                public Entity get(PlayerLeashEntityEvent e) {
                    return e.getEntity();
                }
            }, 0);
            EventValues.registerEventValue(PlayerLeashEntityEvent.class, Player.class, new Getter<Player, PlayerLeashEntityEvent>() {
                @Nullable
                @Override
                public Player get(PlayerLeashEntityEvent e) {
                    return e.getPlayer();
                }
            }, 0);
        }

        if (Skript.classExists("com.destroystokyo.paper.event.server.WhitelistToggleEvent")) {
            Skript.registerEvent("Whitelist Toggle", SimpleEvent.class, WhitelistToggleEvent.class, "white[ ]list (toggle|change)");
            EventValues.registerEventValue(WhitelistToggleEvent.class, Boolean.class, new Getter<Boolean, WhitelistToggleEvent>() {
                @Nullable
                @Override
                public Boolean get(WhitelistToggleEvent e) {
                    return e.isEnabled();
                }
            },0);

        }




    }

}