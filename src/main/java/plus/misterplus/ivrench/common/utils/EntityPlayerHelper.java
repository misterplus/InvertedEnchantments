package plus.misterplus.ivrench.common.utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import plus.misterplus.ivrench.common.enchantment.SuffocationEnchantment;

import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getCurrentLevelTool;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

public class EntityPlayerHelper {
    public static boolean isHeadInWater(PlayerEntity player){
        Vec3d pos = player.getEyePosition(1.0f);
        BlockState blockState = player.getEntityWorld().getBlockState(new BlockPos(pos));
        if (blockState.getBlock() instanceof FlowingFluidBlock) {
            FlowingFluidBlock fluidBlock = (FlowingFluidBlock) blockState.getBlock();
            if (fluidBlock.getFluid() == Fluids.WATER || fluidBlock.getFluid() == Fluids.FLOWING_WATER) {
                return true;
            }
        }
        return blockState.has(BlockStateProperties.WATERLOGGED) && blockState.get(BlockStateProperties.WATERLOGGED);
    }
    public static boolean isFeetInWater(PlayerEntity player){
        Vec3d pos = player.getPositionVec();
        BlockState blockState = player.getEntityWorld().getBlockState(new BlockPos(pos));
        if (blockState.getBlock() instanceof FlowingFluidBlock) {
            FlowingFluidBlock fluidBlock = (FlowingFluidBlock) blockState.getBlock();
            if (fluidBlock.getFluid() == Fluids.WATER || fluidBlock.getFluid() == Fluids.FLOWING_WATER) {
                return true;
            }
        }
        return blockState.has(BlockStateProperties.WATERLOGGED) && blockState.get(BlockStateProperties.WATERLOGGED);
    }
    public static void tryRemoveAir(PlayerEntity player) {
        ItemStack item = player.inventory.armorItemInSlot(3);
        int level = getCurrentLevelTool(item, getEnchantment("suffocation"));
        if (level != -1) {
            player.setAir(Math.max(player.getAir() - level, -19));
        }

    }
}
