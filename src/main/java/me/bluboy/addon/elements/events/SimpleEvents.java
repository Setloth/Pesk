package me.bluboy.addon.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.BatToggleSleepEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.Nullable;

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

    }

}