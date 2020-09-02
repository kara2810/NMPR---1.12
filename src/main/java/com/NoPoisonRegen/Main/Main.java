package com.NoPoisonRegen.Main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "nopoisonregen";
    public static final String NAME = "No Poison with Regeneration";
    public static final String VERSION = "1.0";



    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code

    }
}
