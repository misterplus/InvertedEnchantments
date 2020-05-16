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
import plus.misterplus.ivrench.config.ConfigManager;

import static plus.misterplus.ivrench.InvertedEnchantments.APRIL_FOOLS;
import static plus.misterplus.ivrench.common.enchantment.IceMelterEnchantment.waterRemove;

public class MainCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal(InvertedEnchantments.MOD_ID);

        builder.then(Commands
                .literal("all")
                .requires(ctx -> ctx.hasPermissionLevel(4))
                .executes(ctx -> getAll(ctx.getSource()))
        );

        if (ConfigManager.COMMON.debugCommand.get()) {
            builder.then(Commands
                    .literal("debug")
                    .requires(ctx -> ctx.hasPermissionLevel(4))
                    .executes(ctx -> debug(ctx.getSource()))
            );
            if (ConfigManager.COMMON.inGameSwitchApril.get()) {
                builder.then(Commands
                        .literal("april")
                        .requires(ctx -> ctx.hasPermissionLevel(4))
                        .executes(ctx -> switchApril(ctx.getSource()))
                );
            }
            if (ConfigManager.COMMON.inGameSwitchWater.get()) {
                builder.then(Commands
                        .literal("water")
                        .requires(ctx -> ctx.hasPermissionLevel(4))
                        .executes(ctx -> switchWater(ctx.getSource()))
                );
            }
        }
        dispatcher.register(builder);
    }

    private static int getAll(CommandSource source) {
        PlayerEntity player = (PlayerEntity) source.getEntity();
        if (player != null) {
            ItemStack item = new ItemStack(Items.ENCHANTED_BOOK);
            for (Enchantment ench : Registry.ENCHANTMENT) {
                if (InvertedEnchantments.MOD_ID.equals(ench.getRegistryName().getNamespace())) {
                    EnchantedBookItem.addEnchantment(item, new EnchantmentData(ench, ench.getMaxLevel()));
                }
            }
            item.setDisplayName(new TranslationTextComponent("item.ivrench.ivrench_book.name"));
            player.addItemStackToInventory(item);
            source.sendFeedback(new TranslationTextComponent("ivrench.command.ivrench.succeed"), true);
        }
        return 1;
    }

    private static int switchApril(CommandSource source) {
        APRIL_FOOLS = !APRIL_FOOLS;
        source.sendFeedback(new StringTextComponent(String.valueOf(APRIL_FOOLS)), true);
        return 1;
    }

    private static int switchWater(CommandSource source) {
        waterRemove = !waterRemove;
        source.sendFeedback(new StringTextComponent(String.valueOf(waterRemove)), true);
        return 1;
    }

    private static int debug(CommandSource source) {
        PlayerEntity player = (PlayerEntity) source.getEntity();
        if (player != null) {
            String debugstr = player.getHeldItemMainhand().serializeNBT().toString();
            StringTextComponent debugmsg = new StringTextComponent(debugstr);
            player.sendMessage(debugmsg);
            InvertedEnchantments.getLogger().debug(debugstr);
        }
        return 1;
    }
}