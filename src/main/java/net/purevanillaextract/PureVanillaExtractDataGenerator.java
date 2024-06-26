package net.purevanillaextract;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.Main;
import net.minecraft.data.server.tag.vanilla.VanillaCatVariantTagProvider;
import net.purevanillaextract.datagen.PureVanillaExtractCatVariantTagProvider;
import net.purevanillaextract.datagen.PureVanillaExtractEntityTypeTagGenerator;

public class PureVanillaExtractDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		fabricDataGenerator.createPack().addProvider(PureVanillaExtractEntityTypeTagGenerator::new);
		fabricDataGenerator.createPack().addProvider(PureVanillaExtractCatVariantTagProvider::new);
	}
}
