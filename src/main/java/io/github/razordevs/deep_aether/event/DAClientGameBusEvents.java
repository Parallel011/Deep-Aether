package io.github.razordevs.deep_aether.event;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.screen.SnapshotScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = DeepAether.MODID,  value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class DAClientGameBusEvents {
    private static boolean hasShownScreen = false;

    // Fog effect to mimic PowderSnow behaviour
    @SubscribeEvent
    public static void fogDensityEvent(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null) {
            if (mc.level != null) {
                BlockState state = mc.level.getBlockState(new BlockPos(new Vec3i(player.getBlockX(), (int) player.getEyeY(), player.getBlockZ())));
                if (state.is(DABlocks.VIRULENT_QUICKSAND.get())) {
                    event.setNearPlaneDistance(0.5f);
                    event.setFarPlaneDistance(1.8f);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onGuiOpen(ScreenEvent.Opening event) {
        if(DeepAether.MOD_VERSION.contains("snapshot")){
            Screen screen = event.getScreen();
            if (screen instanceof TitleScreen title && !hasShownScreen) {
                event.setNewScreen(new SnapshotScreen(title));
                hasShownScreen = true;
            }
        }
    }

//    @SubscribeEvent(priority = EventPriority.HIGH)
//    public static void onGuiOpenHighest(ScreenEvent.Opening event) {
//        CumulusClient.MENU_HELPER.prepareMenu(DAMenus.DEEP_AETHER.get());
//        CumulusClient.MENU_HELPER.prepareMenu(DAMenus.DEEP_AETHER_LEFT.get());
//    }
}
