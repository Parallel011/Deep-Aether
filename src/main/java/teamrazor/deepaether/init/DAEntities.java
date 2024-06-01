package teamrazor.deepaether.init;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.*;
import teamrazor.deepaether.entity.living.AerglowFish;
import teamrazor.deepaether.entity.living.Venomite;
import teamrazor.deepaether.entity.living.Windfly;
import teamrazor.deepaether.entity.living.boss.eots.EOTSController;
import teamrazor.deepaether.entity.living.boss.eots.EOTSSegment;
import teamrazor.deepaether.entity.living.projectile.FireProjectile;
import teamrazor.deepaether.entity.living.projectile.VenomiteBubble;
import teamrazor.deepaether.entity.living.quail.Quail;
import teamrazor.deepaether.entity.living.projectile.ThrownQuailEgg;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, DeepAether.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<DABoatEntity>> BOAT = ENTITY_TYPES.register("boat",
			() -> EntityType.Builder.<DABoatEntity>of(DABoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

	public static final DeferredHolder<EntityType<?>, EntityType<DAChestBoatEntity>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat",
			() -> EntityType.Builder.<DAChestBoatEntity>of(DAChestBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));

	public static final DeferredHolder<EntityType<?>,EntityType<ThrownQuailEgg>> QUAIL_EGG = ENTITY_TYPES.register("quail_egg",
			() -> EntityType.Builder.<ThrownQuailEgg>of(ThrownQuailEgg::new, MobCategory.MISC)
					.sized(0.25F, 0.25F)
					.clientTrackingRange(4)
					.updateInterval(10)
					.build("quail_egg"));

	public static final DeferredHolder<EntityType<?>,EntityType<AerglowFish>> AETHER_FISH = register("aerglow_fish",
			EntityType.Builder.of(AerglowFish::new, MobCategory.WATER_CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.5f, 0.5f));

	public static final DeferredHolder<EntityType<?>,EntityType<Quail>> QUAIL = register("quail",
			Quail::new, 0.35F, 0.7f);

	public static final DeferredHolder<EntityType<?>,EntityType<Venomite>> VENOMITE = register("venomite",
			Venomite::new, 0.7F, 0.6F);

	public static final DeferredHolder<EntityType<?>,EntityType<EOTSController>> EOTS_CONTROLLER = register("eots_controller",
			EOTSController::new, 1F, 1F);

	public static final DeferredHolder<EntityType<?>,EntityType<EOTSSegment>> EOTS_SEGMENT = register("eots_segment",
			EOTSSegment::new, 1F, 1F);


	public static final DeferredHolder<EntityType<?>,EntityType<Windfly>> WINDFLY = register("windfly", Windfly::new, 1F, 1F);

	public static final DeferredHolder<EntityType<?>,EntityType<FireProjectile>> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile",
			() -> EntityType.Builder.<FireProjectile>of(FireProjectile::new, MobCategory.MISC).sized(0.35F, 0.35F).clientTrackingRange(4).updateInterval(10).build("fire_projectile"));

	public static final DeferredHolder<EntityType<?>,EntityType<VenomiteBubble>> VENOMITE_BUBBLE = ENTITY_TYPES.register("venomite_bubble",
			() -> EntityType.Builder.<VenomiteBubble>of(VenomiteBubble::new, MobCategory.MISC).sized(0.35F, 0.2F).clientTrackingRange(4).updateInterval(10).build("venomite_bubble"));


	private static <T extends Entity> DeferredHolder<EntityType<?>,EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return ENTITY_TYPES.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, EntityType.EntityFactory<T> entity, float width, float height) {
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AerglowFish.createAttributes();
			Quail.init();
			Venomite.init();
			Windfly.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(AETHER_FISH.get(), AerglowFish.createAttributes().build());
		event.put(QUAIL.get(), Quail.createAttributes().build());
		event.put(VENOMITE.get(), Venomite.createAttributes().build());
		event.put(EOTS_SEGMENT.get(), EOTSController.createMobAttributes().build());
		event.put(EOTS_CONTROLLER.get(), EOTSController.createMobAttributes().build());
		event.put(WINDFLY.get(), Windfly.createAttributes().build());
	}
}