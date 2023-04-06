package net.ethan.test.villager;

import com.google.common.collect.ImmutableSet;
import net.ethan.test.Test;
import net.ethan.test.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.concurrent.Immutable;
import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, Test.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Test.MOD_ID);

    public static final RegistryObject<PoiType> FLOATY_BLOCK_POI = POI_TYPES.register("floaty_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.FLOATY_BLOCK.get().getStateDefinition().getPossibleStates()),
            1, 1));

    public static final RegistryObject<VillagerProfession> FLOAT_MASTER = VILLAGER_PROFESSIONS.register("float_master",
            () -> new VillagerProfession("float_master", x -> x.get() == FLOATY_BLOCK_POI.get(),
                    x -> x.get() == FLOATY_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, FLOATY_BLOCK_POI.get());
        }catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
