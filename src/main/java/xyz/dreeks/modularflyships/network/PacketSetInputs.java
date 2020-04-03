package xyz.dreeks.modularflyships.network;

import net.minecraft.network.PacketByteBuf;

public class PacketSetInputs extends SimplePacket {

    public boolean left, right, forward, backward, up, down;

    public PacketSetInputs() {

    }

    public PacketSetInputs(PacketByteBuf pbb) {
        super(pbb);
    }

    public PacketSetInputs(boolean left, boolean right, boolean forward, boolean backward, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.forward = forward;
        this.backward = backward;
        this.up = up;
        this.down = down;
    }

    @Override
    public PacketByteBuf write() {
        buf.writeBoolean(this.left);
        buf.writeBoolean(this.right);
        buf.writeBoolean(this.forward);
        buf.writeBoolean(this.backward);
        buf.writeBoolean(this.up);
        buf.writeBoolean(this.down);

        return this.buf;
    }

    @Override
    public void read() {
        this.left = buf.readBoolean();
        this.right = buf.readBoolean();
        this.forward = buf.readBoolean();
        this.backward = buf.readBoolean();
        this.up = buf.readBoolean();
        this.down = buf.readBoolean();
    }

}
