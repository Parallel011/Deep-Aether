package io.github.razordevs.deep_aether.recipe;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DABookCategory implements StringRepresentable {
    COMBINEABLE_FODDER("combinable_fodder"),
    COMBINEABLE_MISC("combinable_misc"),
    UNKNOWN("unknown");

    /**
     * Warning for "deprecation" is suppressed because using {@link StringRepresentable.EnumCodec} is necessary.
     */
    @SuppressWarnings("deprecation")
    public static final StringRepresentable.EnumCodec<DABookCategory> CODEC = StringRepresentable.fromEnum(DABookCategory::values);
    private final String name;

    DABookCategory(String name) {
        this.name = name;
    }

    @NotNull
    public String getSerializedName() {
        return this.name;
    }
}

