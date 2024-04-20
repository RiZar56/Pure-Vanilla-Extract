package net.purevanillaextract.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CluckshroomEntityModel <T extends Entity> extends ChickenEntityModel<T> {


	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart beak;
	private final ModelPart wattle;

	public CluckshroomEntityModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.beak = root.getChild("beak");
		this.wattle = root.getChild("red_thing");
		this.body = root.getChild("body");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
		this.rightWing = root.getChild("right_wing");
		this.leftWing = root.getChild("left_wing");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(38, 11).cuboid(-2.0F, -11.0F, 0.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		ModelPartData bone = head.addChild("bone", ModelPartBuilder.create().uv(38, 11).cuboid(-2.5F, -11.0F, 0.5F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		ModelPartData beak = modelPartData.addChild("beak", ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		ModelPartData wattle = modelPartData.addChild("red_thing", ModelPartBuilder.create().uv(14, 4).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		ModelPartData bone2 = body.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 11.0F, 5.0F));
		ModelPartData head_r1 = bone2.addChild("head_r1", ModelPartBuilder.create().uv(38, 18).cuboid(-2.5F, -2.0F, -10.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		ModelPartData bone3 = body.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 11.0F, 5.0F));
		ModelPartData head_r2 = bone3.addChild("head_r2", ModelPartBuilder.create().uv(38, 18).cuboid(5.5F, -7.0F, -1.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, -5.0F, -1.5708F, 0.0F, -1.5708F));
		ModelPartData left_wing = modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(24, 13).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 13.0F, 0.0F));
		ModelPartData right_wing = modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(24, 13).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 13.0F, 0.0F));
		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 19.0F, 1.0F));
		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 19.0F, 1.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}


	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head, this.beak, this.wattle);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
	}

	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;
		this.beak.pitch = this.head.pitch;
		this.beak.yaw = this.head.yaw;
		this.wattle.pitch = this.head.pitch;
		this.wattle.yaw = this.head.yaw;
		this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.rightWing.roll = animationProgress;
		this.leftWing.roll = -animationProgress;
	}
}