package net.purevanillaextract.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.purevanillaextract.entity.PureVanillaExtractEntities;
import net.purevanillaextract.item.PureVanillaExtractItems;
import org.jetbrains.annotations.Nullable;

public class CluckshroomEntity extends ChickenEntity implements Shearable, VariantHolder<CluckshroomEntity.Type> {
    private static final TrackedData<String> TYPE;
    private float field_28639 = 1.0F;

    public CluckshroomEntity(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }

    public void tickMovement() {
        super.tickMovement();
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (this.isOnGround() ? -1.0F : 4.0F) * 0.3F;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (!this.isOnGround() && this.flapSpeed < 1.0F) {
            this.flapSpeed = 1.0F;
        }

        this.flapSpeed *= 0.9F;
        Vec3d vec3d = this.getVelocity();
        if (!this.isOnGround() && vec3d.y < 0.0D) {
            this.setVelocity(vec3d.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flapProgress += this.flapSpeed * 2.0F;
        if (!this.getWorld().isClient && this.isAlive() && !this.isBaby() && !this.hasJockey() && --this.eggLayTime <= 0) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(PureVanillaExtractItems.BLUEFISH);
            this.emitGameEvent(GameEvent.ENTITY_PLACE);
            this.eggLayTime = this.random.nextInt(6000) + 6000;
        }

    }

    @Nullable
    public CluckshroomEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (CluckshroomEntity) PureVanillaExtractEntities.CLUCKSHROOM.create(serverWorld);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TYPE, CluckshroomEntity.Type.RED.name);
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos.down()).isOf(Blocks.MYCELIUM) ? 10.0F : world.getPhototaxisFavor(pos);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient) {
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
            }

            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.getWorld().isClient()) {
            ChickenEntity chickenEntity = (ChickenEntity) EntityType.CHICKEN.create(this.getWorld());
            if (chickenEntity != null) {
                ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                this.discard();
                chickenEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
                chickenEntity.setHealth(this.getHealth());
                chickenEntity.bodyYaw = this.bodyYaw;
                if (this.hasCustomName()) {
                    chickenEntity.setCustomName(this.getCustomName());
                    chickenEntity.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistent()) {
                    chickenEntity.setPersistent();
                }

                chickenEntity.setInvulnerable(this.isInvulnerable());
                this.getWorld().spawnEntity(chickenEntity);

                for(int i = 0; i < 3; ++i) {
                    this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getBodyY(1.0D), this.getZ(), new ItemStack(this.getVariant().mushroom.getBlock())));
                }
            }
        }
    }

    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void setVariant(CluckshroomEntity.Type type) {
        this.dataTracker.set(TYPE, type.name);
    }

    static {
        TYPE = DataTracker.registerData(CluckshroomEntity.class, TrackedDataHandlerRegistry.STRING);
    }

    protected boolean isFlappingWings() {
        return this.speed > this.field_28639;
    }

    protected void addFlapEffects() {
        this.field_28639 = this.speed + this.maxWingDeviation / 2.0F;
    }


    @Override
    public CluckshroomEntity.Type getVariant() {
        return CluckshroomEntity.Type.fromName((String)this.dataTracker.get(TYPE));
    }

    public static enum Type implements StringIdentifiable {
        RED("red", Blocks.RED_MUSHROOM.getDefaultState()),
        BROWN("brown", Blocks.BROWN_MUSHROOM.getDefaultState());

        public static final EnumCodec<CluckshroomEntity.Type> CODEC = StringIdentifiable.createCodec(CluckshroomEntity.Type::values);
        final String name;
        final BlockState mushroom;

        private Type(String name, BlockState mushroom) {
            this.name = name;
            this.mushroom = mushroom;
        }

        public BlockState getCluckshroomState() {
            return this.mushroom;
        }

        public String asString() {
            return this.name;
        }

        static CluckshroomEntity.Type fromName(String name) {
            return (CluckshroomEntity.Type)CODEC.byId(name, RED);
        }
    }
}
