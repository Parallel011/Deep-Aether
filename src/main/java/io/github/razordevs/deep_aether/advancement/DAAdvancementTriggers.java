package io.github.razordevs.deep_aether.advancement;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAAdvancementTriggers {

    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, DeepAether.MODID);
    public static final DeferredHolder<CriterionTrigger<?>, PoisonTrigger> POISON_TRIGGER = TRIGGERS.register("poison_trigger", PoisonTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, KilledTrigger> FLAWLESS_TRIGGER = TRIGGERS.register("flawless_boss_trigger", KilledTrigger::new);
}
