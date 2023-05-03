
package teamrazor.deepaether.item.gear.skyjade;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import teamrazor.deepaether.tags.SkyjadeTool;

public class SkyjadeToolsAxeItem extends AxeItem implements SkyjadeTool {
	public SkyjadeToolsAxeItem(Tier tier, int i, float v, Properties properties) {
		super(tier, i, v, properties);
	}

	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}
}
