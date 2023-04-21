package net.ethan.test.world.feature;

import net.ethan.test.Test;
import net.ethan.test.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Test.MOD_ID);

    public static final RegistryObject<PlacedFeature> RHODIUM_ORE_PLACED = PLACED_FEATURES.register("rhodium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.RHODIUM_ORE.getHolder().get(),
                    commonOrePlacement(7, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> END_RHODIUM_ORE_PLACED = PLACED_FEATURES.register("end_rhodium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ENDSTONE_RHODIUM_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> NETHER_RHODIUM_ORE_PLACED = PLACED_FEATURES.register("nether_rhodium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHERRACK_RHODIUM_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> WISTERIA_CHECKED = PLACED_FEATURES.register("wisteria_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.WISTERIA.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.WISTERIA_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> WISTERIA_PLACED = PLACED_FEATURES.register("wisteria_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.WISTERIA.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> RHODIUM_GEODE_PLACED = PLACED_FEATURES.register("rhodium_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.RHODIUM_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(40), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> LILY_PLACED = PLACED_FEATURES.register("lily_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.LILY.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(8),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));


    public static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
        return List.of(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(i), modifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(i), modifier);
    }


    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);}
}
