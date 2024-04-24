package net.purevanillaextract.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.purevanillaextract.PureVanillaExtract;

public class PureVanillaExtractBlocks {

    public static final Block WOLF_COCOON = registerBlock("wolf_cocoon",
            new WolfCocoonBlock(FabricBlockSettings.copyOf(Blocks.SNIFFER_EGG)));

    public static final Block SALT = registerBlock("salt",
            new RedstoneWireBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).instrument(Instrument.BIT).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)));

    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(3.0f, 3.0f)));


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
