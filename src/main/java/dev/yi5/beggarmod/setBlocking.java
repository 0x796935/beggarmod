package dev.yi5.beggarmod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class setBlocking extends CommandBase {

    @Override
    public String getCommandName() {
        return "setBlocking";
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
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Usage: /setBlocking").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
        }else{
            if(args[0].equals("true")){
                BeggarMod.blockBeggarMessages = true;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Blocker set to true").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
            }
            else if(args[0].equals("false")){
                BeggarMod.blockBeggarMessages = false;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Blocker set to false").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
            else{
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Usage: /setBlocking [true/false]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
            }
        }
    }
}