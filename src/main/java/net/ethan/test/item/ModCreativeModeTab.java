package net.ethan.test.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TEST_TAB = new CreativeModeTab("testingtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RHODIUM.get());
        }
    };
}
