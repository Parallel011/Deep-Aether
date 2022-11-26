package teamrazor.deepaether;

//import com.gildedgames.aether.data.generators.AetherDataGenerators;
import com.gildedgames.aether.block.dispenser.AetherDispenseBehaviors;
import com.gildedgames.aether.data.generators.tags.AetherBlockTagData;
import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;


import teamrazor.deepaether.block.Behaviors.DeepAetherModBlockInteractionBehavior;
import teamrazor.deepaether.block.Behaviors.DeepAetherModDispenseBehaviors;
import teamrazor.deepaether.fluids.DeepAetherModFluidTypes;
import teamrazor.deepaether.init.*;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraft.resources.ResourceLocation;
//import teamrazor.deepaether.tags.DeepAetherBiomeTagData;
import teamrazor.deepaether.tags.DeepAetherBlockTagData;
import teamrazor.deepaether.tags.DeepAetherItemTagData;
//import teamrazor.deepaether.world.DeepAetherModBiomeBuilders;
//import teamrazor.deepaether.world.DeepAetherModDataGenerators;


import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;
import teamrazor.deepaether.world.feature.tree.decorators.DeepAetherDecoratorType;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerBlobFoliagePlacer;

import java.util.stream.Collectors;

@Mod("deep_aether")
public class DeepAetherMod {
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final String MODID = "deep_aether";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public DeepAetherMod() {
		DeepAetherModTabs.load();
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);



		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();


		bus.addListener(this::commonSetup);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		GeckoLib.initialize();

		DeepAetherModEntities.REGISTRY.register(bus);
		DeepAetherModSounds.REGISTRY.register(bus);
		DeepAetherModFluids.register(bus);
		DeepAetherModFluidTypes.register(bus);
		DeepAetherDecoratorType.REGISTRY.register(bus);
		FlowerBlobFoliagePlacer.REGISTRY.register(bus);
		DeepAetherModPlacedFeatures.register(bus);

		//DeepAetherModBiomes.REGISTRY.register(bus);
		//DeepAetherModBiomes.registerBiomes();

	}



	private void setup(final FMLCommonSetupEvent event)
	{
		event.enqueueWork(() ->
		{
			// Given we only add two biomes, we should keep our weight relatively low.
			//Regions.register(new DeepAetherModRegion(new ResourceLocation(MODID, "overworld"), 2));

			// Register our surface rules
			//SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, DeepAetherModSurfaceRuleData.makeRules());
		});
	}

	public void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		AetherBlockTagData blockTags = new AetherBlockTagData(generator, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new DeepAetherItemTagData(generator, blockTags, helper));
		generator.addProvider(event.includeServer(), new DeepAetherBlockTagData(generator, helper));
		// generator.addProvider(event.includeServer(), new DeepAetherBiomeTagData(generator, helper));
		//generator.addProvider(event.includeServer(), DeepAetherModDataGenerators.levelStem(generator, helper));
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		registerDispenserBehaviors();

	}

		private void enqueueIMC(final InterModEnqueueEvent event)
	{
		// Some example code to dispatch IMC to another mod
		InterModComms.sendTo(MODID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event)
	{
		// Some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m->m.messageSupplier().get()).
				collect(Collectors.toList()));
	}
	private void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(Items.POTION, DeepAetherModDispenseBehaviors.WATER_BOTTLE_TO_AETHER_MUD_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DeepAetherModItems.PLACEABLE_POISON_BUCKET.get(), DeepAetherModDispenseBehaviors.DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DeepAetherModItems.VIRULENT_QUICKSAND_BUCKET.get(), DeepAetherModDispenseBehaviors.DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DeepAetherModItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get(), AetherDispenseBehaviors.SKYROOT_BUCKET_PICKUP_BEHAVIOR);
		DispenserBlock.registerBehavior(DeepAetherModItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get(), AetherDispenseBehaviors.SKYROOT_BUCKET_DISPENSE_BEHAVIOR);
	}
}
