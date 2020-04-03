package xyz.dreeks.modularflyships.render;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import xyz.dreeks.modularflyships.Constants;
import xyz.dreeks.modularflyships.entities.FlyshipEntity;

// Temporary stolen from MC until we get a real model
public class FlyshipRendererEntity extends EntityRenderer<FlyshipEntity> {

    protected final BoatEntityModel model = new BoatEntityModel();

    // EntityRenderDispatcher => EntityRendererManager
    public FlyshipRendererEntity(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(FlyshipEntity flyship, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        // Never called for some reasons
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - f));


        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        //this.model.setAngles(boatEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(this.getTexture(flyship)));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
        this.model.getBottom().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        super.render(flyship, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(FlyshipEntity entity) {
        return new Identifier("textures/entity/boat/oak.png");
        //return Constants.id("textures/entities/flyship.png");
    }

}
