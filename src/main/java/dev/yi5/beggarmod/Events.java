package dev.yi5.beggarmod;

import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class Events {

    private long fileSize = 0;
    String[] usernames = new String[0];
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) throws IOException {
        String path = Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "\\config\\beggarmod\\usernames.txt";
        if(!new java.io.File(path).exists())return;


        if(fileSize != new java.io.File(path).getTotalSpace()){ // if filesize is not the old one Load the list
            fileSize = new java.io.File(path).getTotalSpace();
            // check if user is connected to hypixel.net
            try{
                usernames = new java.util.Scanner(new java.io.File(path)).useDelimiter("\\Z").next().split("\n");
                Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText("Loaded " + usernames.length + ", file size is " + fileSize).setChatStyle(new net.minecraft.util.ChatStyle().setColor(net.minecraft.util.EnumChatFormatting.GREEN)));
                // show first 5 usernames
                for(int i = 0; i < 5; i++){
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText(usernames[i]).setChatStyle(new net.minecraft.util.ChatStyle().setColor(net.minecraft.util.EnumChatFormatting.GREEN)));
                }
            }catch (Exception e){
                Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText("Error reading list.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        }


        event.message.getSiblings().forEach((sibling) -> {
            if (sibling.getChatStyle().getChatClickEvent() != null) {
                if (sibling.getChatStyle().getChatClickEvent().getValue().contains("/viewprofile")) {
                    String username = sibling.getChatStyle().getChatClickEvent().getValue().replace("/viewprofile ", "");
                    // check if username is in list

                    // return if username does not match the username regex for minecraft
                    if (!username.matches("^[a-zA-Z0-9_]{2,16}$")) return;

                    for (String user : usernames) {
                        if (user.toLowerCase().equals(username.toLowerCase())) {
                            // beggar got found in chat
                            if (BeggarMod.blockBeggarMessages) {
                                event.setCanceled(true);
                            }
                            if (!BeggarMod.logBeggarMessages) return;
                            String formatted_username = event.message.getSiblings().get(0).getUnformattedText(); // Username with Level (hypixel message formatting sucks)
                            String formatted_message = event.message.getSiblings().get(1).getUnformattedText(); // Message with format and without username

                            Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText("[BEGGAR] " + formatted_username + " " + formatted_message).setChatStyle(new net.minecraft.util.ChatStyle().setColor(EnumChatFormatting.DARK_RED)));
                        }
                    }
                }
            }
        });
    }
}