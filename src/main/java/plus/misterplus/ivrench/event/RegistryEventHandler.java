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
        event.getRegistry().registerAll(new EnchantmentSuffocation(RARE, aentityequipmentslot).setName("suffocation").setRegistryName("suffocation"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(COMMON, ALL, aentityequipmentslot).setName("noprotection").setRegistryName("noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(UNCOMMON, FIRE, aentityequipmentslot).setName("fire_noprotection").setRegistryName("fire_noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(UNCOMMON, FALL, aentityequipmentslot).setName("featherless_falling").setRegistryName("featherless_falling"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(RARE, EXPLOSION, aentityequipmentslot).setName("blast_noprotection").setRegistryName("blast_noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(UNCOMMON, PROJECTILE, aentityequipmentslot).setName("projectile_noprotection").setRegistryName("projectile_noprotection"));
        event.getRegistry().registerAll(new EnchantmentWaterSlower(RARE, aentityequipmentslot).setName("aqua_allergy").setRegistryName("aqua_allergy"));
        event.getRegistry().registerAll(new EnchantmentSelfThorn(VERY_RARE, aentityequipmentslot).setName("self_thorn").setRegistryName("self_thorn"));
        event.getRegistry().registerAll(new EnchantmentWaterTrapper(RARE, aentityequipmentslot).setName("depth_slower").setRegistryName("depth_slower"));
        event.getRegistry().registerAll(new EnchantmentIceMelter(RARE, new EntityEquipmentSlot[]{FEET}).setName("ice_melter").setRegistryName("ice_melter"));
        event.getRegistry().registerAll(new EnchantmentUnbindingCurse(VERY_RARE, aentityequipmentslot).setName("unbinding_curse").setRegistryName("unbinding_curse"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(COMMON, 0, new EntityEquipmentSlot[]{MAINHAND}).setName("bluntness").setRegistryName("bluntness"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(UNCOMMON, 1, new EntityEquipmentSlot[]{MAINHAND}).setName("smiteless").setRegistryName("smiteless"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(UNCOMMON, 2, new EntityEquipmentSlot[]{MAINHAND}).setName("blessing_of_arthropods").setRegistryName("blessing_of_arthropods"));
        event.getRegistry().registerAll(new EnchantmentKnockForward(UNCOMMON, new EntityEquipmentSlot[]{MAINHAND}).setName("knockforward").setRegistryName("knockforward"));
        event.getRegistry().registerAll(new EnchantmentSelfFire(RARE, new EntityEquipmentSlot[]{MAINHAND}).setName("self_fire").setRegistryName("self_fire"));
        event.getRegistry().registerAll(new EnchantmentLootless(RARE, WEAPON, MAINHAND).setName("lootLess").setRegistryName("lootLess"));
        event.getRegistry().registerAll(new EnchantmentLootless(RARE, DIGGER, MAINHAND).setName("lootLessDigger").setRegistryName("lootLessDigger"));
        event.getRegistry().registerAll(new EnchantmentLootless(RARE, FISHING_ROD, MAINHAND).setName("lootLessFishing").setRegistryName("lootLessFishing"));
        event.getRegistry().registerAll(new EnchantmentEdgeless(RARE, new EntityEquipmentSlot[]{MAINHAND}).setName("edgeless").setRegistryName("edgeless"));
        event.getRegistry().registerAll(new EnchantmentUndigging(COMMON, new EntityEquipmentSlot[]{MAINHAND}).setName("undigging").setRegistryName("undigging"));
        event.getRegistry().registerAll(new EnchantmentTouching(VERY_RARE, new EntityEquipmentSlot[]{MAINHAND}).setName("touching").setRegistryName("touching"));
        event.getRegistry().registerAll(new EnchantmentNoDurability(UNCOMMON, new EntityEquipmentSlot[]{MAINHAND}).setName("breaking").setRegistryName("breaking"));
        event.getRegistry().registerAll(new EnchantmentArrowNoDamage(COMMON, MAINHAND).setName("powerless").setRegistryName("powerless"));
        event.getRegistry().registerAll(new EnchantmentArrowKnockForward(RARE, new EntityEquipmentSlot[]{MAINHAND}).setName("self_punch").setRegistryName("self_punch"));
        event.getRegistry().registerAll(new EnchantmentArrowWater(RARE, new EntityEquipmentSlot[]{MAINHAND}).setName("aqua").setRegistryName("aqua"));
        event.getRegistry().registerAll(new EnchantmentArrowFinite(VERY_RARE, new EntityEquipmentSlot[]{MAINHAND}).setName("finity").setRegistryName("finity"));
        event.getRegistry().registerAll(new EnchantmentFishingSlow(RARE, FISHING_ROD, new EntityEquipmentSlot[]{MAINHAND}).setName("lureless").setRegistryName("lureless"));
        event.getRegistry().registerAll(new EnchantmentBreaking(RARE, EntityEquipmentSlot.values()).setName("unmending").setRegistryName("unmending"));
        event.getRegistry().registerAll(new EnchantmentVanishing(VERY_RARE, EntityEquipmentSlot.values()).setName("vanishing").setRegistryName("vanishing"));
    }
}
