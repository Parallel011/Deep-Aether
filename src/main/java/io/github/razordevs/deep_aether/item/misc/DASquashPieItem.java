package io.github.razordevs.deep_aether.item.misc;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DASquashPieItem extends Item {
    Holder<MobEffect>[] effects;

    public DASquashPieItem(Properties properties, Holder<MobEffect>... effects) {
        super(properties);
        this.effects = effects;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity player) {
        if(!level.isClientSide())
            player.addEffect(new MobEffectInstance(effects[level.random.nextInt(effects.length)], 900));

        return super.finishUsingItem(stack, level, player);
    }
}
