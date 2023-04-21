package net.ethan.test.entity;

import net.ethan.test.Test;
import net.ethan.test.entity.custom.TlalocEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Test.MOD_ID);

    public static final RegistryObject<EntityType<TlalocEntity>> TLALOC = ENTITY_TYPES.register("tlaloc", () ->
            EntityType.Builder.of(TlalocEntity::new, MobCategory.MONSTER).sized(0.9f, 3.0f)
                    .build(new ResourceLocation(Test.MOD_ID, "tlaloc").toString()));
    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
