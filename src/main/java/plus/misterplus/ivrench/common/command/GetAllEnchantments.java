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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import plus.misterplus.ivrench.InvertedEnchantments;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
                item.setTranslatableName("item.ivrench.ivrench_book.name");
                player.addItemStackToInventory(item);
                sender.sendMessage(new TextComponentTranslation("ivrench.command.ivrench.succeed"));
                return;
            }
        }
        sender.sendMessage(new TextComponentTranslation("ivrench.command.ivrench.usage"));
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        List<String> list = new LinkedList<>();
        list.add("all");
        return list;
    }
}