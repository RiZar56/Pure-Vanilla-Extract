package net.purevanillaextract.client.render.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.purevanillaextract.entity.passive.BluefishEntity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BluefishEntityModel <T extends BluefishEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	//private final ModelPart right_fin;
	//private final ModelPart left_fin;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart fin_left;
	private final ModelPart fin_right;
	private final ModelPart fin_back;
	private final ModelPart fin_front;
	private final ModelPart tail;
	public BluefishEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.nose = root.getChild("nose");
		this.fin_left = root.getChild("fin_left");
		this.fin_right = root.getChild("fin_right");
		this.fin_back = root.getChild("fin_back");
		this.fin_front = root.getChild("fin_front");
		this.tail = root.getChild("tail");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -2.0F, 2.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

		ModelPartData right_fin = body.addChild("right_fin", ModelPartBuilder.create().uv(6, 14).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData left_fin = body.addChild("left_fin", ModelPartBuilder.create().uv(11, 7).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(13, 0).cuboid(-1.0F, -2.0F, -5.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

		ModelPartData nose = modelPartData.addChild("nose", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, -3.0F));

		ModelPartData fin_left = modelPartData.addChild("fin_left", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData fin_right = modelPartData.addChild("fin_right", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData fin_back = modelPartData.addChild("fin_back", ModelPartBuilder.create().uv(0, 10).cuboid(0.0F, -3.0F, 1.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		ModelPartData fin_front = modelPartData.addChild("fin_front", ModelPartBuilder.create().uv(0, 8).cuboid(0.0F, -3.0F, -1.0F, 0.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 27.0F, 0.0F));

		ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 7.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		nose.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		fin_left.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		fin_right.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		fin_back.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		fin_front.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		tail.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return null;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}