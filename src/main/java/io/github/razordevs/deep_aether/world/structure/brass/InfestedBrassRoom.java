package io.github.razordevs.deep_aether.world.structure.brass;

import io.github.razordevs.deep_aether.world.structure.DAStructurePieceTypes;
import io.github.razordevs.deep_aether.world.structure.brass.processor.BrassDungeonRoomProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class InfestedBrassRoom extends AbstractBrassRoom {
    public InfestedBrassRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, Holder<StructureProcessorList> processors) {
        super(DAStructurePieceTypes.INFESTED_BRASS_ROOM.get(), manager, name,
                makeSettingsWithPivot(makeSettings(), rotation), pos, processors);
    }

    public InfestedBrassRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.INFESTED_BRASS_ROOM.get(), context.registryAccess(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings());
    }

    protected static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings();
    }

    public static class BossRoom extends AbstractBossRoom {
        public BossRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, Holder<StructureProcessorList> processors) {
            super(DAStructurePieceTypes.INFESTED_BRASS_BOSS_ROOM.get(), manager, name,
                    makeSettingsWithPivot(makeSettings(), rotation), pos, processors);
        }

        public BossRoom(StructurePieceSerializationContext context, CompoundTag tag) {
            super(DAStructurePieceTypes.INFESTED_BRASS_BOSS_ROOM.get(), context.registryAccess(), tag, context.structureTemplateManager(), resourceLocation
                    -> makeSettings());
        }

        protected static StructurePlaceSettings makeSettings() {
            return InfestedBrassRoom.makeSettings()
                    .setFinalizeEntities(true);
        }
    }
}