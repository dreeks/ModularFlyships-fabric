package xyz.dreeks.modularflyships.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlyshipEntity extends VehiculeEntity {

    public FlyshipEntity(World world) {
        super(MFSEntities.flyshipType, world);
    }

    public FlyshipEntity(World world, double x, double y, double z) {
        this(world);
        this.updatePosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {

    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {

    }

    @Override
    public void tick() {
        // System.out.println("Entity ticking");
    }

}
