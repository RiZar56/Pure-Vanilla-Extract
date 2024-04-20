package net.purevanillaextract.client.render.entity.model;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;

public class SkeletonWolfEntityRenderer extends WolfEntityRenderer {
    private static final Identifier WILD_TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/skeleton_wolf/skeleton_wolf.png");
    private static final Identifier TAMED_TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/skeleton_wolf/skeleton_wolf_tame.png");
    private static final Identifier ANGRY_TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/skeleton_wolf/skeleton_wolf_angry.png");

    public Identifier getTexture(WolfEntity wolfEntity) {
        if (wolfEntity.isTamed()) {
            return TAMED_TEXTURE;
        } else {
            return wolfEntity.hasAngerTime() ? ANGRY_TEXTURE : WILD_TEXTURE;
        }
    }

    public SkeletonWolfEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }
}
