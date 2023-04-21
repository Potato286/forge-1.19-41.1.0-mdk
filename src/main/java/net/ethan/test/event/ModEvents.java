package net.ethan.test.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.ethan.test.Test;
import net.ethan.test.enchantment.ModEnchantments;
import net.ethan.test.enchantment.WingardiumLeviosaEnchantment;
import net.ethan.test.entity.ModEntityTypes;
import net.ethan.test.entity.custom.TlalocEntity;
import net.ethan.test.item.ModItems;
import net.ethan.test.networking.ModMessages;
import net.ethan.test.networking.packet.ThirstDataSyncS2CPacket;
import net.ethan.test.thirst.PlayerThirst;
import net.ethan.test.thirst.PlayerThirstProvider;
import net.ethan.test.villager.ModVillagers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.extensions.IForgeItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.npc.VillagerTrades;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = Test.MOD_ID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event){
            if(event.getType() == VillagerProfession.TOOLSMITH) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.DICE.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        stack,10,8,0.02F));
            };
            if(event.getType() == ModVillagers.FLOAT_MASTER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.BLUEBERRY.get(), 18);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        stack,10,800,0.02F));
            }
            if(event.getType() == ModVillagers.FLOAT_MASTER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.BLUEBERRY_SEEDS.get(), 16);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 1),
                        stack,10,800,0.02F));
            }

        }
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                    event.addCapability(new ResourceLocation(Test.MOD_ID, "properties"), new PlayerThirstProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if(event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PlayerThirst.class);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                        thirst.subThirst(1);
                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), ((ServerPlayer) event.player));
                    }
                });
            }
        }
        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                    });
                }
            }
        }
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event){
            if(event.getSource().getEntity() instanceof  Player player){
                ItemStack stack = ((Player) event.getSource().getEntity()).getItemInHand(InteractionHand.MAIN_HAND);
                Entity entity = event.getEntity();
                if(entity.isAlive()){
                    if (EnchantmentHelper.getEnchantments(stack).equals(ModEnchantments.WINGARDIUM_LEVIOSA_V2.get())){
                        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WINGARDIUM_LEVIOSA_V2.get(), player) == 1){
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10));
                        }
                        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WINGARDIUM_LEVIOSA_V2.get(), player) == 2){
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 1));
                        }
                        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WINGARDIUM_LEVIOSA_V2.get(), player) == 3){
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 2));
                        }
                        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WINGARDIUM_LEVIOSA_V2.get(), player) == 4){
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 3));
                        }
                        if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WINGARDIUM_LEVIOSA_V2.get(), player) == 5){
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 4));
                        }
                    }
                }
            }
            if(event.getEntity() instanceof Sheep){
                if(event.getSource().getEntity() instanceof Player player){
                    if (player.getMainHandItem().getItem() == Items.BEEF){
                        player.sendSystemMessage(Component.literal(player.getName().getString() + " hit a sheep with beef"));
                    } else {
                        player.sendSystemMessage(Component.literal(player.getName().getString() + " is a cruel being"));
                    }
                }
            }
        }
    }
    @Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.TLALOC.get(), TlalocEntity.setAttributes());
        }
    }

}
