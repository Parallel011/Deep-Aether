package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class SpookyRing extends RingItem {
    public static final UUID SPOOKY_RING_UUID = UUID.fromString("48934393-2a67-4bd1-b5bd-88c18538cee1");

    public SpookyRing(Supplier<? extends SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }

    Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();

    private int calculateIncrease(SlotContext context) {
        Level level = context.entity().level();
        int a = (int) level.getDayTime();
        if (level.isNight() && a < 13000) {
            return Mth.abs(a - 18000) / 2000;
        } else return 0;
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        AttributeModifier attribute = slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).getModifier(SPOOKY_RING_UUID);
        if (attribute != null) {

            slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(SPOOKY_RING_UUID);
            attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(SPOOKY_RING_UUID, "Gloves Damage Bonus", this.calculateIncrease(slotContext), AttributeModifier.Operation.ADDITION));

        }
        else
            attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(SPOOKY_RING_UUID, "Gloves Damage Bonus", this.calculateIncrease(slotContext), AttributeModifier.Operation.ADDITION));

        System.out.println(this.calculateIncrease(slotContext));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext, prevStack, stack);
        SpookyMoonConditions(slotContext);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        SpookyMoonConditions(slotContext);
    }

    //Little easter egg
    public static void SpookyMoonConditions(SlotContext slotContext) {
        Entity entity = slotContext.entity();
        Level level = entity.level();
        if(EquipmentUtil.hasTwoSpookyRings(slotContext) && DeepAetherMod.IS_HALLOWEEN_CONTENT_ENABLED /*level.getMoonPhase() == 0 && DeepAetherMod.IS_HALLOWEEN*/) {
            LevelRenderer.MOON_LOCATION = new ResourceLocation(DeepAetherMod.MODID,"textures/environment/spooky_moon_phases.png");
        }
        else LevelRenderer.MOON_LOCATION = new ResourceLocation("textures/environment/moon_phases.png");
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tagTooltips, ItemStack stack) {
        tagTooltips.add(Component.translatable("gui.deep_aether.spooky_ring").withStyle(ChatFormatting.DARK_RED));
        return super.getAttributesTooltip(tagTooltips, stack);
    }
}
