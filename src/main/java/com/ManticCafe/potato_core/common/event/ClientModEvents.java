package com.ManticCafe.potato_core.common.event;

import com.ManticCafe.potato_core.main;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import com.ManticCafe.potato_core.common.entity.renderer.DiamondProjectileRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(entityhandler.DIAMOND_PROJECTILE.get(), DiamondProjectileRenderer::new);
    }
}