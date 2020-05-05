package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Iterator;

public class IceMelterEnchantment extends InvertedEnchantmentBase {
    public IceMelterEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
    }

    public static void freezeNearby(LivingEntity p_185266_0_, World p_185266_1_, BlockPos p_185266_2_, int p_185266_3_) {
        if (p_185266_0_.onGround) {
            BlockState blockstate = Blocks.FROSTED_ICE.getDefaultState();
            float f = (float) Math.min(16, 2 + p_185266_3_);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            Iterator var7 = BlockPos.getAllInBoxMutable(p_185266_2_.add((double) (-f), -1.0D, (double) (-f)), p_185266_2_.add((double) f, -1.0D, (double) f)).iterator();

            while (true) {
                BlockPos blockpos;
                BlockState blockstate1;
                do {
                    do {
                        if (!var7.hasNext()) {
                            return;
                        }

                        blockpos = (BlockPos) var7.next();
                    } while (!blockpos.withinDistance(p_185266_0_.getPositionVec(), (double) f));

                    blockpos$mutable.setPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    blockstate1 = p_185266_1_.getBlockState(blockpos$mutable);
                } while (!blockstate1.isAir(p_185266_1_, blockpos$mutable));

                BlockState blockstate2 = p_185266_1_.getBlockState(blockpos);
                boolean isFull = blockstate2.getBlock() == Blocks.WATER && (Integer) blockstate2.get(FlowingFluidBlock.LEVEL) == 0;
                if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.isValidPosition(p_185266_1_, blockpos) && p_185266_1_.func_226663_a_(blockstate, blockpos, ISelectionContext.dummy()) && !ForgeEventFactory.onBlockPlace(p_185266_0_, new BlockSnapshot(p_185266_1_, blockpos, blockstate2), Direction.UP)) {
                    p_185266_1_.setBlockState(blockpos, blockstate);
                    p_185266_1_.getPendingBlockTicks().scheduleTick(blockpos, Blocks.FROSTED_ICE, MathHelper.nextInt(p_185266_0_.getRNG(), 60, 120));
                }
            }
        }
    }
//    public static void freezeNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {
//        if (living.onGround) {
//            float f = (float) Math.min(16, 2 + level);
//            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//            for (BlockPos blockpos$mutable1 : BlockPos.getAllInBoxMutable(pos.add(-f, -1.0D, -f), pos.add(f, -1.0D, f))) {
//                if (blockpos$mutable1.withinDistance(living.getPositionVec(),f)){
//                    blockpos$mutable.setPos(blockpos$mutable1.getX(), blockpos$mutable1.getY() + 1, blockpos$mutable1.getZ());
//                    BlockState blockstate = worldIn.getBlockState(blockpos$mutable);
//
//                    if (blockstate.getMaterial() == Material.AIR) {
//                        BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable1);
//
//                        if (blockstate1.getMaterial() == Material.ICE && !worldIn.getDimension().isNether()) {
//                            worldIn.setBlockState(blockpos$mutable1, Blocks.WATER.getDefaultState(), 11);
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
