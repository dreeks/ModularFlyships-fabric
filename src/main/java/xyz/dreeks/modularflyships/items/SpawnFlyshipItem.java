package xyz.dreeks.modularflyships.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import xyz.dreeks.modularflyships.entities.FlyshipEntity;

public class SpawnFlyshipItem extends BaseItem {

    public SpawnFlyshipItem() {
        super("spawn_flyship", new Item.Settings());
    }

    public ActionResult useOnBlock(ItemUsageContext context) {

        if (context.getWorld().isClient) {
            return ActionResult.SUCCESS;
        }

        FlyshipEntity fse = new FlyshipEntity(context.getWorld());

        BlockPos pos = context.getBlockPos();
        fse.setPos(pos.getX(), pos.getY(), pos.getZ());

        if (context.getWorld().spawnEntity(fse)){
            context.getStack().decrement(1);
        }

        return ActionResult.SUCCESS;
    }

}
