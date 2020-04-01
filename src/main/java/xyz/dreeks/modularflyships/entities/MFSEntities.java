package xyz.dreeks.modularflyships.entities;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.dreeks.modularflyships.Constants;

public class MFSEntities {

    public static EntityType flyshipType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Constants.MOD_ID, "flyship"),
            FabricEntityTypeBuilder.create(
                    EntityCategory.MISC,
                    ((type, world) -> new FlyshipEntity(world))
            ).size(new EntityDimensions(1f, 1f, true))
             .build()
    );

    // Could be static but keeping it not, to have the same behaviour in main mod class
    public void init() {

    }

}
