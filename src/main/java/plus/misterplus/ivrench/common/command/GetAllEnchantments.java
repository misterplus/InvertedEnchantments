package plus.misterplus.ivrench.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import plus.misterplus.ivrench.InvertedEnchantments;

public class GetAllEnchantments extends CommandBase {

    @Override
    public String getName() {
        return InvertedEnchantments.MOD_ID;
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
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length == 1) {
            if ("all".equals(args[0])) {
                EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
                ItemStack item = new ItemStack(Items.ENCHANTED_BOOK);
                for (Enchantment ench : Enchantment.REGISTRY) {
                    if ((InvertedEnchantments.MOD_ID.equals(ench.getRegistryName().getNamespace()))) {
                        ItemEnchantedBook.addEnchantment(item, new EnchantmentData(ench, ench.getMaxLevel()));
                    }
                }
                player.addItemStackToInventory(item);
                sender.sendMessage(new TextComponentTranslation("ivrench.command.ivrench.succeed"));
                return;
            }
        }
        sender.sendMessage(new TextComponentTranslation("ivrench.command.ivrench.usage"));
    }
}