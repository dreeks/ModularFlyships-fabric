package xyz.dreeks.modularflyships;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import xyz.dreeks.modularflyships.entities.MFSEntities;
import xyz.dreeks.modularflyships.render.models.FlyshipRendererEntity;

public class ClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(MFSEntities.flyshipType, (dispatcher, ctx) -> new FlyshipRendererEntity(dispatcher));
    }

}
