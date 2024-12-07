package io.github.razordevs.deep_aether.world.structure.brass;

import com.aetherteam.aether.world.structurepiece.AetherTemplateStructurePiece;
import com.google.common.collect.ImmutableList;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.block.misc.TrappedSkyrootBlock;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.function.Function;

public class BrassDungeonPiece extends AetherTemplateStructurePiece {
    public static final RuleProcessor LOCKED_NIMBUS_STONE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_NIMBUS_STONE.get(), 0.1F), AlwaysTrueTest.INSTANCE, DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get().defaultBlockState())
    ));

    public static final RuleProcessor TRAPPED_NIMBUS_STONE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_NIMBUS_STONE.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_NIMBUS_STONE.get().defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_LIGHT_NIMBUS_STONE.get().defaultBlockState())
    ));

    public static final RuleProcessor TRAPPED_SKYROOT_PLANKS_NORMAL = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState())
    ));

    public static final RuleProcessor TRAPPED_SKYROOT_PLANKS_PLANT = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState().setValue(TrappedSkyrootBlock.TRAPPED_MOB_TYPE, 1))
    ));

    public static final RuleProcessor TRAPPED_SKYROOT_PLANKS_COCKATRICE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState().setValue(TrappedSkyrootBlock.TRAPPED_MOB_TYPE, 2))
    ));
    public BrassDungeonPiece(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos, Holder<StructureProcessorList> processors) {
        super(type, manager, makeLocation(name), settings, pos, processors);
    }

    public BrassDungeonPiece(StructurePieceType type, RegistryAccess access, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
        super(type, access, tag, manager, settingsFactory);
    }

    protected static ResourceLocation makeLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "brass_dungeon/" + name);
    }
}