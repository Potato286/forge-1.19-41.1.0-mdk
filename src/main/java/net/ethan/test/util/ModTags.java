package net.ethan.test.util;

import net.ethan.test.Test;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_RHODIUM_TOOL
                = tag("needs_rhodium_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Test.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
