package com.ManticCafe.potato_core.common.entity.renderer;

import com.ManticCafe.potato_core.common.entity.entities.DiamondProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class DiamondProjectileRenderer extends ThrownItemRenderer<DiamondProjectileEntity> {

    public DiamondProjectileRenderer(EntityRendererProvider.Context context) {
        super(context, 1.0F, true);
    }
}