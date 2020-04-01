package plus.misterplus.ivrench.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import plus.misterplus.ivrench.common.enchantment.*;

import static net.minecraft.enchantment.Enchantment.Rarity.*;
import static net.minecraft.enchantment.EnchantmentProtection.Type.ALL;
import static net.minecraft.enchantment.EnchantmentProtection.Type.*;
import static net.minecraft.enchantment.EnumEnchantmentType.*;
import static net.minecraft.inventory.EntityEquipmentSlot.FEET;
import static net.minecraft.inventory.EntityEquipmentSlot.MAINHAND;
import static plus.misterplus.ivrench.InvertedEnchantments.aentityequipmentslot;

@Mod.EventBusSubscriber
public class RegistryEventHandler {
    @SubscribeEvent
    public static void onEnchantmentRegistration(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(new EnchantmentSuffocation(RARE, aentityequipmentslot).setFakeName("oxygen").setName("suffocation").setRegistryName("suffocation"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(COMMON, ALL, aentityequipmentslot).setRegistryName("noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(UNCOMMON, FIRE, aentityequipmentslot).setRegistryName("fire_noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(UNCOMMON, FALL, aentityequipmentslot).setRegistryName("featherless_falling"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(RARE, EXPLOSION, aentityequipmentslot).setRegistryName("blast_noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(UNCOMMON, PROJECTILE, aentityequipmentslot).setRegistryName("projectile_noprotection"));
        event.getRegistry().registerAll(new EnchantmentWaterSlower(RARE, aentityequipmentslot).setFakeName("waterWorker").setName("aqua_allergy").setRegistryName("aqua_allergy"));
        event.getRegistry().registerAll(new EnchantmentSelfThorn(VERY_RARE, aentityequipmentslot).setFakeName("thorns").setName("self_thorn").setRegistryName("self_thorn"));
        event.getRegistry().registerAll(new EnchantmentWaterTrapper(RARE, aentityequipmentslot).setFakeName("waterWalker").setName("depth_slower").setRegistryName("depth_slower"));
        event.getRegistry().registerAll(new EnchantmentIceMelter(RARE, FEET).setFakeName("frostWalker").setName("ice_melter").setRegistryName("ice_melter"));
        event.getRegistry().registerAll(new EnchantmentUnbindingCurse(VERY_RARE, aentityequipmentslot).setFakeName("binding_curse").setName("unbinding_curse").setRegistryName("unbinding_curse"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(COMMON, 0, new EntityEquipmentSlot[]{MAINHAND}).setRegistryName("bluntness"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(UNCOMMON, 1, new EntityEquipmentSlot[]{MAINHAND}).setRegistryName("smiteless"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(UNCOMMON, 2, new EntityEquipmentSlot[]{MAINHAND}).setRegistryName("blessing_of_arthropods"));
        event.getRegistry().registerAll(new EnchantmentKnockForward(UNCOMMON, MAINHAND).setFakeName("knockback").setName("knockforward").setRegistryName("knockforward"));
        event.getRegistry().registerAll(new EnchantmentSelfFire(RARE, MAINHAND).setFakeName("fire").setName("self_fire").setRegistryName("self_fire"));
        event.getRegistry().registerAll(new EnchantmentLootless(RARE, WEAPON, MAINHAND).setFakeName("lootBonus").setName("lootLess").setRegistryName("lootLess"));
        event.getRegistry().registerAll(new EnchantmentLootless(RARE, DIGGER, MAINHAND).setFakeName("lootBonusDigger").setName("lootLessDigger").setRegistryName("lootLessDigger"));
        event.getRegistry().registerAll(new EnchantmentLootless(RARE, FISHING_ROD, MAINHAND).setFakeName("lootBonusFishing").setName("lootLessFishing").setRegistryName("lootLessFishing"));
        event.getRegistry().registerAll(new EnchantmentEdgeless(RARE, MAINHAND).setFakeName("sweeping").setName("edgeless").setRegistryName("edgeless"));
        event.getRegistry().registerAll(new EnchantmentUndigging(COMMON, MAINHAND).setFakeName("digging").setName("undigging").setRegistryName("undigging"));
        event.getRegistry().registerAll(new EnchantmentTouching(VERY_RARE, MAINHAND).setFakeName("untouching").setName("touching").setRegistryName("touching"));
        event.getRegistry().registerAll(new EnchantmentNoDurability(UNCOMMON, MAINHAND).setFakeName("durability").setName("breaking").setRegistryName("breaking"));
        event.getRegistry().registerAll(new EnchantmentArrowNoDamage(COMMON, MAINHAND).setFakeName("arrowDamage").setName("powerless").setRegistryName("powerless"));
        event.getRegistry().registerAll(new EnchantmentArrowKnockForward(RARE, MAINHAND).setFakeName("arrowKnockback").setName("self_punch").setRegistryName("self_punch"));
        event.getRegistry().registerAll(new EnchantmentArrowWater(RARE, MAINHAND).setFakeName("arrowFire").setName("aqua").setRegistryName("aqua"));
        event.getRegistry().registerAll(new EnchantmentArrowFinite(VERY_RARE, MAINHAND).setFakeName("arrowInfinite").setName("finity").setRegistryName("finity"));
        event.getRegistry().registerAll(new EnchantmentFishingSlow(RARE, FISHING_ROD, MAINHAND).setFakeName("lootBonusFishing").setName("lureless").setRegistryName("lureless"));
        event.getRegistry().registerAll(new EnchantmentBreaking(RARE, EntityEquipmentSlot.values()).setFakeName("mending").setName("unmending").setRegistryName("unmending"));
        event.getRegistry().registerAll(new EnchantmentVanishing(VERY_RARE, EntityEquipmentSlot.values()).setFakeName("vanishing_curse").setName("vanishing").setRegistryName("vanishing"));
    }
}
