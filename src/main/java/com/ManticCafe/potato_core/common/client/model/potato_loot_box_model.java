package com.ManticCafe.potato_core.common.client.model;

import com.ManticCafe.potato_core.common.entity.lootBox.potato_loot_box;
import com.ManticCafe.potato_core.main;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class potato_loot_box_model extends GeoModel<potato_loot_box> {

    @Override
    public ResourceLocation getModelResource(potato_loot_box animatable) {
        return new ResourceLocation(main.MODID,"geo/potato_loot_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(potato_loot_box animatable) {
        return new ResourceLocation(main.MODID,"textures/entity/potato_loot_box.png");
    }

    @Override
    public ResourceLocation getAnimationResource(potato_loot_box animatable) {
        return new ResourceLocation(main.MODID,"animations/potato_loot_box.animation.json");
    }

}
