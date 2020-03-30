package plus.misterplus.ivrench.common.utils.command;

import java.util.Random;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class FastRandom extends CommandBase {


	@Override
	public String getName() {
		return "fastrandom";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/fastrandom <random count> [type](diamond/gold/iron/chain) [curse](true/false) [bow](true/false) [shield](true/false) [clearinv](true/false) - yep randomenchantment";
	}


	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}


	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(args.length >= 1 && !args[0].isEmpty()) {
			if (args[0].contains("all")){
				NBTTagList nbt = new NBTTagList();
				EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
				ItemStack item = new ItemStack(Item.getByNameOrId("minecraft:enchanted_book"));
				
			 	for (Enchantment ench : Enchantment.REGISTRY) {
					if(!(ench.getRegistryName().getNamespace() == "minecraft")) {

						NBTTagCompound nbtt = new NBTTagCompound();
						nbtt.setShort("id", (short)Enchantment.getEnchantmentID(ench));
						nbtt.setShort("lvl", (short)ench.getMaxLevel());
						nbt.appendTag(nbtt);
						
					}
				}
						NBTTagCompound nbttt = new NBTTagCompound();
						nbttt.setTag("StoredEnchantments", nbt);
						item.setTagCompound(nbttt);
						player.addItemStackToInventory(item);
				return;
			}
		}
		

		int i = 0;
		if(args.length >= 1 && !args[0].isEmpty()) {
			i = Integer.parseInt(args[0]);
			if (i<=0) {
				sender.sendMessage(new TextComponentString("Wrong Count."));
				return;
			}else if (i>=20){
				sender.sendMessage(new TextComponentString("Too much count."));
				return;
			}

		}else {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;

		}

		Boolean curseb = false;
		if(args.length >= 2 && !args[1].isEmpty()) {
			curseb = Boolean.valueOf(args[2]);
		}

		EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();

		try {
			if (Boolean.valueOf(args[5])) player.inventory.clear();
		} catch (Exception e) {}

		try {

			if (args[1].contains("diamond")) {
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:diamond_boots")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:diamond_leggings")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:diamond_chestplate")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:diamond_helmet")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:diamond_sword")),i,curseb));

			}else if (args[1].contains("iron")) {
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:iron_boots")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:iron_leggings")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:iron_chestplate")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:iron_helmet")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:iron_sword")),i,curseb));

			}else if (args[1].contains("gold")) {
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:golden_boots")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:golden_leggings")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:golden_chestplate")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:golden_helmet")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:golden_sword")),i,curseb));

			}else if (args[1].contains("chain")) {
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:chainmail_boots")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:chainmail_leggings")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:chainmail_chestplate")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:chainmail_helmet")),i,curseb));
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:stone_sword")),i,curseb));

			}

			if (Boolean.valueOf(args[3])) {
				player.addItemStackToInventory(genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:bow")),i,curseb));
				player.addItemStackToInventory(new ItemStack(Item.getByNameOrId("minecraft:arrow")));
			}
			if (Boolean.valueOf(args[4])) player.replaceItemInInventory(99,genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:shield")),i,curseb));

		}catch (Exception e) {
			player.replaceItemInInventory(0,genEnchantment(new ItemStack(Item.getByNameOrId("minecraft:wooden_sword")),i,curseb));

		}



		sender.sendMessage(new TextComponentString("§6§l你被强化了，快送！"));

	}


	private ItemStack genEnchantment(ItemStack item,int count,boolean iscursed){
		int j = 0;
		while(j<count) {
			Enchantment ench = Enchantment.REGISTRY.getRandomObject(new Random());
			NBTTagList nbt = item.getEnchantmentTagList();
			int a = 0;
			int b = 0;
			while(a<=nbt.tagCount()) {
				if(!ench.isCompatibleWith(Enchantment.getEnchantmentByID(nbt.getCompoundTagAt(a).getInteger("id")))){
					b++;
				}

				a++;
			}

			if(ench.canApply(item) && b == 0) {
				if (ench.isCurse() && iscursed) { 
					item.addEnchantment(ench, ench.getMaxLevel());
				}else {
					if (!ench.isCurse()) item.addEnchantment(ench, ench.getMaxLevel());
				}

			}else {
				a--;
			}
			j++;
		}
		return item;
	}

}