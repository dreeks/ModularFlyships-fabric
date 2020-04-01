package xyz.dreeks.modularflyships.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public class FlyshipEntity extends VehiculeEntity {

    public FlyshipEntity(World world) {
        super(MFSEntities.flyshipType, world);
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
