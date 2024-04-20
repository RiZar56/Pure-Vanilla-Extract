package net.purevanillaextract;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.purevanillaextract.client.render.entity.model.*;
import net.purevanillaextract.entity.PureVanillaExtractEntities;

import static net.purevanillaextract.PureVanillaExtract.MOD_ID;

@Environment(EnvType.CLIENT)
public class PureVanillaExtractClient implements ClientModInitializer {

	public static final EntityModelLayer SKELETON_WOLF = new EntityModelLayer(new Identifier(MOD_ID, "skeleton_wolf"), "main");
	public static final EntityModelLayer BLUEFISH = new EntityModelLayer(new Identifier(MOD_ID, "bluefish"), "main");
	public static final EntityModelLayer WOOLY_COW = new EntityModelLayer(new Identifier(MOD_ID, "wooly_cow"), "main");
	private static final EntityModelLayer CLUCKSHROOM = new EntityModelLayer(new Identifier(MOD_ID, "cluckshroom"), "main");


	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.


		EntityRendererRegistry.register(PureVanillaExtractEntities.SKELETON_WOLF, SkeletonWolfEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(SKELETON_WOLF, SkeletonWolfEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(PureVanillaExtractEntities.BLUEFISH, BluefishEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(BLUEFISH, BluefishEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(PureVanillaExtractEntities.WOOLY_COW, WoolyCowEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(WOOLY_COW, WoolyCowEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(PureVanillaExtractEntities.CLUCKSHROOM, CluckshroomEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(CLUCKSHROOM, CluckshroomEntityModel::getTexturedModelData);
	}
}