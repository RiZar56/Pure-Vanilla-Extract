package net.purevanillaextract.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.entity.passive.WoolyCowEntity;


@Environment(EnvType.CLIENT)
public class WoolyCowEntityRenderer extends MobEntityRenderer<WoolyCowEntity, WoolyCowEntityModel<WoolyCowEntity>> {
    public static final Identifier WOOLY_TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/wooly_cow/wooly_cow.png" );
    public static final Identifier SHORN_TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/wooly_cow/wooly_cow_shorn.png" );


    public WoolyCowEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WoolyCowEntityModel<>(context.getPart(PureVanillaExtractModelLayers.WOOLY_COW)), 0.7f);
    }


    public Identifier getTexture(WoolyCowEntity woolyCowEntity) {
        if (woolyCowEntity.isSheared()){
            return SHORN_TEXTURE;
        } else {
            return WOOLY_TEXTURE;
        }
    }

}
