package plus.misterplus.ivrench.event;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mod.EventBusSubscriber
public class GenericEventHandler {
    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        World world = event.player.getEntityWorld();
        EntityPlayer player = event.player;
        int i = getMaxEnchantmentLevel(getEnchantment("projectile_noprotection"), player);
        if (i > 0) {
            BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);
            BlockPos pos1 = pos.offset(EnumFacing.UP, i).offset(EnumFacing.WEST, i).offset(EnumFacing.NORTH, i);
            BlockPos pos2 = pos.offset(EnumFacing.DOWN, i).offset(EnumFacing.EAST, i).offset(EnumFacing.SOUTH, i);
            //Only works with arrows for now
            List<EntityArrow> list = world.getEntitiesWithinAABB(EntityArrow.class, new AxisAlignedBB(pos1, pos2));
            for (EntityArrow arrow : list) {
                arrow.setPosition(player.posX, player.posY, player.posZ);
            }
        }
        int j = getMaxEnchantmentLevel(getEnchantment("self_thorn"), player);
        if (j > 0) {
            player.attackEntityFrom(DamageSource.CACTUS, (float)j / 20.0F);
        }
        int k = getMaxEnchantmentLevel(getEnchantment("depth_slower"), player);
        if (k > 0 && (player.isInsideOfMaterial(Material.WATER) || world.getBlockState(new BlockPos(player.posX, player.posY, player.posZ)).getMaterial() == Material.WATER)) {
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, k - 1, false, false));
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player.isInsideOfMaterial(Material.WATER) && getMaxEnchantmentLevel(getEnchantment("aqua_allergy"), player) > 0) {
            event.setNewSpeed(event.getOriginalSpeed() / 10.0F);
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        Entity targetEntity = event.getEntity();
        int i = getMaxEnchantmentLevel(getEnchantment("knockforward"), player);
        if (i > 0) {
            if (targetEntity instanceof EntityLivingBase)
            {
                ((EntityLivingBase)targetEntity).knockBack(player, (float)i * 0.5F, (double) -MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(MathHelper.cos(player.rotationYaw * 0.017453292F)));
            }
            else
            {
                targetEntity.addVelocity((double)(MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)i * 0.5F), -0.1D, (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)i * 0.5F));
            }
        }

        int j = getMaxEnchantmentLevel(getEnchantment("self_fire"), player);
        if (j > 0) {
            player.setFire(j);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity hurter = event.getSource().getTrueSource();
        Entity arrow = event.getSource().getImmediateSource();
        Entity targetEntity = event.getEntity();
        if (arrow instanceof EntityArrow && hurter instanceof EntityLivingBase) {
            EntityLivingBase player = (EntityLivingBase) hurter;
            int i = getEnchantmentLevel(getEnchantment("powerless"), player.getActiveItemStack());
            if (i > 0) {
                event.setAmount((float) (event.getAmount() - (float)i * 0.5D + 0.5D));
            }
            int j = getMaxEnchantmentLevel(getEnchantment("self_punch"), player);
            if (j > 0) {
                if (targetEntity instanceof EntityLivingBase)
                {
                    ((EntityLivingBase)targetEntity).knockBack(player, (float)j * 0.5F, (double) -MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(MathHelper.cos(player.rotationYaw * 0.017453292F)));
                }
                else
                {
                    targetEntity.addVelocity((double)(MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)j * 0.5F), -0.1D, (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)j * 0.5F));
                }
            }
            int k = getMaxEnchantmentLevel(getEnchantment("aqua"), player);
            if (k > 0) {
                targetEntity.extinguish();
            }
        }
    }

    @SubscribeEvent
    public static void onLivingEntityUseItem(LivingEntityUseItemEvent.Stop event) {
        Item item = event.getItem().getItem();
        Entity entity = event.getEntity();
        if (item instanceof ItemBow || entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            int i = getMaxEnchantmentLevel(getEnchantment("finity"), player);
            if (i > 0) {
                player.inventory.clearMatchingItems(Items.ARROW, 0, 9999, null);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDrop(LivingDropsEvent event) {
        Entity killer = event.getSource().getTrueSource();
        if (killer instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) killer;
            int i = getMaxEnchantmentLevel(getEnchantment("lootLess"), living);
            if (i > 0) {
                event.setCanceled(living.getEntityWorld().rand.nextInt(i + 1) > 0);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        if (event.getHarvester() == null) return;

        int i = getMaxEnchantmentLevel(getEnchantment("lootLessDigger"), event.getHarvester());
        if (i > 0) {
            event.setDropChance(1.0F - (float)i / 3.0F);
        }

        int j = getMaxEnchantmentLevel(getEnchantment("touching"), event.getHarvester());
        if (j > 0)
            event.setDropChance(0.0F);
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        int i = getMaxEnchantmentLevel(getEnchantment("undigging"), player);
        if (i > 0) {
            event.setNewSpeed(event.getOriginalSpeed() / (float)i);
        }
    }
}