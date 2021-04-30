package com.nopoisonregen.main;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Main.MOD_ID)
public class Main {
   public static final String MOD_ID = "nopoisonregen";

   public Main() {
      MinecraftForge.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void playerTickEvent(PlayerTickEvent event) {
      PlayerEntity player = event.player;
      if (player.hasEffect(Effects.REGENERATION) && player.hasEffect(Effects.POISON)) {
         int regenTime = player.getEffect(Effects.REGENERATION).getDuration();
         int poisonTime = player.getEffect(Effects.POISON).getDuration();
         int remainingTime = regenTime - poisonTime;
         if (remainingTime < 0) {
            player.removeEffectNoUpdate(Effects.POISON);
            player.removeEffectNoUpdate(Effects.REGENERATION);
            player.addEffect(new EffectInstance(Effects.POISON, -remainingTime));
         } else if (remainingTime > 0) {
            player.removeEffectNoUpdate(Effects.POISON);
            player.removeEffectNoUpdate(Effects.REGENERATION);
            player.addEffect(new EffectInstance(Effects.REGENERATION, remainingTime));
         } else {
            player.removeEffectNoUpdate(Effects.POISON);
            player.removeEffectNoUpdate(Effects.REGENERATION);
         }
      }
   }
}
