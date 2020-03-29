package plus.misterplus.ivrench.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import plus.misterplus.ivrench.common.enchantment.*;

import static net.minecraft.enchantment.EnumEnchantmentType.*;
import static net.minecraft.inventory.EntityEquipmentSlot.FEET;
import static net.minecraft.inventory.EntityEquipmentSlot.MAINHAND;
import static plus.misterplus.ivrench.InvertedEnchantments.aentityequipmentslot;

@Mod.EventBusSubscriber
public class RegisteryEventHandler {
    @SubscribeEvent
    public static void onEnchantmentRegistration(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(new EnchantmentSuffocation(Enchantment.Rarity.RARE, aentityequipmentslot).setName("suffocation").setRegistryName("suffocation"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(Enchantment.Rarity.COMMON, EnchantmentProtection.Type.ALL, aentityequipmentslot).setName("noprotection").setRegistryName("noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(Enchantment.Rarity.UNCOMMON, EnchantmentProtection.Type.FIRE, aentityequipmentslot).setName("fire_noprotection").setRegistryName("fire_noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(Enchantment.Rarity.UNCOMMON, EnchantmentProtection.Type.FALL, aentityequipmentslot).setName("featherless_falling").setRegistryName("featherless_falling"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(Enchantment.Rarity.RARE, EnchantmentProtection.Type.EXPLOSION, aentityequipmentslot).setName("blast_noprotection").setRegistryName("blast_noprotection"));
        event.getRegistry().registerAll(new EnchantmentNoProtection(Enchantment.Rarity.UNCOMMON, EnchantmentProtection.Type.PROJECTILE, aentityequipmentslot).setName("projectile_noprotection").setRegistryName("projectile_noprotection"));
        event.getRegistry().registerAll(new EnchantmentWaterSlower(Enchantment.Rarity.RARE, aentityequipmentslot).setName("aqua_allergy").setRegistryName("aqua_allergy"));
        event.getRegistry().registerAll(new EnchantmentSelfThorn(Enchantment.Rarity.VERY_RARE, aentityequipmentslot).setName("self_thorn").setRegistryName("self_thorn"));
        event.getRegistry().registerAll(new EnchantmentWaterTrapper(Enchantment.Rarity.RARE, aentityequipmentslot).setName("depth_slower").setRegistryName("depth_slower"));
        event.getRegistry().registerAll(new EnchantmentIceMelter(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] {FEET}).setName("ice_melter").setRegistryName("ice_melter"));
        event.getRegistry().registerAll(new EnchantmentUnbindingCurse(Enchantment.Rarity.VERY_RARE, aentityequipmentslot).setName("unbinding_curse").setRegistryName("unbinding_curse"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(Enchantment.Rarity.COMMON, 0, new EntityEquipmentSlot[] {MAINHAND}).setName("bluntness").setRegistryName("bluntness"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(Enchantment.Rarity.UNCOMMON, 1, new EntityEquipmentSlot[] {MAINHAND}).setName("smiteless").setRegistryName("smiteless"));
        event.getRegistry().registerAll(new EnchantmentNoDamage(Enchantment.Rarity.UNCOMMON, 2, new EntityEquipmentSlot[] {MAINHAND}).setName("blessing_of_arthropods").setRegistryName("blessing_of_arthropods"));
        event.getRegistry().registerAll(new EnchantmentKnockForward(Enchantment.Rarity.UNCOMMON, new EntityEquipmentSlot[] {MAINHAND}).setName("knockforward").setRegistryName("knockforward"));
        event.getRegistry().registerAll(new EnchantmentSelfFire(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] {MAINHAND}).setName("self_fire").setRegistryName("self_fire"));
        event.getRegistry().registerAll(new EnchantmentLootless(Enchantment.Rarity.RARE, WEAPON, MAINHAND).setName("lootLess").setRegistryName("lootLess"));
        event.getRegistry().registerAll(new EnchantmentLootless(Enchantment.Rarity.RARE, DIGGER, MAINHAND).setName("lootLessDigger").setRegistryName("lootLessDigger"));
        event.getRegistry().registerAll(new EnchantmentLootless(Enchantment.Rarity.RARE, FISHING_ROD, MAINHAND).setName("lootLessFishing").setRegistryName("lootLessFishing"));
        event.getRegistry().registerAll(new EnchantmentEdgeless(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] {MAINHAND}).setName("edgeless").setRegistryName("edgeless"));
        event.getRegistry().registerAll(new EnchantmentUndigging(Enchantment.Rarity.COMMON, new EntityEquipmentSlot[] {MAINHAND}).setName("undigging").setRegistryName("undigging"));
        event.getRegistry().registerAll(new EnchantmentTouching(Enchantment.Rarity.VERY_RARE, new EntityEquipmentSlot[] {MAINHAND}).setName("touching").setRegistryName("touching"));
        event.getRegistry().registerAll(new EnchantmentNoDurability(Enchantment.Rarity.UNCOMMON, new EntityEquipmentSlot[] {MAINHAND}).setName("breaking").setRegistryName("breaking"));
        event.getRegistry().registerAll(new EnchantmentArrowNoDamage(Enchantment.Rarity.COMMON, MAINHAND).setName("powerless").setRegistryName("powerless"));
        event.getRegistry().registerAll(new EnchantmentArrowKnockForward(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND}).setName("self_punch").setRegistryName("self_punch"));
        event.getRegistry().registerAll(new EnchantmentArrowWater(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND}).setName("aqua").setRegistryName("aqua"));
        event.getRegistry().registerAll(new EnchantmentArrowFinite(Enchantment.Rarity.VERY_RARE, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND}).setName("finity").setRegistryName("finity"));
        event.getRegistry().registerAll(new EnchantmentFishingSlow(Enchantment.Rarity.RARE, EnumEnchantmentType.FISHING_ROD, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND}).setName("lureless").setRegistryName("lureless"));
        event.getRegistry().registerAll(new EnchantmentBreaking(Enchantment.Rarity.RARE, EntityEquipmentSlot.values()).setName("unmending").setRegistryName("unmending"));
        event.getRegistry().registerAll(new EnchantmentVanishing(Enchantment.Rarity.VERY_RARE, EntityEquipmentSlot.values()).setName("vanishing").setRegistryName("vanishing"));
    }
}
