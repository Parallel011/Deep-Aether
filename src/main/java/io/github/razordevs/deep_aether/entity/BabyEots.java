package io.github.razordevs.deep_aether.entity;

import io.github.razordevs.deep_aether.entity.living.projectile.WindCrystal;
import io.github.razordevs.deep_aether.init.DAEntities;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BabyEots extends FlyingMob {

    private static final EntityDataAccessor<Integer> DATA_OWNER_ID = SynchedEntityData.defineId(BabyEots.class, EntityDataSerializers.INT);

    public BabyEots(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.moveControl = new BabyEotsMoveControl(this);
        this.lookControl = new BabyEotsLookControl(this);
    }

    public BabyEots(Level level, Player owner) {
        this(DAEntities.BABY_EOTS.get(), level);
        this.setOwner(owner);
        this.setPos(owner.position());
        level.addFreshEntity(this);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return FlyingMob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0).add(Attributes.MOVEMENT_SPEED, 10.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_OWNER_ID, 0);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new BabyEotsOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new BabyEotsOwnerHurtTargetGoal(this));
        //this.goalSelector.addGoal(1, new BabyEotsMeeleGoal(this));
        this.goalSelector.addGoal(2, new BabyEotsAirChargeGoal(this));
        this.goalSelector.addGoal(3, new FollowPlayerGoal(this));
    }

    protected void pushEntities() {
    }

    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public boolean hurt(DamageSource source, float damage) {
        return false;
    }

    @Nullable
    public Player getOwner() {
        return (Player)this.level().getEntity(this.getEntityData().get(DATA_OWNER_ID));
    }

    public void setOwner(Player entity) {
        this.getEntityData().set(DATA_OWNER_ID, entity.getId());
    }

    public void setOwner(int entity) {
        this.getEntityData().set(DATA_OWNER_ID, entity);
    }

    private void followOwner() {
        Player player = this.getOwner();
        if(player != null) {
            this.moveControl.setWantedPosition(player.getX(), player.getY() + 3, player.getZ(), 1.0F);
        }
    }

    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        if (target instanceof Creeper || target instanceof Ghast || target instanceof ArmorStand || target instanceof BabyEots) {
            return false;
        } else if (target instanceof Wolf wolf) {
            return !wolf.isTame() || wolf.getOwner() != owner;
        } else {
            if (target instanceof Player player && owner instanceof Player player1 && !player1.canHarmPlayer(player)) {
                return false;
            }

            if (target instanceof AbstractHorse abstracthorse && abstracthorse.isTamed()) {
                return false;
            }

            return !(target instanceof TamableAnimal tamableanimal) || !tamableanimal.isTame();
        }
    }

    @Override
    public boolean canUsePortal(boolean use) {
        return false;
    }

    @Override
    public void load(CompoundTag tag) {
        this.setOwner(0);
        this.discard();
    }

    public static class FollowPlayerGoal extends Goal {
        private final BabyEots eots;

        public FollowPlayerGoal(BabyEots eots) {
            this.eots = eots;
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.eots.getOwner();
            if (livingentity == null) {
                this.eots.discard();
                return false;
            } else {
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            this.eots.followOwner();
        }
    }

    public static class BabyEotsOwnerHurtByTargetGoal extends TargetGoal {
        private final BabyEots eots;
        private LivingEntity ownerLastHurtBy;
        private int timestamp;

        public BabyEotsOwnerHurtByTargetGoal(BabyEots eots) {
            super(eots, false);
            this.eots = eots;
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.eots.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurtBy = livingentity.getLastHurtByMob();
                int i = livingentity.getLastHurtByMobTimestamp();
                return i != this.timestamp
                        && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT)
                        && this.eots.wantsToAttack(this.ownerLastHurtBy, livingentity);
            }
        }

        @Override
        public void start() {
            this.mob.setTarget(this.ownerLastHurtBy);
            LivingEntity livingentity = this.eots.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtByMobTimestamp();
            }

            super.start();
        }
    }

    public static class BabyEotsOwnerHurtTargetGoal extends TargetGoal {
        private final BabyEots eots;
        private LivingEntity ownerLastHurt;
        private int timestamp;

        public BabyEotsOwnerHurtTargetGoal(BabyEots eots) {
            super(eots, false);
            this.eots = eots;
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.eots.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurt = livingentity.getLastHurtMob();
                int i = livingentity.getLastHurtMobTimestamp();
                return i != this.timestamp
                        && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT)
                        && this.eots.wantsToAttack(this.ownerLastHurt, livingentity);
            }
        }

        @Override
        public void start() {
            this.mob.setTarget(this.ownerLastHurt);
            LivingEntity livingentity = this.eots.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtMobTimestamp();
            }

            super.start();
        }
    }

    public static class BabyEotsMeeleGoal extends Goal {
        int cooldown = 20;
        private final BabyEots eots;

        public BabyEotsMeeleGoal(BabyEots eots) {
            this.eots = eots;
        }

        @Override
        public boolean canUse() {
            if(eots.getTarget() == null || !eots.getTarget().isAlive())
                return false;
            else if(cooldown > 0) {
                cooldown--;
                return false;
            }
            else {
                cooldown = eots.random.nextInt(20) + 20;
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.eots.getTarget();
            if (livingentity == null || !livingentity.isAlive()) {
                return false;
            }
            else return this.eots.isWithinRestriction(livingentity.blockPosition());
        }

        @Override
        public void start() {
            this.eots.setAggressive(true);
        }

        @Override
        public void stop() {
            this.eots.setAggressive(false);
            this.eots.followOwner();
        }

        @Override
        public void tick() {
            LivingEntity living = eots.getTarget();
            if(living != null) {
                Vec3 target = this.eots.getTarget().position().add(0, 1.0F,0);
                this.eots.getMoveControl().setWantedPosition(target.x(), target.y(), target.z(), 2.0);
                if (this.eots.position().distanceToSqr(target) < 2.0F) {
                    living.hurt(this.eots.level().damageSources().mobAttack(this.eots), 4.0F);
                }
            }
        }
    }

    protected static class BabyEotsAirChargeGoal extends Goal {
        BabyEots eots;
        private int attackTimer = 10; //Delay between attacks
        private int attackDelay = 10; //Gives the segment time to rotate against the player before attacking
        private int numberOfAttacks = 0;
        public BabyEotsAirChargeGoal(BabyEots eots) {
            this.eots = eots;
        }

        @Override
        public boolean canUse() {
            if(this.eots.getTarget() == null || !this.eots.getTarget().isAlive() || !this.eots.hasLineOfSight(this.eots.getTarget()))
                return false;
            else if (this.attackTimer > 0) {
                --this.attackTimer;
                return false;
            } else {
                this.attackTimer = 2 + this.eots.getRandom().nextInt(10);
                return true;
            }
        }


        @Override
        public boolean canContinueToUse() {
            if(attackDelay < -2) {
                return false;
            }
            LivingEntity livingentity = this.eots.getTarget();
            return livingentity != null && livingentity.isAlive();
        }

        @Override
        public void start() {
            this.attackDelay = 10;
            this.numberOfAttacks = this.eots.random.nextInt(2);
            super.start();
        }


        @Override
        public void tick() {
            if(this.eots.getTarget() != null) {
                this.lookAt(this.eots.getTarget());
                if(attackDelay <= 0) {
                    new WindCrystal(this.eots.level(), this.eots, this.eots.getLookAngle().multiply(0.7F,0.7F,0.7F).offsetRandom(this.eots.random, 0.3F), true);
                    this.eots.level().playSound(null, this.eots.getX(), this.eots.getY(), this.eots.getZ(), DASounds.EOTS_SHOOT, SoundSource.HOSTILE, 2.0F, 1.0F);
                    if(numberOfAttacks > 0) {
                        numberOfAttacks--;
                        attackDelay = 9;
                    }
                    else attackDelay = -3;
                }
                else attackDelay--;
            }
        }

        private void lookAt(LivingEntity target) {
            double d0 = target.getX() - this.eots.getX();
            double d1 = target.getEyeY() - this.eots.getEyeY();
            double d2 = target.getZ() - this.eots.getZ();

            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            this.eots.setXRot(Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * 180.0F / (float)Math.PI))));
            this.eots.setYRot(Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * 180.0F / (float)Math.PI) - 90.0F));

            this.eots.setYHeadRot(this.eots.getYRot());
            this.eots.xRotO = this.eots.getXRot();
            this.eots.yRotO = this.eots.getYRot();
        }
    }

    public static class BabyEotsMoveControl extends MoveControl {
        private float speed = 0.1F;

        private final BabyEots eots;

        public BabyEotsMoveControl(BabyEots eots) {
            super(eots);
            this.eots = eots;
        }

        @Override
        public void tick() {
            if (this.eots.horizontalCollision) {
                this.eots.setYRot(this.eots.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d0 = this.getWantedX() - this.eots.getX();
            double d1 = this.getWantedY() - this.eots.getY();
            double d2 = this.getWantedZ() - this.eots.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d3) > 1.0E-5F) {
                double d4 = 1.0 - Math.abs(d1 * 0.7F) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                float f = this.eots.getYRot();
                float f1 = (float)Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(this.eots.getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * (180.0F / (float)Math.PI));
                this.eots.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                this.eots.yBodyRot = this.eots.getYRot();
                if (Mth.degreesDifferenceAbs(f, this.eots.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float f4 = (float)(-(Mth.atan2(-d1, d3) * 180.0F / (float)Math.PI));
                this.eots.setXRot(f4);
                float f5 = this.eots.getYRot() + 90.0F;
                double d6 = (double)(this.speed * Mth.cos(f5 * (float) (Math.PI / 180.0))) * Math.abs(d0 / d5);
                double d7 = (double)(this.speed * Mth.sin(f5 * (float) (Math.PI / 180.0))) * Math.abs(d2 / d5);
                double d8 = (double)(this.speed * Mth.sin(f4 * (float) (Math.PI / 180.0))) * Math.abs(d1 / d5);
                Vec3 vec3 = this.eots.getDeltaMovement();
                this.eots.setDeltaMovement(vec3.add(new Vec3(d6, d8, d7).subtract(vec3).scale(0.2)));
            }
        }
    }


    protected static class BabyEotsLookControl extends LookControl {
        public BabyEotsLookControl(BabyEots eots) {
            super(eots);
        }

        /**
         * Updates look
         */
        @Override
        public void tick() {
        }
    }
}
