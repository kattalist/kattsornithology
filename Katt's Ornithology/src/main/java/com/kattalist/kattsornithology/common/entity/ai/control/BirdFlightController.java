package com.kattalist.kattsornithology.common.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BirdFlightController extends MoveControl{
	
	private final Mob entity;
	private final double mobSpeed;

	public BirdFlightController(Mob bird, double speed) {
		super(bird);
		this.entity = bird;
		this.mobSpeed = speed;
	}
	
	@Override
	public void tick() {
		if (this.operation == MoveControl.Operation.MOVE_TO) {
			entity.setNoGravity(true);
			Vec3 vec3 = new Vec3(this.wantedX - entity.getX(), this.wantedY - entity.getY(), this.wantedZ - entity.getZ());
			double length = vec3.length();
			if (length < entity.getBoundingBox().getSize()) {
				this.operation = MoveControl.Operation.WAIT;
				entity.setDeltaMovement(entity.getDeltaMovement().scale(0.5D));
			} else {
				entity.setDeltaMovement(entity.getDeltaMovement().add(vec3.scale(this.speedModifier * mobSpeed * 0.05D / length)));
				if (entity.getTarget() == null) {
					Vec3 mvmt = entity.getDeltaMovement();
					entity.setYRot(-((float) Mth.atan2(mvmt.x, mvmt.z)) * (180F / (float) Math.PI));
					entity.yBodyRot = entity.getYRot();
				} else {
					double dx = entity.getTarget().getX() - entity.getX();
					double dz = entity.getTarget().getZ() - entity.getZ();
					entity.setYRot(-((float) Mth.atan2(dz, dx)) * (180F / (float) Math.PI));
                    entity.yBodyRot = entity.getYRot();
				}
			}
		} else if (this.operation == Operation.STRAFE) {
            this.operation = Operation.WAIT;
        }
	}
	
	private boolean canReach(Vec3 vec3, int tries) {
        AABB axisalignedbb = this.entity.getBoundingBox();

        for (int i = 1; i < tries; ++i) {
            axisalignedbb = axisalignedbb.move(vec3);
            if (!this.entity.level.noCollision(this.entity, axisalignedbb)) {
                return false;
            }
        }

        return true;
    }

}
