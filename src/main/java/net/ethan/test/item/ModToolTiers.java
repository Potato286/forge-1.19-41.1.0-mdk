package net.ethan.test.item;

import net.ethan.test.Test;
import net.ethan.test.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static Tier RHODIUM;

    static {
        RHODIUM = TierSortingRegistry.registerTier(new ForgeTier(5, 2531, 39.0f, 5.0f, 300
        , ModTags.Blocks.NEEDS_RHODIUM_TOOL, () -> Ingredient.of(ModItems.RHODIUM.get())),
                new ResourceLocation(Test.MOD_ID, "rhodium"), List.of(Tiers.NETHERITE), List.of());
    }
}
