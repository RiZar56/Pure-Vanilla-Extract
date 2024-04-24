package net.purevanillaextract.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.client.render.entity.model.BluefishEntityModel;
import net.purevanillaextract.client.render.entity.model.PureVanillaExtractModelLayers;
import net.purevanillaextract.entity.passive.BluefishEntity;

@Environment(EnvType.CLIENT)
public class BluefishEntityRenderer extends MobEntityRenderer<BluefishEntity, BluefishEntityModel<BluefishEntity>> {
private static final Identifier TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/bluefish.png");

        public BluefishEntityRenderer(EntityRendererFactory.Context context) {
                super(context, new BluefishEntityModel<>(context.getPart(PureVanillaExtractModelLayers.BLUEFISH)), 0.3f);
        }

        public Identifier getTexture(BluefishEntity bluefishEntity) {
                return TEXTURE;
                }

        protected void setupTransforms(BluefishEntity bluefishEntity, MatrixStack matrixStack, float f, float g, float h) {
                super.setupTransforms(bluefishEntity, matrixStack, f, g, h);
                float i = 4.3F * MathHelper.sin(0.6F * f);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(i));
                if (!bluefishEntity.isTouchingWater()) {
                matrixStack.translate(0.1F, 0.1F, -0.1F);
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
                }
        }
}