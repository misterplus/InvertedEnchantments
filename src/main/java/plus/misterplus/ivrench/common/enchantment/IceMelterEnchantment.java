package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class IceMelterEnchantment extends InvertedEnchantmentBase {
    public IceMelterEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
    }

//    public static void freezeNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {
//        if (living.onGround) {
//            float f = (float) Math.min(16, 2 + level);
//            BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.offset(0.0f,0, 0, 0);
//
//            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add(-f, -1.0D, -f), pos.add(f, -1.0D, f))) {
//                if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double) (f * f)) {
//                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
//                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);
//
//                    if (iblockstate.getMaterial() == Material.AIR) {
//                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);
//
//                        if (iblockstate1.getMaterial() == Material.ICE && worldIn.provider.getDimension() != -1) {
//                            worldIn.setBlockState(blockpos$mutableblockpos1, Blocks.FLOWING_WATER.getDefaultState(), 11);
//                        }
//                    }
//                }
//            }
//        }
//    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof DepthStriderEnchantment || ench instanceof WaterTrapperEnchantment || ench instanceof FrostWalkerEnchantment)
            return false;
        else
            return super.canApplyTogether(ench);
    }
}
