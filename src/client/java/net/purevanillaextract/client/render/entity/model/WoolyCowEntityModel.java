package net.purevanillaextract.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
@Environment(EnvType.CLIENT)
public class WoolyCowEntityModel<T extends Entity> extends QuadrupedEntityModel<T> {

	public WoolyCowEntityModel(ModelPart root) {
		super(root, false, 10.0F, 4.0F, 2.0F, 2.0F, 24);
	}


	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, new Dilation(0.0F))
		.uv(22, 0).cuboid(4.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 0).cuboid(-5.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -8.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 2.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(19, 5).cuboid(-5.5F, -10.0F, -7.0F, 11.0F, 17.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData fur = body.addChild("fur", ModelPartBuilder.create().uv(0, 33).cuboid(-6.0F, -22.0F, -8.0F, 12.0F, 13.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, -2.0F));

		ModelPartData body_r2 = fur.addChild("body_r2", ModelPartBuilder.create().uv(42, 33).cuboid(-6.0F, -8.0F, -7.0F, 12.0F, 18.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -19.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData right_hind_leg = modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 12.0F, 7.0F));

		ModelPartData left_hind_leg = modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 12.0F, 7.0F));

		ModelPartData right_front_leg = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-1.9F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 12.0F, -6.0F));

		ModelPartData left_front_leg = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.1F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 12.0F, -6.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	public ModelPart getHead() {
		return this.head;
	}

}