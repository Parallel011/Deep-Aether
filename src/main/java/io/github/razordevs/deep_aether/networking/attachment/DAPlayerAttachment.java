package io.github.razordevs.deep_aether.networking.attachment;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncPacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.razordevs.deep_aether.networking.packet.DAPlayerSyncPacket;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DAPlayerAttachment implements INBTSynchable {
    private boolean isSliderSlamActivated;
    private boolean hasSkyjadeSet;
    private boolean isSkyjadeAbilityActivated;

    private int bladeOfLuckDamage;
    private int oldBladeOfLuckDamage = 6;
    public boolean changeBladeOfLuckState;

    public int windShieldCooldown;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setSliderSlamActivated", Triple.of(Type.BOOLEAN, (object) -> this.setSliderSlamActivated((boolean) object), this::isSliderSlamActivated)),
            Map.entry("hasSkyjadeSet", Triple.of(Type.BOOLEAN, (object) -> this.setHasSkyjadeSet((boolean) object), this::hasSkyjadeSet)),
            Map.entry("setSkyjadeAbilityActivated", Triple.of(Type.BOOLEAN, (object) -> this.setSkyjadeAbilityActivated((boolean) object), this::isSkyjadeAbilityActivated)),
            Map.entry("setBladeOfLuckDamage", Triple.of(Type.INT, (object) -> this.setBladeOfLuckDamage((int) object), this::getBladeOfLuckDamage)),
            Map.entry("setWindShieldCooldown", Triple.of(Type.INT, (object) -> this.setWindShieldCooldown((int) object), this::getWindShieldCooldown))
    );

    public static final Codec<DAPlayerAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("is_slider_slam_activated").forGetter(DAPlayerAttachment::isSliderSlamActivated),
            Codec.BOOL.fieldOf("has_skyjade_set").forGetter(DAPlayerAttachment::isSliderSlamActivated),
            Codec.BOOL.fieldOf("is_skyjade_ability_activated").forGetter(DAPlayerAttachment::isSkyjadeAbilityActivated),
            Codec.INT.fieldOf("get_blade_of_luck_damage").forGetter(DAPlayerAttachment::getBladeOfLuckDamage),
            Codec.INT.fieldOf("get_wind_shield_cooldown").forGetter(DAPlayerAttachment::getWindShieldCooldown)
    ).apply(instance, DAPlayerAttachment::new));

    private DAPlayerAttachment(boolean isSliderSlamActivated, boolean hasSkyjadeSet, boolean isSkyjadeAbilityActivated, int bladeOfLuckDamage, int windShieldCooldown) {
        this.setSliderSlamActivated(isSliderSlamActivated);
        this.setHasSkyjadeSet(hasSkyjadeSet);
        this.setSkyjadeAbilityActivated(isSkyjadeAbilityActivated);
        this.setBladeOfLuckDamage(bladeOfLuckDamage);
        this.setWindShieldCooldown(windShieldCooldown);
    }

    public DAPlayerAttachment() {
        this(false, false, false, 0, 0);
    }

    public boolean isSliderSlamActivated() {
        return isSliderSlamActivated;
    }

    public void setSliderSlamActivated(boolean var1) {
        isSliderSlamActivated = var1;
    }

    public boolean hasSkyjadeSet() {
        return hasSkyjadeSet;
    }

    public void setHasSkyjadeSet(boolean var1) {
        hasSkyjadeSet = var1;
    }

    public boolean isSkyjadeAbilityActivated() {
        return isSkyjadeAbilityActivated;
    }

    public void setSkyjadeAbilityActivated(boolean var1) {
        isSkyjadeAbilityActivated = var1;
    }

    public int getBladeOfLuckDamage() {
        return bladeOfLuckDamage;
    }

    public int getOldBladeOfLuckDamage() {
        return oldBladeOfLuckDamage;
    }

    public void setBladeOfLuckDamage(int bladeOfLuckDamage) {
        this.oldBladeOfLuckDamage = this.getBladeOfLuckDamage();
        changeBladeOfLuckState = true;
        this.bladeOfLuckDamage = bladeOfLuckDamage;
    }

    public void setWindShieldCooldown(int windShieldCooldown) {
        this.windShieldCooldown = windShieldCooldown;
    }

    public int getWindShieldCooldown() {
        return windShieldCooldown;
    }

    public void onJoinLevel(Player player) {
        if (!player.level().isClientSide()) {
            this.setSynched(player.getId(), Direction.CLIENT, "setWindShieldCooldown", getWindShieldCooldown());
        }
    }

    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    public SyncPacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return new DAPlayerSyncPacket(entityID, key, type, value);
    }
}
