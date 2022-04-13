package com.kattalist.kattsornithology.common.entity;

import java.util.Random;

import com.kattalist.kattsornithology.common.entity.ai.control.BirdFlightController;
import com.kattalist.kattsornithology.common.entity.ai.goal.HummingbirdDartAroundGoal;
import com.kattalist.kattsornithology.common.entity.ai.goal.SeekFlowerGoal;
import com.kattalist.kattsornithology.core.init.EntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class HummingbirdEntity extends Animal implements FlyingAnimal {

	public int currentFlowerTimer = 0;

	public HummingbirdEntity(EntityType<? extends Animal> entityType, Level level) {
		super(entityType, level);
		this.moveControl = new BirdFlightController(this, 4.0D);
		this.setNoGravity(true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new SeekFlowerGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new HummingbirdDartAroundGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, 4D)
				.add(Attributes.MOVEMENT_SPEED, 0.45D);
		
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.isAtFlower()) {
			currentFlowerTimer++;
		} else {
			currentFlowerTimer = 0;
		}
		Vec3 vec3 = this.getDeltaMovement();
	}

	@Override
	public boolean isFlying() {
		return !this.isOnGround();
	}

	public boolean isAtFlower() {
		for (int y = 0; y < 2; y++) {
			BlockPos checkPos = this.blockPosition().below(y);
			if (this.level.getBlockState(checkPos).is(BlockTags.FLOWERS)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
		return EntityInit.HUMMINGBIRD.get().create(level);
	}

	@Override
	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	public static boolean checkHummingbirdSpawnRules(EntityType<HummingbirdEntity> p_29424_, LevelAccessor p_29425_,
			MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
		BlockState blockstate = p_29425_.getBlockState(p_29427_.below());
		return (blockstate.is(BlockTags.LEAVES) || blockstate.is(BlockTags.LOGS) || blockstate.is(Blocks.AIR))
				&& p_29425_.getRawBrightness(p_29427_, 0) > 8;
	}

}
