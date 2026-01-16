package com.ManticCafe.potato_core.common.entity.renderer;

import com.ManticCafe.potato_core.common.client.model.potato_loot_box_model;
import com.ManticCafe.potato_core.common.entity.lootBox.potato_loot_box;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class potatoLootBoxRenderer extends GeoEntityRenderer<potato_loot_box> {
    public potatoLootBoxRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new potato_loot_box_model());
    }
}
