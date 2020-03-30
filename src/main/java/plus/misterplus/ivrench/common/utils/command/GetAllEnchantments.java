package plus.misterplus.ivrench.common.utils.command;

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
import net.minecraft.util.text.TextComponentTranslation;
import plus.misterplus.ivrench.InvertedEnchantments;

public class GetAllEnchantments extends CommandBase {


	@Override
	public String getName() {
		return "ivrench";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return new TextComponentTranslation("ivrench.command.ivrench.usage").toString();
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
					if((ench.getRegistryName().getNamespace().equalsIgnoreCase(InvertedEnchantments.MOD_ID))) {

						NBTTagCompound nbtc = new NBTTagCompound();
						nbtc.setShort("id", (short)Enchantment.getEnchantmentID(ench));
						nbtc.setShort("lvl", (short)ench.getMaxLevel());
						nbt.appendTag(nbtc);

					}
				}
				NBTTagCompound nbttc = new NBTTagCompound();
				nbttc.setTag("StoredEnchantments", nbt);
				item.setTagCompound(nbttc);
				item.setTranslatableName("item.ivrench.ivrench_book.name");
				player.addItemStackToInventory(item);
				sender.sendMessage(new TextComponentTranslation("ivrench.command.ivrench.succeed"));
				return;
			}
		}

		sender.sendMessage(new TextComponentTranslation("ivrench.command.ivrench.usage"));


	}


}