package dev.yi5.beggarmod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class UpdateList extends CommandBase {

    @Override
    public String getCommandName() {
        return "updatelist";
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
        // download https://raw.githubusercontent.com/LagKnowsWhy/begging-list/master/usernames.txt
        // and save it to the beggar mod folder
        try{
            // create path
            String path = Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "\\config\\beggarmod";
            new java.io.File(path).mkdirs();
            //Minecraft.getMinecraft().thePlayer.sendChatMessage("Downloading list to " + path);
            JavaDownloadFileFromURL.downloadUsingNIO("https://raw.githubusercontent.com/LagKnowsWhy/begging-list/master/usernames.txt", path+"\\usernames.txt");
        }catch (Exception e){
            // print Exception to chat
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(e.toString());
            // IChatComponent
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Error downloading list.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("List successfully updated").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
    }
}