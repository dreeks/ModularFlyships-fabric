package xyz.dreeks.modularflyships.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import xyz.dreeks.modularflyships.entities.VehiculeEntity;

import java.io.IOException;
import java.util.UUID;

/**
 * Temporarly stolen from MC until we have other things to add
 */
public class PacketSpawnEntity implements Packet<ClientPlayPacketListener> {

    public int id;
    public UUID uuid;
    public int entityTypeId;
    public double x, y, z;
    public int velocityX, velocityY, velocityZ;
    public byte yaw;
    public byte pitch;
    public byte headYaw;

    public PacketSpawnEntity() {
    }

    public PacketSpawnEntity(VehiculeEntity entity) {
        this.id = entity.getEntityId();
        this.uuid = entity.getUuid();
        this.entityTypeId = Registry.ENTITY_TYPE.getRawId(entity.getType());
        this.x = entity.getX();
        this.y = entity.getY();
        this.z = entity.getZ();
        this.yaw = (byte)((int)(entity.yaw * 256.0F / 360.0F));
        this.pitch = (byte)((int)(entity.pitch * 256.0F / 360.0F));
        //this.headYaw = (byte)((int)(entity.headYaw * 256.0F / 360.0F));
        double d = 3.9D;
        Vec3d vec3d = entity.getVelocity();
        double e = MathHelper.clamp(vec3d.x, -3.9D, 3.9D);
        double f = MathHelper.clamp(vec3d.y, -3.9D, 3.9D);
        double g = MathHelper.clamp(vec3d.z, -3.9D, 3.9D);
        this.velocityX = (int)(e * 8000.0D);
        this.velocityY = (int)(f * 8000.0D);
        this.velocityZ = (int)(g * 8000.0D);
    }

    public void read(PacketByteBuf buf) throws IOException {
        this.id = buf.readVarInt();
        this.uuid = buf.readUuid();
        this.entityTypeId = buf.readVarInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.yaw = buf.readByte();
        this.pitch = buf.readByte();
        this.headYaw = buf.readByte();
        this.velocityX = buf.readShort();
        this.velocityY = buf.readShort();
        this.velocityZ = buf.readShort();
    }

    public void write(PacketByteBuf buf) throws IOException {
        buf.writeVarInt(this.id);
        buf.writeUuid(this.uuid);
        buf.writeVarInt(this.entityTypeId);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeByte(this.yaw);
        buf.writeByte(this.pitch);
        buf.writeByte(this.headYaw);
        buf.writeShort(this.velocityX);
        buf.writeShort(this.velocityY);
        buf.writeShort(this.velocityZ);
    }

    @Override
    public void apply(ClientPlayPacketListener listener) {

    }

}
