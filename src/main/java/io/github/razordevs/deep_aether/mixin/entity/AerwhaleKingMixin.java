package io.github.razordevs.deep_aether.mixin.entity;
/*
import com.aetherteam.aether.entity.AetherBossMob;
import com.legacy.lost_aether.entity.AerwhaleKingEntity;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(value = AerwhaleKingEntity.class)
public abstract class AerwhaleKingMixin implements AetherBossMob<AerwhaleKingEntity> {

    @Inject(at = @At("TAIL"), method = "onDungeonPlayerAdded", remap = false)
    private void onDungeonPlayerAdded(Player player, CallbackInfo ci) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.setData(DAAttachments.PLAYER_BOSS_FIGHT, false);
        }
    }
}

*/