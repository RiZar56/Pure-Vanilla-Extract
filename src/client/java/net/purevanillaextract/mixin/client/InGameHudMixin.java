package net.purevanillaextract.mixin.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.entity.effect.PureVanillaExtractStatusEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
abstract class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;
    private static final Identifier INSOMNIA_OVERLAY = new Identifier(PureVanillaExtract.MOD_ID, "textures/misc/insomnia.png");
    private int scaledWidth;
    private int scaledHeight;


    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(DrawContext context, float tickDelta, CallbackInfo ci){
        assert this.client.player != null;
        if(this.client.player.hasStatusEffect(PureVanillaExtractStatusEffects.INSOMNIA)){
            this.renderThisOverlay(context, INSOMNIA_OVERLAY, 0.05F);
        }
    }

    private void renderThisOverlay(DrawContext context, Identifier texture, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        context.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        context.drawTexture(texture, 0, 0, -90, 0.0F, 0.0F, this.scaledWidth, this.scaledHeight, this.scaledWidth, this.scaledHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
