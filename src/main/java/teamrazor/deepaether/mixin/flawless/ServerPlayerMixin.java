package teamrazor.deepaether.mixin.flawless;


import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import teamrazor.deepaether.entity.IPlayerBossFight;

import javax.annotation.Nullable;
import java.util.UUID;

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
