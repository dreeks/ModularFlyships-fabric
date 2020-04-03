package xyz.dreeks.modularflyships.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;

public abstract class SimplePacket {

    protected PacketByteBuf buf;

    public SimplePacket() {
        this.buf = new PacketByteBuf(Unpooled.buffer());
    }

    public SimplePacket(PacketByteBuf pbb) {
        this.buf = pbb;
        this.read();
    }

    public abstract PacketByteBuf write();
    public abstract void read();

}
