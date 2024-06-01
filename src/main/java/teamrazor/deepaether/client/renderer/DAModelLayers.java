package teamrazor.deepaether.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAether;

public class DAModelLayers {
    public static final ModelLayerLocation AERGLOW_FISH = register("aether_fish");
    public static final ModelLayerLocation EOTS_SEGMENT = register("eots_segment_head");
    public static final ModelLayerLocation VENOMITE_BUBBLE = register("venomite_bubble");
    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return register(new ResourceLocation(DeepAether.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation identifier, String type) {
        return new ModelLayerLocation(identifier, type);
    }
}
