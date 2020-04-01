package xyz.dreeks.modularflyships.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public abstract class VehiculeEntity extends Entity {

    public VehiculeEntity(EntityType<VehiculeEntity> type, World world) {
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
    public Box getHardCollisionBox(Entity collidingEntity) {
        return collidingEntity.getBoundingBox();
    }

    public Box getCollisionBox() {
        return this.getBoundingBox();
    }

    @Override
    public boolean collides() {
        return true;
    }
}
