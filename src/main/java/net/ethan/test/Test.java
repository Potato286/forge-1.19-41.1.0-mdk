package net.ethan.test;

import com.mojang.logging.LogUtils;
import net.ethan.test.block.ModBlocks;
import net.ethan.test.block.entity.ModBlockEntities;
import net.ethan.test.enchantment.ModEnchantments;
import net.ethan.test.entity.ModEntityTypes;
import net.ethan.test.entity.client.TlalocRenderer;
import net.ethan.test.fluid.ModFluidTypes;
import net.ethan.test.fluid.ModFluids;
import net.ethan.test.item.ModItems;
import net.ethan.test.loot.ModLootModifiers;
import net.ethan.test.networking.ModMessages;
import net.ethan.test.painting.ModPaintings;
import net.ethan.test.recipe.ModRecipes;
import net.ethan.test.screen.ModMenuTypes;
import net.ethan.test.screen.SinkMenu;
import net.ethan.test.screen.SinkScreen;
import net.ethan.test.util.ModTags;
import net.ethan.test.villager.ModVillagers;
import net.ethan.test.world.feature.ModConfiguredFeatures;
import net.ethan.test.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Test.MOD_ID)
public class Test
{
    public static final String MOD_ID = "test";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Test() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);

        ModPaintings.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModEntityTypes.register(modEventBus);
        GeckoLib.initialize();

        ModEnchantments.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.TLALOC.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.LILY.getId(), ModBlocks.POTTED_LILY);

            ModMessages.register();
            ModVillagers.registerPOIs();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());

            MenuScreens.register(ModMenuTypes.SINK_MENU.get(), SinkScreen::new);

            EntityRenderers.register(ModEntityTypes.TLALOC.get(), TlalocRenderer::new);
        }
    }
}
