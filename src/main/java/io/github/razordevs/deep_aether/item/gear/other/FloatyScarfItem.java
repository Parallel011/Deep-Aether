package io.github.razordevs.deep_aether.item.gear.other;

import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import io.github.razordevs.deep_aether.entity.living.GentleWind;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.FloatyScarf;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FloatyScarfItem extends PendantItem implements FlawlessDrop {
    public FloatyScarfItem(ResourceLocation pendantLocation, Holder<SoundEvent> pendantSound, Properties properties) {
        super(pendantLocation, pendantSound, properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if(reference.entity() instanceof Player player) {
            tryAddGentleWind(stack, player);
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if(!(reference.entity() instanceof Player player))
            return;

        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if(scarf == null)
            return;

        Entity entity = getGentleWind(stack, reference.entity().level());
        if(entity == null || !entity.isAlive()) {
            FloatyScarfItem.tryAddGentleWind(stack, player);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        tryDiscardGentleWind(stack, reference.entity().level());
    }

    public static void tryDiscardGentleWind(@Nullable ItemStack stack, Level level) {
        if(stack == null)
            return;
        Entity entity = getGentleWind(stack, level);
        if(entity != null)
            entity.discard();
    }

    public static void tryAddGentleWind(@Nullable ItemStack stack, Player player) {
        if(stack == null)
            return;

        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if(scarf == null) {
            scarf = FloatyScarf.withDefaultColor(0);
        }
        GentleWind eots = new GentleWind(player.level(), player, scarf.colors());
        Component component = stack.get(DataComponents.CUSTOM_NAME);
        if (component != null) {
            eots.setCustomName(component);
        }
        stack.set(DADataComponentTypes.FLOATY_SCARF, new FloatyScarf(eots.getId(), scarf.colors(), scarf.currentModification()));
    }

    public static Entity getGentleWind(ItemStack stack, Level level){
        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        return scarf != null ? level.getEntity(scarf.uuid()) : null;
    }

    @Nullable
    public GentleWind hasStoredGentleWind(Level level, ItemStack stack) {
        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if(scarf == null)
            return null;

        GentleWind entity = (GentleWind) level.getEntity(scarf.uuid());
        if(entity == null || !entity.isAlive()) {
            return null;
        }
        return entity;
    }

    int i = 0;
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        flawlessComponent(tooltipComponents, i);
        i = i < 80 ? i + 1 : 0;
        if (!tooltipFlag.hasShiftDown()) {
            return;
        }

        chatFormat(tooltipComponents, 0, stack);
        chatFormat(tooltipComponents, 1, stack);
        chatFormat(tooltipComponents, 2, stack);
        chatFormat(tooltipComponents, 3, stack);
        chatFormat(tooltipComponents, 4, stack);
    }

    private void chatFormat(List<Component> tooltipComponents, int color, ItemStack stack) {
        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if (scarf != null) {
            if (color == scarf.currentModification())
                tooltipComponents.add(Component.literal("Color").withColor(scarf.colors().get(color)).withStyle(ChatFormatting.ITALIC));
            else tooltipComponents.add(Component.literal("Color").withColor(scarf.colors().get(color)));
        }
    }
}
