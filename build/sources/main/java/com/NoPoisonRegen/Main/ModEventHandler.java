package com.NoPoisonRegen.Main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created on 21/03/2019.
 */
public class ModEventHandler {
    private int regenTime = 0;
    private int poisonTime = 0;
    private int remainingTime = 0;


    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player.isPotionActive(MobEffects.REGENERATION) && player.isPotionActive(MobEffects.POISON)) {
            // if(player.getActivePotionEffect(MobEffects.REGENERATION).getDuration() != 0){
            regenTime = player.getActivePotionEffect(MobEffects.REGENERATION).getDuration();
            poisonTime = player.getActivePotionEffect(MobEffects.POISON).getDuration();

            remainingTime = regenTime - poisonTime;
            if (remainingTime < 0) {
                player.removeActivePotionEffect(MobEffects.POISON);
                player.removeActivePotionEffect(MobEffects.REGENERATION);
                player.addPotionEffect(new PotionEffect(MobEffects.POISON, -remainingTime));
                remainingTime = 0;

            } else if (remainingTime > 0) {
                player.removeActivePotionEffect(MobEffects.POISON);
                player.removeActivePotionEffect(MobEffects.REGENERATION);
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, remainingTime));
                remainingTime = 0;
            } else {
                player.removeActivePotionEffect(MobEffects.POISON);
                player.removeActivePotionEffect(MobEffects.REGENERATION);
                remainingTime = 0;
            }

        }
    }
}

