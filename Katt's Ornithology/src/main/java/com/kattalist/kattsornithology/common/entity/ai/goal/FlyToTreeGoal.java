package com.kattalist.kattsornithology.common.entity.ai.goal;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class FlyToTreeGoal extends Goal {

	protected final PathfinderMob entity;
	protected double speedModifier;
	private double wantedX, wantedY, wantedZ;
	private final Level level;

	public FlyToTreeGoal(PathfinderMob entity, double speed) {
		
		this.entity = entity;
		this.speedModifier = speed;
		this.level = entity.level;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		return entity.isOnGround() && entity.getRandom().nextInt(10) == 9 && this.setWantedPos();
	}

	@Override
	public boolean canContinueToUse() {
		return !this.entity.getNavigation().isDone();
	}

	@Override
	public void start() {
		this.entity.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}

	protected boolean setWantedPos() {
		Vec3 vec3 = this.getLeafPos();
		if (vec3 == null) {
			return false;
		} else {
			this.wantedX = vec3.x;
			this.wantedY = vec3.y;
			this.wantedZ = vec3.z;
			return true;
		}
	}

	@Nullable
	protected Vec3 getLeafPos() {
		
		Random random = new Random();
		BlockPos blockpos = this.entity.blockPosition();

		for (int i = 0; i < 10; ++i) {
			BlockPos blockpos1 = blockpos.offset(getRandomOffset(random, 5, 20), random.nextInt(16) - 8,
					getRandomOffset(random, 5, 20));
			if (this.level.getBlockState(blockpos1).is(BlockTags.LEAVES) && this.level.getBlockState(blockpos1.above()).getBlock() == Blocks.AIR) {
				return Vec3.atCenterOf(blockpos1.above());
			}
		}

		return null;
	}
	
	protected int getRandomOffset(Random random, int min, int max) {
		//Returns a value between min and max away in either the positive or the negative direction
		if (random.nextInt(2) == 0) {
			return random.nextInt(max - min) + min;
		} else {
			return -random.nextInt(max - min) - min;
		}
	}
}
