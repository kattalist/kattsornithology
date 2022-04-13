package com.kattalist.kattsornithology.common.entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.kattalist.kattsornithology.common.entity.ai.goal.FlyToTreeGoal;
import com.kattalist.kattsornithology.core.init.EntityInit;
import com.kattalist.kattsornithology.core.init.SoundInit;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

public class SongbirdEntity extends Animal implements FlyingAnimal {

	private static final EntityDataAccessor<Integer> SONGBIRD_TYPE = SynchedEntityData.defineId(SongbirdEntity.class,
			EntityDataSerializers.INT);

	public SongbirdEntity(EntityType<? extends Animal> entityType, Level level) {
		super(entityType, level);
		setBirdType(random.nextInt(4));
		this.moveControl = new FlyingMoveControl(this, 30, false);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(SONGBIRD_TYPE, 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("BirdType", this.getBirdType());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setBirdType(compound.getInt("BirdType"));
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new FlyToTreeGoal(this, 1.25D));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
	}

	@Override
	public float getWalkTargetValue(BlockPos pos) {
		// prefer standing on leaves
		Material underMaterial = this.level.getBlockState(pos.below()).getMaterial();
		if (underMaterial == Material.LEAVES) {
			return 200.0F;
		}
		if (underMaterial == Material.WOOD) {
			return 15.0F;
		}
		if (underMaterial == Material.GRASS) {
			return 9.0F;
		}
		// default to just preferring lighter areas
		return this.level.getMaxLocalRawBrightness(pos) - 0.5F;
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
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double) 0.25F)
				.add(Attributes.MOVEMENT_SPEED, 0.2D);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		switch (this.getBirdType()) {
		case 0:
			return SoundInit.SONGBIRD_BLUEBIRD_AMBIENT.get();
		case 1: 
			return SoundInit.SONGBIRD_CHICKADEE_AMBIENT.get();
		case 2:
			return SoundInit.SONGBIRD_GOLDFINCH_AMBIENT.get();
		default:
			return SoundInit.SONGBIRD_ORIOLE_AMBIENT.get();
		}
	}

	public void canSpawn() {

	}

	@Override
	public void aiStep() {
		super.aiStep();
		// Slow falling speed
		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
		return EntityInit.SONGBIRD.get().create(level);
	}

	@Override
	public boolean isFlying() {
		return !this.onGround;
	}

	@Override
	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	public int getBirdType() {
		return entityData.get(SONGBIRD_TYPE);
	}

	public void setBirdType(int type) {
		entityData.set(SONGBIRD_TYPE, type);
	}

	public static boolean checkSongbirdSpawnRules(EntityType<SongbirdEntity> p_29424_, LevelAccessor p_29425_,
			MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
		BlockState blockstate = p_29425_.getBlockState(p_29427_.below());
		return (blockstate.is(BlockTags.LEAVES) || blockstate.is(BlockTags.LOGS) || blockstate.is(Blocks.AIR))
				&& p_29425_.getRawBrightness(p_29427_, 0) > 8;
	}

}
