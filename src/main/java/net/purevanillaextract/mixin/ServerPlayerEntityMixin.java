package net.purevanillaextract.mixin;


import com.mojang.datafixers.util.Either;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.purevanillaextract.entity.effect.PureVanillaExtractStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
abstract class ServerPlayerEntityMixin {
    @Inject(method = "trySleep", at = @At("HEAD"), cancellable = true)
    private void onTrySleep(BlockPos pos, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> callbackInfo) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // Check if player has insomnia
        if (player.hasStatusEffect(PureVanillaExtractStatusEffects.INSOMNIA)) {
            callbackInfo.setReturnValue(Either.left(PlayerEntity.SleepFailureReason.OTHER_PROBLEM));
            //Casting to SleepFailureReason dunno why check InsomniaSleepFailure in Enums ig fix later temp fix vvv
            player.sendMessage(Text.translatable("block.purevanillaextract.bed.insomnia"), true);
        }
    }
}
