package dev.yi5.beggarmod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class setLogging extends CommandBase {

    @Override
    public String getCommandName() {
        return "setLogging";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void  processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Usage: /setLogger [true/false]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
        }else{
            if(args[0].equals("true")){
                BeggarMod.logBeggarMessages = true;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Logger set to true").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
            }
            else if(args[0].equals("false")){
                BeggarMod.logBeggarMessages = false;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Logger set to false").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
            else{
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Usage: /setLogger [true/false]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
            }
        }
    }
}