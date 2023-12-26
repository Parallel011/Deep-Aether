package teamrazor.deepaether.mixin.flawless;


import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import teamrazor.deepaether.entity.IPlayerBossFight;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Used in the flawless boss check
 * See {@link teamrazor.deepaether.entity.IFlawlessBossDrop}
 * See {@link teamrazor.deepaether.event.DAGeneralEvents}
 * See {@link SliderMixin}
 * See {@link ValkyrieQueenMixin}
 * See {@link SunSpiritMixin}
 */
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements IPlayerBossFight {



    public ServerPlayerMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Override
    @Nullable
    public Entity deep_Aether$getBoss() {
        return deep_Aether$getOwner();
    }

    @Override
    public void deep_Aether$setBoss(@Nullable Entity entity) {
        this.deep_Aether$setOwner(entity);
    }

    /**
     * Copied From {@link Projectile}
     * Used to store and get a boss from a boss battle by its UUID.
     */
    @Unique
    @Nullable
    private UUID deep_Aether$ownerUUID;
    @Unique
    @Nullable
    private Entity deep_Aether$cachedOwner;
    @Unique
    public void deep_Aether$setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.deep_Aether$ownerUUID = entity.getUUID();
            this.deep_Aether$cachedOwner = entity;
        }

    }

    @Unique
    @Nullable
    public Entity deep_Aether$getOwner() {
        if (this.deep_Aether$cachedOwner != null && !this.deep_Aether$cachedOwner.isRemoved()) {
            return this.deep_Aether$cachedOwner;
        } else if (this.deep_Aether$ownerUUID != null && this.level() instanceof ServerLevel) {
            this.deep_Aether$cachedOwner = ((ServerLevel)this.level()).getEntity(this.deep_Aether$ownerUUID);
            return this.deep_Aether$cachedOwner;
        } else {
            return null;
        }
    }
}
