package net.purevanillaextract.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.purevanillaextract.entity.PureVanillaExtractEntities;
import net.purevanillaextract.item.PureVanillaExtractItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WoolyCowEntity extends CowEntity implements Shearable {
    private static final TrackedData<Byte> SHEARED;
    public WoolyCowEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHEARED, (byte)0);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHEARS)) {
            if (!this.getWorld().isClient && this.isShearable()) {
                this.sheared(SoundCategory.PLAYERS);
                this.emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(PureVanillaExtractItems.YARN);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
            }
        }

    }

    public boolean isSheared() {
        return ((Byte)this.dataTracker.get(SHEARED) & 4) != 0;
    }

    private void setSheared(boolean sheared) {
        byte b = (Byte)this.dataTracker.get(SHEARED);
        if (sheared){
            this.dataTracker.set(SHEARED, (byte)(b | 4));
        } else {
            this.dataTracker.set(SHEARED, (byte)(b & -5));
        }
    }

    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    public void onEatingGrass() {
        super.onEatingGrass();
        this.setSheared(false);
        if (this.isBaby()) {
            this.growUp(60);
        }

    }

    @Nullable
    public WoolyCowEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (WoolyCowEntity) PureVanillaExtractEntities.WOOLY_COW.create(serverWorld);
    }

    static {
        SHEARED = DataTracker.registerData(WoolyCowEntity.class, TrackedDataHandlerRegistry.BYTE);
    }
}
