package net.ethan.test.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ethan.test.Test;
import net.ethan.test.entity.custom.TlalocEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TlalocRenderer extends GeoEntityRenderer<TlalocEntity> {
    public TlalocRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TlalocModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public ResourceLocation getTextureLocation(TlalocEntity animatable) {
        return new ResourceLocation(Test.MOD_ID, "textures/entity/tlaloc.png");
    }

    @Override
    public RenderType getRenderType(TlalocEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1, 1, 1);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
