package com.chenhao220.giveall;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Giveall extends JavaPlugin implements Listener {
	public void onEnable(){
		 this.getLogger().info("����Ѽ���");
		   if(!new File(getDataFolder()+"\\config.yml").exists()){
			   this.getDataFolder().mkdir();
			   this.getConfig().options().copyDefaults(true);
			   this.saveDefaultConfig();
		   }
		   this.getLogger().info("��������ļ��Ѷ�ȡ");
	   }
	   public void onDisable(){
		   this.getLogger().info("�����ж��");
		   this.saveConfig();
	   }

	public boolean onCommand(CommandSender sender,Command cmd, String label, String[] args){
		   if(cmd.getName().equalsIgnoreCase("giveall")){
			       if(args.length==2){
			    	   String str = args[0];
			    	   String str2 = args[1];
			    	   try{
			    	   int itemID = Integer.parseInt(str);
			    	   int itemAmount = Integer.parseInt(str2);
				       ItemStack item = new ItemStack(itemID,itemAmount);
				       if(itemAmount >64){
				    	   sender.sendMessage("��c[GiveAll]����̫�࣬��Ʒ��������С��64");
			    		   sender.sendMessage("��c[GiveAll]�÷���/giveall <��ƷID> <��Ʒ����>");
			    		   return true;
				       }
				       if(itemAmount <1){
				    	   sender.sendMessage("��c[GiveAll]��Ʒ��������ȷ");
			    		   sender.sendMessage("��c[GiveAll]�÷���/giveall <��ƷID> <��Ʒ����>");
                           return true;
			    	   }
			    	   for(Player p : getServer().getOnlinePlayers()){
			    		   p.getInventory().addItem(item);
			    		   p.sendMessage("��a[GiveAll]���յ���Ʒ��"+itemID+" "+itemAmount+"��");
			    		   return true;
			    	   }
			    	   }catch(Exception e){
			    		   sender.sendMessage("��c[GiveAll]��������");
			    		   sender.sendMessage("��c[GiveAll]�÷���/giveall <��ƷID> <��Ʒ����>");
			    	   }
			       }
			       if(args.length!=2){
				       sender.sendMessage("��a[GiveAll] Version: "+this.getConfig().getString("version"));
				       sender.sendMessage("��a[GiveAll] Code: "+this.getConfig().getString("code"));
				       sender.sendMessage("��a�˰汾����:chenhao220");
				       sender.sendMessage("��a[GiveAll]�÷���/giveall <��ƷID> <��Ʒ����>");
				       return true;
			       }
		return false;
		   }else{
			   sender.sendMessage("�������");
			   return false;
		   }
	   }
}
