package plus.misterplus.ivrench.event;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static plus.misterplus.ivrench.InvertedEnchantments.APRIL_FOOLS;
import static plus.misterplus.ivrench.common.utils.EntityPlayerHelper.*;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;
import static plus.misterplus.ivrench.common.utils.ItemsHelper.clearProjectiles;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenericEventHandler {

    @SubscribeEvent
    public static void onPlayerFishing(ItemFishedEvent event) {
        LivingEntity player = event.getEntityLiving();
        int a = getMaxEnchantmentLevel(getEnchantment("lureless"), player);
        if (a > 0) {
            Random r = new Random();
            if (r.nextInt(a) >= 1) {
                Entity fish = new CodEntity(EntityType.COD, event.getHookEntity().getEntityWorld());
                fish.setPosition(event.getHookEntity().getPosX(), event.getHookEntity().getPosY(), event.getHookEntity().getPosZ());
                event.getHookEntity().getEntityWorld().addEntity(fish);
                event.setCanceled(true);
            }
        }
        int b = getMaxEnchantmentLevel(getEnchantment("loot_less_fishing"), player);
        if (b > 0) {
            Random r = new Random();
            if (r.nextInt(b) >= 1) {
                Entity tnt = new TNTEntity(event.getHookEntity().getEntityWorld(), event.getHookEntity().getPosX(), event.getHookEntity().getPosY(), event.getHookEntity().getPosZ(), event.getEntityLiving());
                event.getHookEntity().getEntityWorld().addEntity(tnt);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onArrowImpactEvent(ProjectileImpactEvent event) {
        if (event.getEntity().getTags().contains("ivrench_fake_arrow") && event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
            System.out.println(((EntityRayTraceResult) event.getRayTraceResult()).getEntity().hurtResistantTime = 20);
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        World world = event.player.getEntityWorld();
        PlayerEntity player = event.player;
        int i = getMaxEnchantmentLevel(getEnchantment("projectile_noprotection"), player);
        if (i > 0) {
            i = i + 3;
            BlockPos pos = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
            BlockPos pos1 = pos.offset(Direction.UP).offset(Direction.WEST, i).offset(Direction.NORTH, i);
            BlockPos pos2 = pos.offset(Direction.DOWN).offset(Direction.EAST, i).offset(Direction.SOUTH, i);
            List<AbstractArrowEntity> list = world.getEntitiesWithinAABB(AbstractArrowEntity.class, new AxisAlignedBB(pos1, pos2));
            for (AbstractArrowEntity arrow : list) {
                arrow.setVelocity(player.getPosX() - arrow.getPosX(), player.getPosYEye() - arrow.getPosY(), player.getPosZ() - arrow.getPosZ());
                if (APRIL_FOOLS) player.hurtResistantTime = 0;
            }

            List<ProjectileItemEntity> list2 = world.getEntitiesWithinAABB(ProjectileItemEntity.class, new AxisAlignedBB(pos1, pos2));
            for (ProjectileItemEntity projectile : list2) {
                projectile.setVelocity(player.getPosX() - projectile.getPosX(), player.getPosYEye() - projectile.getPosY(), player.getPosZ() - projectile.getPosZ());
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
        int l = getMaxEnchantmentLevel(getEnchantment("suffocation"), player);
        if (l > 0 && isHeadInWater(player)) {
            tryRemoveAir(player);
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
        int i = getMaxEnchantmentLevel(getEnchantment("knock_forward"), player);
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
                System.out.println(event.getAmount());
                event.setAmount(Math.max(event.getAmount() - (float) i, 0.5f));
                System.out.println(event.getAmount());
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
        if (hunter instanceof LivingEntity) {
            LivingEntity player = (LivingEntity) hunter;
            int l = getMaxEnchantmentLevel(getEnchantment("bounce"), player);
            if (l > 0) {
                player.attackEntityFrom(DamageSource.MAGIC, (float) (event.getAmount() * l * 0.5));
                event.setAmount(0);
            }

        }
    }

    @SubscribeEvent
    public static void onLivingEntityUseItem(LivingEntityUseItemEvent.Stop event) {
        ItemStack itemStack = event.getItem();
        Item item = event.getItem().getItem();
        Entity entity = event.getEntity();
        if (item instanceof BowItem || entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            int j = getMaxEnchantmentLevel(getEnchantment("self_punch"), player);
            if (j > 0) {
                player.knockBack(player, (float) j * 0.5F, -MathHelper.sin(player.rotationYaw * 0.017453292F), MathHelper.cos(player.rotationYaw * 0.017453292F));
            }
            int k = getMaxEnchantmentLevel(getEnchantment("unishot"), player);
            if (k > 0) {
                System.out.println(event.getItem());
                clearProjectiles(event.getItem());
                event.setCanceled(true);
            }
            int i = getMaxEnchantmentLevel(getEnchantment("self_channeling"), player);
            if (i > 0) {
                LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(player.getEntityWorld(), player.getPosX(), player.getPosY(), player.getPosZ(), false);
                lightningBoltEntity.createSpawnPacket();
            }
            int m = getMaxEnchantmentLevel(getEnchantment("betray"), player);
            if (m > 0) {
                event.setCanceled(true);
                itemStack.setCount(0);
            }
            int n = getMaxEnchantmentLevel(getEnchantment("finity"), player);
            if (n > 0) {
                tryRemoveArrow(player);
            }
            int o = getMaxEnchantmentLevel(getEnchantment("dryness"), player);
            if (o > 0) {
                if (player.getEntityWorld().isRaining() || player.isInWater()) {
                    player.getEntityWorld().getWorldInfo().setRaining(false);
                    player.knockBack(player, (float) o * 2.5F, -MathHelper.sin(player.rotationYaw * 0.017453292F), MathHelper.cos(player.rotationYaw * 0.017453292F));
                }
                event.setCanceled(true);
            }

        }
    }

    @SubscribeEvent
    public static void onLivingDrop(LivingDropsEvent event) {
        Entity killer = event.getSource().getTrueSource();
        if (killer instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) killer;
            int i = getMaxEnchantmentLevel(getEnchantment("loot_less"), living);
            if (i > 0) {
                event.setCanceled(living.getEntityWorld().rand.nextInt(i + 1) > 0);
            }
        }
    }
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        int j = getMaxEnchantmentLevel(getEnchantment("touching"), player);
        if (j > 0) {
            event.setCanceled(true);
            event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState() , 11);
            player.getHeldItemMainhand().attemptDamageItem(1, new Random(), (ServerPlayerEntity) player);
        }

    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        int i = getMaxEnchantmentLevel(getEnchantment("undigging"), player);
        if (i > 0) {
            event.setNewSpeed(event.getOriginalSpeed() / (float) i);
        }
    }
}