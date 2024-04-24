package net.purevanillaextract.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.purevanillaextract.item.PureVanillaExtractItems;

import java.util.Properties;

public class MudFluid extends AbstractFluid {
    @Override
    public Fluid getFlowing() {
        return PureVanillaExtractFluids.FLOWING_MUD;
    }

    @Override
    public Fluid getStill() {
        return PureVanillaExtractFluids.STILL_MUD;
    }

    @Override
    public Item getBucketItem() {
        return PureVanillaExtractItems.MUD_BUCKET;
    }

    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return PureVanillaExtractFluids.MUD_FLUID.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(fluidState));
    }

    @Override
    public boolean isStill(FluidState fluidState) {
        return false;
    }

    @Override
    public int getLevel(FluidState fluidState) {
        return fluidState.get(LEVEL);
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 30;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView worldView) {
        return 2;
    }

    @Override
    protected int getFlowSpeed(WorldView worldView) {
        return 2;
    }

    public static class Flowing extends MudFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }
    public static class Still extends MudFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
