package xyz.dreeks.modularflyships.mixins;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dreeks.modularflyships.entities.FlyshipEntity;
import xyz.dreeks.modularflyships.entities.MFSEntities;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinEntitySpawn {

    @Inject(at = @At("TAIL"), method="onEntitySpawn")
    public void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo cbi) {
        ClientPlayNetworkHandler cpnh = (ClientPlayNetworkHandler) (Object) this;

        if (packet.getEntityTypeId() == MFSEntities.flyshipType) {
            Entity e = new FlyshipEntity(cpnh.getWorld());

            int i = packet.getId();
            e.updateTrackedPosition(packet.getX(), packet.getY(), packet.getZ());
            e.pitch = (float)(packet.getPitch() * 360) / 256.0F;
            e.yaw = (float)(packet.getYaw() * 360) / 256.0F;
            e.setEntityId(i);
            e.setUuid(packet.getUuid());
            cpnh.getWorld().addEntity(i, e);
        }
    }

}
