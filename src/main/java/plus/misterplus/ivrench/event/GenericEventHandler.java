package plus.misterplus.ivrench.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.EntityPlayerHelper.*;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getCurrentLevelTool;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenericEventHandler {

    @SubscribeEvent
    public void onArrowLoose(ArrowLooseEvent event) {
        if (event.getWorld().isRemote()) {
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                int size = player.inventory.getSizeInventory();
                int i = 0;
                while (true) {
                    if (player.inventory.getStackInSlot(i).getItem() instanceof ArrowItem){
                        player.inventory.removeStackFromSlot(i);
                    }
                    if (i == size) break;
                    i++;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        World world = event.player.getEntityWorld();
        PlayerEntity player = event.player;
        int i = getMaxEnchantmentLevel(getEnchantment("projectile_noprotection"), player);
        if (i > 0) {
            BlockPos pos = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
            BlockPos pos1 = pos.offset(Direction.UP).offset(Direction.WEST, i).offset(Direction.NORTH, i);
            BlockPos pos2 = pos.offset(Direction.DOWN).offset(Direction.EAST, i).offset(Direction.SOUTH, i);
            //Only works with arrows for now
            List<ArrowEntity> list = world.getEntitiesWithinAABB(ArrowEntity.class, new AxisAlignedBB(pos1, pos2));
            for (ArrowEntity arrow : list) {
                arrow.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
            }
        }
        int j = getMaxEnchantmentLevel(getEnchantment("self_thorn"), player);
        if (j > 0) {
            player.attackEntityFrom(DamageSource.CACTUS, (float) j / 20.0F);
        }
        int k = getMaxEnchantmentLevel(getEnchantment("depth_slower"), player);
        if (k > 0 && isFeetInWater(player)) {
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 1, k - 1, false, false));
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        if (isFeetInWater(player) && getMaxEnchantmentLevel(getEnchantment("aqua_allergy"), player) > 0) {
            event.setNewSpeed(event.getOriginalSpeed() / 10.0F);
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        Entity targetEntity = event.getEntity();
        int i = getMaxEnchantmentLevel(getEnchantment("knockforward"), player);
        if (i > 0) {
            if (targetEntity instanceof LivingEntity) {
                ((LivingEntity) targetEntity).knockBack(player, (float) i * 0.5F, -MathHelper.sin(player.rotationYaw * 0.017453292F), MathHelper.cos(player.rotationYaw * 0.017453292F));
            } else {
                targetEntity.addVelocity(MathHelper.sin(player.rotationYaw * 0.017453292F) * (float) i * 0.5F, -0.1D, -MathHelper.cos(player.rotationYaw * 0.017453292F) * (float) i * 0.5F);
            }
        }

        int j = getMaxEnchantmentLevel(getEnchantment("self_fire"), player);
        if (j > 0) {
            player.setFire(j);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity hunter = event.getSource().getTrueSource();
        Entity arrow = event.getSource().getImmediateSource();
        Entity targetEntity = event.getEntity();
        if (arrow instanceof ArrowEntity && hunter instanceof LivingEntity) {
            LivingEntity player = (LivingEntity) hunter;
            int i = getMaxEnchantmentLevel(getEnchantment("powerless"), player);
            if (i > 0) {
                event.setAmount((float) (event.getAmount() - (float) i * 0.5D + 0.5D));
            }
            int j = getMaxEnchantmentLevel(getEnchantment("self_punch"), player);
            if (j > 0) {
                if (targetEntity instanceof LivingEntity) {
                    ((LivingEntity) targetEntity).knockBack(player, (float) j * 0.5F, -MathHelper.sin(player.rotationYaw * 0.017453292F), MathHelper.cos(player.rotationYaw * 0.017453292F));
                } else {
                    targetEntity.addVelocity(MathHelper.sin(player.rotationYaw * 0.017453292F) * (float) j * 0.5F, -0.1D, -MathHelper.cos(player.rotationYaw * 0.017453292F) * (float) j * 0.5F);
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
        if (item instanceof BowItem || entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            int j = getMaxEnchantmentLevel(getEnchantment("self_punch"), player);
            if (j > 0) {
                player.knockBack(player, (float) j * 0.5F, -MathHelper.sin(player.rotationYaw * 0.017453292F), MathHelper.cos(player.rotationYaw * 0.017453292F));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDrop(LivingDropsEvent event) {
        Entity killer = event.getSource().getTrueSource();
        if (killer instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) killer;
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
            event.setDropChance(1.0F - (float) i / 3.0F);
        }

        int j = getMaxEnchantmentLevel(getEnchantment("touching"), event.getHarvester());
        if (j > 0)
            event.setDropChance(0.0F);
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        int i = getMaxEnchantmentLevel(getEnchantment("undigging"), player);
        if (i > 0) {
            event.setNewSpeed(event.getOriginalSpeed() / (float) i);
        }
    }
    @SubscribeEvent
    public void suffocate(EntityEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            if(isHeadInWater((PlayerEntity) event.getEntity())){
                tryRemoveAir((PlayerEntity) event.getEntity());
            }
        }
    }



}