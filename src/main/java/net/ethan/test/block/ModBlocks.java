package net.ethan.test.block;

import net.ethan.test.Test;
import net.ethan.test.block.custom.BlueberryCropBlock;
import net.ethan.test.block.custom.FloatyBlock;
import net.ethan.test.block.custom.SinkBlock;
import net.ethan.test.block.custom.SlimeLampBlock;
import net.ethan.test.fluid.ModFluids;
import net.ethan.test.item.ModCreativeModeTab;
import net.ethan.test.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Test.MOD_ID);


    public static final RegistryObject<Block> RHODIUM_BLOCK = registerBlock("rhodium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> RHODIUM_ORE = registerBlock("rhodium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops(),
                    UniformInt.of(100,1000)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> DEEPSLATE_RHODIUM_ORE = registerBlock("deepslate_rhodium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops(),
                    UniformInt.of(100,1000)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> ENDSTONE_RHODIUM_ORE = registerBlock("endstone_rhodium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops(),
                    UniformInt.of(100,1000)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> NETHERRACK_RHODIUM_ORE = registerBlock("netherrack_rhodium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops(),
                    UniformInt.of(100,1000)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> FLOATY_BLOCK = registerBlock("floaty_block",
            () -> new FloatyBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> SLIME_LAMP = registerBlock("slime_lamp",
            () -> new SlimeLampBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(SlimeLampBlock.LIT) ? 15 : 0 )), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> BLUEBERRY_CROP = BLOCKS.register("blueberry_crop",
            () -> new BlueberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<Block> SINK = registerBlock("sink",
            () -> new SinkBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(2f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.TEST_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    };

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
