package io.github.razordevs.deep_aether.init;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DASounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, DeepAether.MODID);

	public static final DeferredHolder<SoundEvent, SoundEvent> NABOORU = register("item.music_disc.nabooru");
	public static final DeferredHolder<SoundEvent, SoundEvent> A_MORNING_WISH = register("item.music_disc.a_morning_wish");
	public static final DeferredHolder<SoundEvent, SoundEvent> CYCLONE = register("item.music_disc.cyclone");

	public static final DeferredHolder<SoundEvent, SoundEvent> ATTA = register("item.music_disc.atta");
	public static final DeferredHolder<SoundEvent, SoundEvent> FAENT = register("item.music_disc.faent");
	public static final DeferredHolder<SoundEvent, SoundEvent> HIMININN = register("item.music_disc.himininn");

	public static final DeferredHolder<SoundEvent, SoundEvent> QUAIL_DEATH = register("entity.quail.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> QUAIL_HURT = register("entity.quail.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> QUAIL_AMBIENT = register("entity.quail.ambient");

	public static final DeferredHolder<SoundEvent, SoundEvent> VENOMITE_DEATH = register("entity.venomite.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> VENOMITE_HURT = register("entity.venomite.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> VENOMITE_AMBIENT = register("entity.venomite.ambient");

	public static final DeferredHolder<SoundEvent, SoundEvent> WINDFLY_HURT = register("entity.windfly.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> WINDFLY_AMBIENT = register("entity.windfly.ambient");

	public static final DeferredHolder<SoundEvent, SoundEvent> EOTS_DEATH = register("entity.eots.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> EOTS_HURT = register("entity.eots.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> EOTS_AMBIENT = register("entity.eots.ambient");
	public static final DeferredHolder<SoundEvent, SoundEvent> EOTS_BLOWING = register("entity.eots.blowing");
	public static final DeferredHolder<SoundEvent, SoundEvent> EOTS_SHOOT = register("entity.eots.shoot");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_BOSS_EOTS = register("music.boss.eots");

	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ARMOR_EQUIP_SKYJADE = register("item.armor.equip_skyjade");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ARMOR_EQUIP_STRATUS = register("item.armor.equip_stratus");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ARMOR_EQUIP_STORMFORGED = register("item.armor.equip_stormforged");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_SKYJADE_RING = register("item.accessory.equip_skyjade_ring");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_GRAVITITE_RING = register("item.accessory.equip_gravitite_ring");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_STRATUS_RING = register("item.accessory.equip_stratus_ring");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_SPOOKY_RING = register("item.accessory.equip_spooky_ring");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_SLIDER_EYE = register("item.accessory.equip_slider_eye");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_ABILITY_SLIDER_EYE = register("item.accessory.ability_slider_eye");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_MEDAL_OF_HONOR = register("item.accessory.equip_medal_of_honor");
	public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_AFTERBURNER_FIRES = register("item.tool.afterburner_fires");

	public static final DeferredHolder<SoundEvent, SoundEvent> DEEP_AETHER_MUSIC = register("music.deep_aether");

	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name)));
	}
}