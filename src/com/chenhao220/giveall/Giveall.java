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
		 this.getLogger().info("插件已加载");
		   if(!new File(getDataFolder()+"\\config.yml").exists()){
			   this.getDataFolder().mkdir();
			   this.getConfig().options().copyDefaults(true);
			   this.saveDefaultConfig();
		   }
		   this.getLogger().info("插件配置文件已读取");
	   }
	   public void onDisable(){
		   this.getLogger().info("插件已卸载");
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
				    	   sender.sendMessage("§c[GiveAll]数量太多，物品数量必须小于64");
			    		   sender.sendMessage("§c[GiveAll]用法：/giveall <物品ID> <物品数量>");
			    		   return true;
				       }
				       if(itemAmount <1){
				    	   sender.sendMessage("§c[GiveAll]物品数量不正确");
			    		   sender.sendMessage("§c[GiveAll]用法：/giveall <物品ID> <物品数量>");
                           return true;
			    	   }
			    	   for(Player p : getServer().getOnlinePlayers()){
			    		   p.getInventory().addItem(item);
			    		   p.sendMessage("§a[GiveAll]已收到物品："+itemID+" "+itemAmount+"个");
			    		   return true;
			    	   }
			    	   }catch(Exception e){
			    		   sender.sendMessage("§c[GiveAll]参数错误！");
			    		   sender.sendMessage("§c[GiveAll]用法：/giveall <物品ID> <物品数量>");
			    	   }
			       }
			       if(args.length!=2){
				       sender.sendMessage("§a[GiveAll] Version: "+this.getConfig().getString("version"));
				       sender.sendMessage("§a[GiveAll] Code: "+this.getConfig().getString("code"));
				       sender.sendMessage("§a此版本作者:chenhao220");
				       sender.sendMessage("§a[GiveAll]用法：/giveall <物品ID> <物品数量>");
				       return true;
			       }
		return false;
		   }else{
			   sender.sendMessage("命令错误");
			   return false;
		   }
	   }
}
