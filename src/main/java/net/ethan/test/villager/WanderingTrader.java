package net.ethan.test.villager;

import net.ethan.test.Test;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Test.MOD_ID)
public class WanderingTrader {

    @SubscribeEvent
    public static void onTradeSetup(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();
        //Didn't find a better way to remove specific trades
        genericTrades.remove(2); //glowstone
        genericTrades.remove(3); //fern
        genericTrades.remove(4); //pumpkin
        genericTrades.remove(6); //dandelion
        genericTrades.remove(6); //poppy
        genericTrades.remove(16); //wheat seeds
        for (int i = 0; i < 16; i++)
        {
            genericTrades.remove(25); //all dyes
        }
        rareTrades.remove(4); //gunpowder

        genericTrades.add(new ItemsForEmeraldsTrade(Items.SUNFLOWER, 1, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.LILAC, 1, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.ROSE_BUSH, 1, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.PEONY, 1, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.COCOA_BEANS, 3, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.BAMBOO, 3, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.SWEET_BERRIES, 3, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.CARROT, 1, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.POTATO, 1, 1, 12, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.WITHER_ROSE, 4, 1, 6, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.LAVA_BUCKET, 2, 1, 10, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.HONEY_BOTTLE, 1, 4, 10, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.HONEYCOMB, 1, 3, 10, 1));
        genericTrades.add(new ItemsForEmeraldsTrade(Items.SPORE_BLOSSOM, 1, 8, 10, 1));

        rareTrades.add(new ItemsForEmeraldsTrade(Blocks.MYCELIUM, 3, 3, 6, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Blocks.SPONGE, 10, 1, 4, 2));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.ENCHANTED_GOLDEN_APPLE, 64, 1, 1, 5));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.TURTLE_EGG, 6, 1, 4, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.TRIDENT, 16, 1, 1, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.WITHER_SKELETON_SKULL, 40, 1, 1, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.AXOLOTL_BUCKET, 5, 1, 5, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.ZOMBIE_HEAD, 30, 1, 1, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.CREEPER_HEAD, 30, 1, 1, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.SKELETON_SKULL, 30, 1, 1, 1));
        rareTrades.add(new ItemsForEmeraldsTrade(Items.BLAZE_ROD, 30, 1, 1, 1));
    }
    static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
        private final ItemStack offer;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int givenEXP;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(Block offer, int price, int count, int maxUses, int givenEXP)
        {
            this(new ItemStack(offer), price, count, maxUses, givenEXP);
        }

        public ItemsForEmeraldsTrade(Item offer, int price, int count, int maxUses) {
            this(new ItemStack(offer), price, count, 12, maxUses);
        }

        public ItemsForEmeraldsTrade(Item offer, int price, int count, int maxUses, int givenEXP) {
            this(new ItemStack(offer), price, count, maxUses, givenEXP);
        }

        public ItemsForEmeraldsTrade(ItemStack offer, int price, int count, int maxUses, int givenEXP) {
            this(offer, price, count, maxUses, givenEXP, 0.05F);
        }

        public ItemsForEmeraldsTrade(ItemStack offer, int price, int count, int maxUses, int givenEXP, float priceMultiplier)
        {
            this.offer = offer;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.givenEXP = givenEXP;
            this.priceMultiplier = priceMultiplier;
        }
        public MerchantOffer getOffer(Entity entity, RandomSource source){
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.price), new ItemStack(this.offer.getItem(), this.count), this.maxUses, this.givenEXP, this.priceMultiplier);
        }
    }

}
