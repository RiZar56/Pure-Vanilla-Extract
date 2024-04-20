package net.purevanillaextract.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;

public class PureVanillaExtractBlocks {

    public static final Block WOLF_COCOON = registerBlock("wolf_cocoon",
            new WolfCocoonBlock(FabricBlockSettings.copyOf(Blocks.SNIFFER_EGG)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(PureVanillaExtract.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(PureVanillaExtract.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerPveBlocks() {
        PureVanillaExtract.LOGGER.info("Registering Mod Blocks for " + PureVanillaExtract.MOD_ID);
    }
}
