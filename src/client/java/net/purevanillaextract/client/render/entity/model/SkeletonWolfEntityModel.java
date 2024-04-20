package net.purevanillaextract.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;

@Environment(EnvType.CLIENT)
public class SkeletonWolfEntityModel<T extends WolfEntity>  extends WolfEntityModel {
    public SkeletonWolfEntityModel(ModelPart root) {
        super(root);
    }
}
