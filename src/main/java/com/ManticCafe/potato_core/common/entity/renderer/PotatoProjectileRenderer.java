package com.ManticCafe.potato_core.common.entity.renderer;

import com.ManticCafe.potato_core.common.entity.entities.DiamondProjectileEntity;
import com.ManticCafe.potato_core.common.entity.entities.PotatoProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class PotatoProjectileRenderer extends ThrownItemRenderer<PotatoProjectileEntity> {

    public PotatoProjectileRenderer(EntityRendererProvider.Context context) {
        super(context, 1.0F, true);
    }
}