package xyz.dreeks.modularflyships.render.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

import java.util.Arrays;

public class FlyshipModel extends CompositeEntityModel {

    public static ImmutableList<ModelPart> parts;


    // Model texU texV x, y, z, dx, dy, dz, delta, mirror
    // u, v, x, y, z, sizeX, sizeY, sizeZ, extraX, extraY, extraZ, mirror, bl

    public FlyshipModel() {
        this.textureWidth = 512;
        this.textureHeight = 512;

        ModelPart[] modelParts = buildArray(28);

        // Seatback
        modelParts[0].setPivot(6.0f, -10f, 0.0f);
        MatrixStack ms0 = new MatrixStack();
        ms0.translate(.3142f, 0f, 0f);
        ms0.push();
        modelParts[0].rotate(ms0);
        modelParts[0].setTextureOffset(396, 73);
        modelParts[0].addCuboid( -12.0F, 0.0F, 0.0F, 12f, 4f, 8f, 0, true);

        this.parts = ImmutableList.<ModelPart>builder().addAll(Arrays.asList(modelParts)).build();
    }

    private ModelPart[] buildArray(int size) {
        ModelPart[] lParts = new ModelPart[size];

        for(int i = 0; i < size; i++) {
            lParts[i] = new ModelPart(this, 0, 0).setTextureSize(this.textureWidth, this.textureHeight);
        }

        return lParts;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return this.parts;
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {

    }
}
