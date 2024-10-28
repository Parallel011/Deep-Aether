package io.github.razordevs.deep_aether.datagen.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import io.github.razordevs.deep_aether.datagen.DAEnchantments;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record DAChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        HolderLookup.RegistryLookup<Enchantment> lookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        builder.accept(DALoot.BRASS_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_LOOT).setWeight(8))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_DISC).setWeight(1))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_TRASH).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_PICKAXE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_AXE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_SWORD.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.STERLING_AERCLOUD.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ICE_PENDANT.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_SHOVEL.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_HOE.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 10.0F))))
                        .add(LootItem.lootTableItem(AetherItems.ENCHANTED_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                        .add(LootItem.lootTableItem(AetherItems.POISON_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.IRON_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.HOLYSTONE_PICKAXE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.BLUE_AERCLOUD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
                        .add(LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                        .add(LootItem.lootTableItem(DAItems.GOLDEN_BERRIES.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.FROZEN_GOLDEN_BERRIES.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.ANTIDOTE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.ROSEROOT_PLANKS.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_TRASH, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD).setWeight(3))
                        .add(LootItem.lootTableItem(AetherBlocks.BLUE_AERCLOUD).setWeight(2))
                        .add(LootItem.lootTableItem(AetherBlocks.GOLDEN_AERCLOUD).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.ROSEROOT_PLANKS.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_DISC, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(AetherItems.MUSIC_DISC_AETHER_TUNE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 10.0F))
                        .add(LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 16.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_PICKAXE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(2))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_STORM_FORGED).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_TREASURE).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_GUMMIES).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 5.0F))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_HELMET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_CHESTPLATE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_LEGGINGS.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_BOOTS.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_GLOVES.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_RING.get()).setWeight(1))
                        .add(LootItem.lootTableItem(Items.SADDLE).setWeight(1))
                        //.add(LootItem.lootTableItem(AetherItems.SHIELD_OF_REPULSION.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.CLOUD_STAFF.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.STORM_BOW.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.STORM_SWORD.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.CLOUD_CAPE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.BLADE_OF_LUCK.get()).setWeight(1))
                        //.add(LootItem.lootTableItem(AetherItems.HAMMER_OF_KINGBDOGZ.get()).setWeight(1))
                        .add(NestedLootTable.lootTableReference(DALoot.BRASS_DUNGEON_STORM_FORGED).setWeight(10))
                        //.add(LootItem.lootTableItem(AetherItems.SENTRY_BOOTS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_PILLAR.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 15.0F))
                        //.add(LootItem.lootTableItem(AetherItems.LIGHTNING_KNIFE.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).setWeight(3))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_PILLAR.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_TREASURE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(DAItems.CLOUD_CAPE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.AERCLOUD_NECKLACE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(lookup.getOrThrow(DAEnchantments.GLOVES_REACH))).setWeight(3))
        ));
        builder.accept(DALoot.BRASS_DUNGEON_STORM_FORGED, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_HELMET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_CHESTPLATE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_LEGGINGS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_BOOTS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_GLOVES.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_GUMMIES, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_GUMMY_SWET.get()).setWeight(2))
                )
        );

        builder.accept(DALoot.ALTAR_CAMP, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_AXE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_HOE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.COLD_PARACHUTE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART_SHOOTER.get()).setWeight(1))
                )
        );
    }
}
