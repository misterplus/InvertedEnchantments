package plus.misterplus.ivrench.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import plus.misterplus.ivrench.InvertedEnchantments;

public class MainCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal(InvertedEnchantments.MOD_ID);
        builder.then(Commands
                .literal("all")
                .requires(ctx -> ctx.hasPermissionLevel(4))
                .executes(ctx -> getAll(ctx.getSource()))
        );
        builder.then(Commands
                .literal("debug")
                .requires(ctx -> ctx.hasPermissionLevel(4))
                .executes(ctx -> debug(ctx.getSource()))
        );
        dispatcher.register(builder);
    }

    private static int getAll(CommandSource source) {
        PlayerEntity player = (PlayerEntity) source.getEntity();
        ItemStack item = new ItemStack(Items.ENCHANTED_BOOK);
        for (Enchantment ench : Registry.ENCHANTMENT) {
            if (InvertedEnchantments.MOD_ID.equals(ench.getRegistryName().getNamespace())) {
                EnchantedBookItem.addEnchantment(item, new EnchantmentData(ench, ench.getMaxLevel()));
            }
        }
        item.setDisplayName(new TranslationTextComponent("item.ivrench.ivrench_book.name"));
        player.addItemStackToInventory(item);
        source.sendFeedback(new TranslationTextComponent("ivrench.command.ivrench.succeed"), true);

        return 1;
    }

    private static int debug(CommandSource source) {
        PlayerEntity player = (PlayerEntity) source.getEntity();
        //send debugmsg to game
//        int i=0;
//        for (Enchantment ench : Registry.ENCHANTMENT) {
//            InvertedEnchantments.getLogger().info(ench.getDisplayName(1).getString());
//            if (InvertedEnchantments.MOD_ID.equals(ench.getRegistryName().getNamespace())) {
//                ItemStack item = new ItemStack(Items.ENCHANTED_BOOK);
//                EnchantedBookItem.addEnchantment(item, new EnchantmentData(ench, ench.getMaxLevel()));
//                player.addItemStackToInventory(item);
//                i++;
//            }
//        }
//        InvertedEnchantments.getLogger().info(i);
        String debugstr = player.getHeldItemMainhand().serializeNBT().toString();
        StringTextComponent debugmsg = new StringTextComponent(debugstr);
        player.sendMessage(debugmsg);
        InvertedEnchantments.getLogger().debug(debugstr);

        return 1;
    }
}