package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.DeepAetherConfig;
import io.github.razordevs.deep_aether.init.DATiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SkyjadeToolsSwordItem extends SwordItem implements SkyjadeTool, SkyjadeWeapon {
	public SkyjadeToolsSwordItem() {
		super(DATiers.SKYJADE, new Properties().attributes(SwordItem.createAttributes(DATiers.SKYJADE, 3, -3f)));
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		this.disableSound(player, pos);
		return super.canAttackBlock(state, level, pos, player);
	}

	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return DeepAetherConfig.COMMON.skyjade_enchant.get() && !DeepAetherConfig.COMMON.enable_skyjade_rework.get();
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return DeepAetherConfig.COMMON.skyjade_enchant.get() && !DeepAetherConfig.COMMON.enable_skyjade_rework.get();
	}
}
