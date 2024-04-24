package net.purevanillaextract.fluid;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;

public class PureVanillaExtractFluids {

    public static FlowableFluid STILL_MUD = registerFluid("still_mud", new MudFluid.Still());
    public static FlowableFluid FLOWING_MUD = registerFluid("mud_flowing", new MudFluid.Flowing());

    public static final Block MUD_FLUID = registerBlock("mud_fluid", new FluidBlock(STILL_MUD, FabricBlockSettings.copy(Blocks.WATER)));


    private static FlowableFluid registerFluid(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, new Identifier(PureVanillaExtract.MOD_ID, name), flowableFluid);
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(PureVanillaExtract.MOD_ID, name), block);
    }

    public static void registerPveFluids() {
        PureVanillaExtract.LOGGER.info("Registering Mod Fluids for " + PureVanillaExtract.MOD_ID);
    }
}
