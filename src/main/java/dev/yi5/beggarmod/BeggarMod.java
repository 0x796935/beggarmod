package dev.yi5.beggarmod;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = BeggarMod.MODID, version = BeggarMod.VERSION)
public class BeggarMod
{
    public static final String MODID = "beggar-mod";
    public static final String VERSION = "1.0";

    public static boolean logBeggarMessages = true;
    public static boolean blockBeggarMessages= true;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println(MODID+" initializing.");
        MinecraftForge.EVENT_BUS.register(new Events());
        ClientCommandHandler.instance.registerCommand(new UpdateList());
        ClientCommandHandler.instance.registerCommand(new setLogging());
        ClientCommandHandler.instance.registerCommand(new setBlocking());
    }
}
