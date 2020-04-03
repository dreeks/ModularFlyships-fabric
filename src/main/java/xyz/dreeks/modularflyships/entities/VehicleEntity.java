package xyz.dreeks.modularflyships.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.dreeks.modularflyships.Constants;
import xyz.dreeks.modularflyships.network.PacketSetInputs;

// @TODO: Understand how MrCrayfish has implemented his multi-seat vehicle to
//        create something similar
public abstract class VehicleEntity extends Entity {

    private static final TrackedData<Boolean> goingLeft = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> goingRight = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> goingForward = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> goingBackward = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> goingUp = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> goingDown = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Float> speed = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> speedAltitude = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.FLOAT);

    public VehicleEntity(EntityType<VehicleEntity> type, World world) {
        super(type, world);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public boolean interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return false;
        } else {
            return !this.world.isClient ? player.startRiding(this) : false;
        }
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(goingLeft, false);
        this.dataTracker.startTracking(goingRight, false);
        this.dataTracker.startTracking(goingForward, false);
        this.dataTracker.startTracking(goingBackward, false);
        this.dataTracker.startTracking(goingUp, false);
        this.dataTracker.startTracking(goingDown, false);
        this.dataTracker.startTracking(speed, 0.0f);
        this.dataTracker.startTracking(speedAltitude, 0.0f);
    }

    // @STOLEN
    @Override
    public void tick() {
        if (!this.world.isClient) {
            this.prevX = this.getX();
            this.prevY = this.getY();
            this.prevZ = this.getZ();
        }

        if(!this.hasPassengers()) {
            this.dataTracker.set(goingLeft, false);
            this.dataTracker.set(goingRight, false);
            this.dataTracker.set(goingForward, false);
            this.dataTracker.set(goingBackward, false);
            this.dataTracker.set(goingUp, false);
            this.dataTracker.set(goingDown, false);
        }

        super.tick();

        //this.tickLerp();

        if (this.isLogicalSideForUpdatingMovement()) {
            //this.updateVelocity();
            //this.move(MovementType.SELF, this.getVelocity());
        } else {
            this.move(MovementType.SELF, Vec3d.ZERO);
        }

    }

    private float velocityDecay;

    private void updateVelocity() {
        double e = this.hasNoGravity() ? 0.0D : -0.03999999910593033D;
        this.velocityDecay = 0.05F;
        this.updatePosition(this.getX(), this.getY(), this.getZ());
        this.setVelocity(this.getVelocity().multiply(1.0D, 0.0D, 1.0D));

        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x * (double)this.velocityDecay, vec3d.y + e, vec3d.z * (double)this.velocityDecay);
        this.yaw *= this.velocityDecay;
    }

    // No clue what the real theme
    private int field_7708 = 10;
    private double clientX, clientY, clientZ;
    private double clientYaw, clientPitch;

    private void tickLerp() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.field_7708 = 0;
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
        }

        if (this.field_7708 > 0) {
            double d = this.getX() + (this.clientX - this.getX()) / (double)this.field_7708;
            double e = this.getY() + (this.clientY - this.getY()) / (double)this.field_7708;
            double f = this.getZ() + (this.clientZ - this.getZ()) / (double)this.field_7708;
            double g = MathHelper.wrapDegrees(this.clientYaw - (double)this.yaw);
            this.yaw = (float)((double)this.yaw + g / (double)this.field_7708);
            this.pitch = (float)((double)this.pitch + (this.clientPitch - (double)this.pitch) / (double)this.field_7708);
            --this.field_7708;
            this.updatePosition(d, e, f);
            this.setRotation(this.yaw, this.pitch);
        }
    }

    @Environment(EnvType.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;
        this.clientYaw = yaw;
        this.clientPitch = pitch;
        this.field_7708 = 10;
    }

    @Environment(EnvType.CLIENT)
    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;
        this.clientYaw = (double)yaw;
        this.clientPitch = (double)pitch;
        this.field_7708 = 10;
    }

    @Environment(EnvType.CLIENT)
    public void setInputs(boolean left, boolean right, boolean forward, boolean backward, boolean up, boolean down) {
        boolean sendPacket = false;

        sendPacket |= this.dataTracker.get(this.goingLeft) != left;
        sendPacket |= this.dataTracker.get(this.goingRight) != right;
        sendPacket |= this.dataTracker.get(this.goingForward) != forward;
        sendPacket |= this.dataTracker.get(this.goingBackward) != backward;
        sendPacket |= this.dataTracker.get(this.goingUp) != up;
        sendPacket |= this.dataTracker.get(this.goingDown) != down;

        setServerInputs(left, right, forward, backward, up, down);

        if (sendPacket) {
            ClientSidePacketRegistry.INSTANCE.sendToServer(Constants.PACKET_SET_INPUTS, new PacketSetInputs(left, right, forward, backward, up, down).write());
        }
    }

    public void setServerInputs(boolean left, boolean right, boolean forward, boolean backward, boolean up, boolean down) {
        this.dataTracker.set(this.goingLeft, left);
        this.dataTracker.set(this.goingRight, right);
        this.dataTracker.set(this.goingForward, forward);
        this.dataTracker.set(this.goingBackward, backward);
        this.dataTracker.set(this.goingUp, up);
        this.dataTracker.set(this.goingDown, down);
    }

    @Override
    public Box getHardCollisionBox(Entity collidingEntity) {
        return collidingEntity.getBoundingBox();
    }

    @Override
    public Box getCollisionBox() {
        return this.getBoundingBox();
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean collides() {
        return true;
    }

}
