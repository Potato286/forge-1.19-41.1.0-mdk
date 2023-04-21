package net.ethan.test.item;

import net.ethan.test.Test;
import net.ethan.test.block.ModBlocks;
import net.ethan.test.entity.ModEntityTypes;
import net.ethan.test.fluid.ModFluids;
import net.ethan.test.item.custom.DiceItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Test.MOD_ID);

    public static final RegistryObject<Item> RHODIUM = ITEMS.register("rhodium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> RAW_RHODIUM = ITEMS.register("raw_rhodium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> DICE = ITEMS.register("dice",
            () -> new DiceItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(2f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 1), 1.0f)
                    .effect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 6000, 1), 1.0f)
                    .effect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 6000, 2), 1.0f)
                    .build()).tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER,
                    new  Item.Properties().stacksTo(1).tab(ModCreativeModeTab.TEST_TAB).craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> SCYTHE = ITEMS.register("scythe",
            () -> new HoeItem(ModToolTiers.RHODIUM, 5, -2.4f,
                    new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> TLALOC_SPAWN_EGG = ITEMS.register("tlaloc_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.TLALOC, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> PICKAXE = ITEMS.register("pickaxe",
            () -> new PickaxeItem(ModToolTiers.RHODIUM,1, -2.8f,
                    new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.TEST_TAB)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
