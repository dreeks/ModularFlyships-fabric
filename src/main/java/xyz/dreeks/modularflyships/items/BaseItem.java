package xyz.dreeks.modularflyships.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.dreeks.modularflyships.Constants;
import xyz.dreeks.modularflyships.ModularFlyships;
import xyz.dreeks.modularflyships.api.IRegistrable;

public class BaseItem extends Item implements IRegistrable {

    public String unlocalizedName;

    public BaseItem(String unlocalizedName, Settings settings) {
        super(settings.group(ModularFlyships.ITEM_GROUP));
        this.unlocalizedName = unlocalizedName;
        MFSItems.items.add(this);
    }

    @Override
    public void register() {
        Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, this.unlocalizedName), this);
    }
}
