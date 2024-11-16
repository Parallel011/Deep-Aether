package io.github.razordevs.deep_aether.client.renderer;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class DAModelLayers {
    public static final ModelLayerLocation AERGLOW_FISH = register("aether_fish");
    public static final ModelLayerLocation EOTS_CONTROLLER = register("eots_controller");
    public static final ModelLayerLocation EOTS_SEGMENT = register("eots_segment_head");
    public static final ModelLayerLocation EOTS_SEGMENT_CLASSIC = register("eots_segment_head_classic");
    public static final ModelLayerLocation BABY_EOTS = register("baby_eots");
    public static final ModelLayerLocation VENOMITE_BUBBLE = register("venomite_bubble");
    public static final ModelLayerLocation VENOMITE = register("venomite");
    public static final ModelLayerLocation QUAIL = register("quail");
    public static final ModelLayerLocation WINDFLY = register("windfly");
    public static final ModelLayerLocation BABY_ZEPHYR = register("baby_zephyr");
    public static final ModelLayerLocation WIND_SHIELD = register("wind_shield");
    public static final ModelLayerLocation WIND_SHIELD_SLIM = register("wind_shield_slim");
    public static final ModelLayerLocation WIND_SHIELD_ARM = register("wind_shield_arm");
    public static final ModelLayerLocation SCARF = register("scarf");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return register(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation identifier, String type) {
        return new ModelLayerLocation(identifier, type);
    }
}
