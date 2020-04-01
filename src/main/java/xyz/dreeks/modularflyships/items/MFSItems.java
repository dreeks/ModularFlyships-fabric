package xyz.dreeks.modularflyships.items;

import net.minecraft.item.Item;
import xyz.dreeks.modularflyships.api.Initializable;

import java.util.ArrayList;

public class MFSItems extends Initializable<BaseItem> {

    public static final ArrayList<BaseItem> items = new ArrayList<>();
    public static final Item SpawnFlyshipItem = new SpawnFlyshipItem();

    @Override
    public void init() {
        super.init();

        // Do non standard items registration
    }

    @Override
    protected ArrayList<BaseItem> getElements() {
        return items;
    }

}
