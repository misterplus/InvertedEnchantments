package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plus.misterplus.ivrench.config.ConfigManager;

import static plus.misterplus.ivrench.InvertedEnchantments.APRIL_FOOLS;

public class IceMelterEnchantment extends InvertedEnchantmentBase {
    public static boolean waterRemove = ConfigManager.COMMON.waterRemove.get();
    public IceMelterEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
    }

    public static void freezeNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {
        if (living.onGround) {
            float f = (float) Math.min(16, 2 + level);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            for (BlockPos blockpos$mutable1 : BlockPos.getAllInBoxMutable(pos.add(-f, -1.0D, -f), pos.add(f, -1.0D, f))) {
                if (blockpos$mutable1.withinDistance(living.getPositionVec(),f)){
                    blockpos$mutable.setX(blockpos$mutable1.getX());
                    blockpos$mutable.setY(blockpos$mutable1.getY() + 1);
                    blockpos$mutable.setY(blockpos$mutable1.getZ());
                    BlockState blockstate = worldIn.getBlockState(blockpos$mutable);

                    if (blockstate.getMaterial() == Material.AIR) {
                        BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable1);

                        if ((APRIL_FOOLS || waterRemove) && blockstate1.getBlock() == Blocks.WATER && blockstate1.getFluidState().isSource()) {
                            worldIn.setBlockState(blockpos$mutable1, Blocks.AIR.getDefaultState(), 11);
                        }
                        if (blockstate1.getBlock() == Blocks.ICE) {
                            if (!worldIn.getDimension().isNether()) {
                                worldIn.setBlockState(blockpos$mutable1, Blocks.WATER.getDefaultState(), 11);
                            } else {
                                worldIn.setBlockState(blockpos$mutable1, Blocks.AIR.getDefaultState(), 11);
                            }
                        }
                        if (!waterRemove) continue;
                        if (blockstate1.getBlock() == Blocks.PACKED_ICE) {
                            if(!worldIn.getDimension().isNether()) {
                                worldIn.setBlockState(blockpos$mutable1, Blocks.ICE.getDefaultState(), 11);
                            } else {
                                worldIn.setBlockState(blockpos$mutable1, Blocks.AIR.getDefaultState(), 11);
                            }
                        }
                        if (blockstate1.getBlock() == Blocks.BLUE_ICE) {
                            if(!worldIn.getDimension().isNether()) {
                                worldIn.setBlockState(blockpos$mutable1, Blocks.PACKED_ICE.getDefaultState(), 11);
                            } else {
                                worldIn.setBlockState(blockpos$mutable1, Blocks.AIR.getDefaultState(), 11);
                            }
                        }
                    }
                }
            }
        }
    }

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
