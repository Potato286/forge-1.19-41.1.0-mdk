package net.ethan.test.entity.client;

import net.ethan.test.Test;
import net.ethan.test.entity.custom.TlalocEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TlalocModel extends AnimatedGeoModel<TlalocEntity> {
    @Override
    public ResourceLocation getModelResource(TlalocEntity object) {
        return new ResourceLocation(Test.MOD_ID, "geo/tlaloc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TlalocEntity object) {
        return new ResourceLocation(Test.MOD_ID, "textures/entity/tlaloc.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TlalocEntity animatable) {
        return new ResourceLocation(Test.MOD_ID, "animations/tlaloc.animation.json");
    }
}
