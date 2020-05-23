package plus.misterplus.ivrench.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.utils.MixinMethods;

import java.util.List;

@Mixin(Block.class)
public class MixinBlock {
    @Inject(
            method = "func_220077_a(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/tileentity/TileEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void injectBlockDrop(BlockState p_220077_0_, ServerWorld p_220077_1_, BlockPos p_220077_2_, TileEntity p_220077_3_, Entity p_220077_4_, ItemStack p_220077_5_, CallbackInfoReturnable<List<ItemStack>> cir) {
        cir.setReturnValue(MixinMethods.injectBlockDrop(p_220077_4_, cir.getReturnValue()));
    }
}
