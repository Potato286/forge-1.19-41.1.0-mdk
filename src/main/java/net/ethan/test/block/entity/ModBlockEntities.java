package net.ethan.test.block.entity;

import net.ethan.test.Test;
import net.ethan.test.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Test.MOD_ID);

    public static final RegistryObject<BlockEntityType<SinkBlockEntity>> SINK = BLOCK_ENTITIES.register("sink", () ->
            BlockEntityType.Builder.of(SinkBlockEntity::new, ModBlocks.SINK.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
