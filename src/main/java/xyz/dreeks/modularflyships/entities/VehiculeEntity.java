package xyz.dreeks.modularflyships.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.dreeks.modularflyships.network.PacketSpawnEntity;

public abstract class VehiculeEntity extends Entity {

    public VehiculeEntity(EntityType<VehiculeEntity> type, World world) {
        super(type, world);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new PacketSpawnEntity();
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        player.startRiding(this);
        return ActionResult.PASS;
    }
}
