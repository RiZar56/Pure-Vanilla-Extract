package net.purevanillaextract.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SnifferEggBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.purevanillaextract.entity.PureVanillaExtractEntities;
import net.purevanillaextract.entity.passive.SkeletonWolfEntity;


public class WolfCocoonBlock
extends SnifferEggBlock {

    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private boolean isReadyToBirth(BlockState state) {
        return this.getHatchStage(state) == 2;
    }


    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.isReadyToBirth(state)) {
            world.playSound(null, pos, SoundEvents.BLOCK_SNIFFER_EGG_CRACK, SoundCategory.BLOCKS, 0.7f, 0.9f + random.nextFloat() * 0.2f);
            world.setBlockState(pos, (BlockState) state.with(HATCH, this.getHatchStage(state) + 1), Block.NOTIFY_LISTENERS);
            return;
        }
        world.playSound(null, pos, SoundEvents.BLOCK_SNIFFER_EGG_HATCH, SoundCategory.BLOCKS, 0.7f, 0.9f + random.nextFloat() * 0.2f);
        world.breakBlock(pos, false);
        SkeletonWolfEntity skeletonWolfEntity = PureVanillaExtractEntities.SKELETON_WOLF.create(world);
        if (skeletonWolfEntity != null) {
            Vec3d vec3d = pos.toCenterPos();
            skeletonWolfEntity.refreshPositionAndAngles(vec3d.getX(), vec3d.getY(), vec3d.getZ(), MathHelper.wrapDegrees(world.random.nextFloat() * 360.0f), 0.0f);
            world.spawnEntity(skeletonWolfEntity);
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean bl = WolfCocoonBlock.isUnderHatchBooster(world, pos);
        if (!world.isClient() && bl) {
            world.syncWorldEvent(WorldEvents.SNIFFER_EGG_CRACKS, pos, 0);
        }
        int i = bl ? 12000 : 24000;
        int j = i / 3;
        world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(state));
        world.scheduleBlockTick(pos, this, j + world.random.nextInt(300));
    }

    public static boolean isUnderHatchBooster(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isIn(BlockTags.SOUL_SPEED_BLOCKS);
    }

    public WolfCocoonBlock(Settings settings) {
        super(settings);
    }
}
