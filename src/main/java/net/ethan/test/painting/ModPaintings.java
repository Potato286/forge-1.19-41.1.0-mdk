package net.ethan.test.painting;

import net.ethan.test.Test;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.time.temporal.TemporalQueries;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Test.MOD_ID);

    public static final RegistryObject<PaintingVariant> CHAD = PAINTING_VARIANTS.register("chad",
            () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> RICK = PAINTING_VARIANTS.register("rick",
            () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> POTATO = PAINTING_VARIANTS.register("potato_painting",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> PLANT = PAINTING_VARIANTS.register("plant",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> WANDERER = PAINTING_VARIANTS.register("wanderer",
            () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> SUNSET = PAINTING_VARIANTS.register("sunset",
            () -> new PaintingVariant(32, 16));



    public static void register(IEventBus eventBus){PAINTING_VARIANTS.register(eventBus);}
}