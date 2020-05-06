package plus.misterplus.ivrench.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import plus.misterplus.ivrench.common.enchantment.*;

import static net.minecraft.enchantment.Enchantment.Rarity.*;
import static net.minecraft.enchantment.EnchantmentType.*;
import static net.minecraft.enchantment.ProtectionEnchantment.Type.ALL;
import static net.minecraft.enchantment.ProtectionEnchantment.Type.*;
import static net.minecraft.inventory.EquipmentSlotType.FEET;
import static net.minecraft.inventory.EquipmentSlotType.MAINHAND;
import static plus.misterplus.ivrench.InvertedEnchantments.aEquipmentSlotType;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {

    @SubscribeEvent
    public static void onEnchantmentRegistration(final RegistryEvent.Register<Enchantment> event) {

        event.getRegistry().registerAll(new SuffocationEnchantment(RARE, aEquipmentSlotType).setFakeName("respiration").setName("suffocation").setRegistryName("suffocation"));
        event.getRegistry().registerAll(new NoProtectionEnchantment(COMMON, ALL, aEquipmentSlotType).setRegistryName("noprotection"));
        event.getRegistry().registerAll(new NoProtectionEnchantment(UNCOMMON, FIRE, aEquipmentSlotType).setRegistryName("fire_noprotection"));
        event.getRegistry().registerAll(new NoProtectionEnchantment(UNCOMMON, FALL, aEquipmentSlotType).setRegistryName("featherless_falling"));
        event.getRegistry().registerAll(new NoProtectionEnchantment(RARE, EXPLOSION, aEquipmentSlotType).setRegistryName("blast_noprotection"));
        event.getRegistry().registerAll(new NoProtectionEnchantment(UNCOMMON, PROJECTILE, aEquipmentSlotType).setRegistryName("projectile_noprotection"));
        event.getRegistry().registerAll(new WaterSlowerEnchantment(RARE, aEquipmentSlotType).setFakeName("aqua_affinity").setName("aqua_allergy").setRegistryName("aqua_allergy"));
        event.getRegistry().registerAll(new SelfThornEnchantment(VERY_RARE, aEquipmentSlotType).setFakeName("thorns").setName("self_thorn").setRegistryName("self_thorn"));
        event.getRegistry().registerAll(new WaterTrapperEnchantment(RARE, aEquipmentSlotType).setFakeName("depth_strider").setName("depth_slower").setRegistryName("depth_slower"));
        event.getRegistry().registerAll(new IceMelterEnchantment(RARE, FEET).setFakeName("frost_walker").setName("ice_melter").setRegistryName("ice_melter"));
        event.getRegistry().registerAll(new UnbindingCurseEnchantment(VERY_RARE, aEquipmentSlotType).setFakeName("binding_curse").setName("unbinding_curse").setRegistryName("unbinding_curse"));
        event.getRegistry().registerAll(new NoDamageEnchantment(COMMON, 0, new EquipmentSlotType[]{MAINHAND}).setRegistryName("bluntness"));
        event.getRegistry().registerAll(new NoDamageEnchantment(UNCOMMON, 1, new EquipmentSlotType[]{MAINHAND}).setRegistryName("smiteless"));
        event.getRegistry().registerAll(new NoDamageEnchantment(UNCOMMON, 2, new EquipmentSlotType[]{MAINHAND}).setRegistryName("blessing_of_arthropods"));
        event.getRegistry().registerAll(new KnockForwardEnchantment(UNCOMMON, MAINHAND).setFakeName("knockback").setName("knockforward").setRegistryName("knock_forward"));
        event.getRegistry().registerAll(new SelfFireEnchantment(RARE, MAINHAND).setFakeName("fire_aspect").setName("self_fire").setRegistryName("self_fire"));
        event.getRegistry().registerAll(new LootlessEnchantment(RARE, WEAPON, MAINHAND).setFakeName("looting").setName("lootLess").setRegistryName("loot_less"));
        event.getRegistry().registerAll(new LootlessEnchantment(RARE, DIGGER, MAINHAND).setFakeName("fortune").setName("lootLessDigger").setRegistryName("loot_less_digger"));
        event.getRegistry().registerAll(new LootlessEnchantment(RARE, FISHING_ROD, MAINHAND).setFakeName("luck_of_the_sea").setName("lootLessFishing").setRegistryName("loot_less_fishing"));
        event.getRegistry().registerAll(new EdgelessEnchantment(RARE, MAINHAND).setFakeName("sweeping").setName("edgeless").setRegistryName("edgeless"));
        event.getRegistry().registerAll(new UndiggingEnchantment(COMMON, MAINHAND).setFakeName("efficiency").setName("undigging").setRegistryName("undigging"));
        event.getRegistry().registerAll(new TouchingEnchantment(VERY_RARE, MAINHAND).setFakeName("silk_touch").setName("touching").setRegistryName("touching"));
        event.getRegistry().registerAll(new NoDurabilityEnchantment(UNCOMMON, MAINHAND).setFakeName("unbreaking").setName("breaking").setRegistryName("breaking"));
        event.getRegistry().registerAll(new ArrowNoDamageEnchantment(COMMON, MAINHAND).setFakeName("power").setName("powerless").setRegistryName("powerless"));
        event.getRegistry().registerAll(new ArrowKnockForwardEnchantment(RARE, MAINHAND).setFakeName("punch").setName("self_punch").setRegistryName("self_punch"));
        event.getRegistry().registerAll(new ArrowWaterEnchantment(RARE, MAINHAND).setFakeName("flame").setName("aqua").setRegistryName("aqua"));
        event.getRegistry().registerAll(new ArrowFiniteEnchantment(VERY_RARE, MAINHAND).setFakeName("infinity").setName("finity").setRegistryName("finity"));
        event.getRegistry().registerAll(new FishingFailEnchantment(RARE, FISHING_ROD, MAINHAND).setFakeName("lure").setName("lureless").setRegistryName("lureless"));
        event.getRegistry().registerAll(new BreakingEnchantment(RARE, EquipmentSlotType.values()).setFakeName("mending").setName("unmending").setRegistryName("unmending"));
        event.getRegistry().registerAll(new VanishingEnchantment(VERY_RARE, EquipmentSlotType.values()).setFakeName("vanishing_curse").setName("vanishing").setRegistryName("vanishing"));
        event.getRegistry().registerAll(new NoPiercingEnchantment(COMMON, EquipmentSlotType.values()).setFakeName("piercing").setName("unpiercing").setRegistryName("unpiercing"));
        event.getRegistry().registerAll(new SlowChargeEnchantment(UNCOMMON, EquipmentSlotType.values()).setFakeName("quick_charge").setName("slow_charge").setRegistryName("slow_charge"));
        event.getRegistry().registerAll(new UnishotEnchantment(RARE, EquipmentSlotType.values()).setFakeName("multishot").setName("unishot").setRegistryName("unishot"));
        event.getRegistry().registerAll(new SelfChannelingEnchantment(VERY_RARE, EquipmentSlotType.values()).setFakeName("channeling").setName("self_channeling").setRegistryName("self_channeling"));
        event.getRegistry().registerAll(new BounceEnchantment(RARE, EquipmentSlotType.values()).setFakeName("impaling").setName("bounce").setRegistryName("bounce"));
        event.getRegistry().registerAll(new DrynessEnchantment(RARE, EquipmentSlotType.values()).setFakeName("riptide").setName("dryness").setRegistryName("dryness"));
        event.getRegistry().registerAll(new BetrayEnchantment(UNCOMMON, EquipmentSlotType.values()).setFakeName("loyalty").setName("betray").setRegistryName("betray"));
    }
}
