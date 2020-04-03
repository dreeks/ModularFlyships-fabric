package xyz.dreeks.modularflyships.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import xyz.dreeks.modularflyships.ClientInitializer;
import xyz.dreeks.modularflyships.entities.VehicleEntity;

@Environment(EnvType.CLIENT)
public class KeyEvents implements ClientTickCallback {

    @Override
    public void tick(MinecraftClient mc) {
        if (mc.player != null) {
            Entity riding = mc.player.getVehicle();
            if (riding != null && riding instanceof VehicleEntity) {
                // This will be executed only if the keys are not already retreived
                ClientInitializer.setKeybinds(mc);

                // Then we can update the entity
                VehicleEntity e = (VehicleEntity)riding;
                e.setInputs(ClientInitializer.KeyLeft.isPressed(), ClientInitializer.KeyRight.isPressed(), ClientInitializer.KeyForward.isPressed(), ClientInitializer.KeyBackward.isPressed(), ClientInitializer.KeyUp.isPressed(), ClientInitializer.KeyDown.isPressed());

                // Klaxon
                // @TODO: Send a different packet
            }
        }
    }
}
