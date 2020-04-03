package xyz.dreeks.modularflyships.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import xyz.dreeks.modularflyships.entities.VehicleEntity;

import java.io.IOException;

/**
 * Temporarly stolen from MC until we have other things to add
 */
public class PacketSpawnEntity extends EntitySpawnS2CPacket {

    public PacketSpawnEntity() {
    }

    public PacketSpawnEntity(VehicleEntity entity) {
        super(entity);
    }

    @Override
    public void read(PacketByteBuf buf) throws IOException {
        super.read(buf);
    }

    @Override
    public void write(PacketByteBuf buf) throws IOException {
        super.write(buf);
    }

    @Override
    public void apply(ClientPlayPacketListener listener) {
        super.apply(listener);
    }

}
