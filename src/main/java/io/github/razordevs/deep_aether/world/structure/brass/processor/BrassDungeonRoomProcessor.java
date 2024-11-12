package io.github.razordevs.deep_aether.world.structure.brass.processor;

import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.world.structure.processor.DAStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;

public class BrassDungeonRoomProcessor extends StructureProcessor {
    public static final BrassDungeonRoomProcessor INSTANCE = new BrassDungeonRoomProcessor();

    public static final MapCodec<BrassDungeonRoomProcessor> CODEC = MapCodec.unit(BrassDungeonRoomProcessor.INSTANCE);

    @Override
    public StructureTemplate.StructureEntityInfo processEntity(LevelReader level, BlockPos seedPos, StructureTemplate.StructureEntityInfo rawEntityInfo, StructureTemplate.StructureEntityInfo entityInfo, StructurePlaceSettings placementSettings, StructureTemplate template) {
        BlockPos pos = BlockPos.containing(entityInfo.pos);

        BossRoomTracker<?> tracker = new BossRoomTracker<>(null,
                entityInfo.pos,
                new AABB(pos.getX()- 21, pos.getY() -8, pos.getZ() - 21, pos.getX() + 22, pos.getY() + 32, pos.getZ() + 22),
                new ArrayList<>());
        entityInfo.nbt.put("Dungeon", tracker.addAdditionalSaveData());
        return super.processEntity(level, seedPos, rawEntityInfo, entityInfo, placementSettings, template);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return DAStructureProcessor.BOSS_ROOM.get();
    }
}