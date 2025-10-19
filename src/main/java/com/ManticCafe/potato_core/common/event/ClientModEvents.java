package com.ManticCafe.potato_core.common.event;

import com.ManticCafe.potato_core.common.entity.entities.PotatoProjectileEntity;
import com.ManticCafe.potato_core.common.entity.renderer.PotatoProjectileRenderer;
import com.ManticCafe.potato_core.main;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import com.ManticCafe.potato_core.common.entity.renderer.DiamondProjectileRenderer;
import com.ManticCafe.potato_core.common.entity.renderer.itemBaseBlockEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(entityhandler.DIAMOND_PROJECTILE.get(), DiamondProjectileRenderer::new);
        event.registerEntityRenderer(entityhandler.POTATO_PROJECTILE.get(), PotatoProjectileRenderer::new);

        event.registerBlockEntityRenderer(entityhandler.ITEM_BASE_BLOCK_ENTITY.get(), itemBaseBlockEntityRenderer::new);
    }
}