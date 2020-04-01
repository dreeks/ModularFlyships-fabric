package xyz.dreeks.modularflyships.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.dreeks.modularflyships.Constants;
import xyz.dreeks.modularflyships.api.IRegistrable;

public class BaseBlock extends Block implements IRegistrable {

    public String unlocalizedName;

    public BaseBlock(String unlocalizedName, Settings settings) {
        super(settings);
        this.unlocalizedName = unlocalizedName;
        MFSBlocks.blocks.add(this);
    }

    @Override
    public void register() {
        Registry.register(Registry.BLOCK, new Identifier(Constants.MOD_ID, this.unlocalizedName), this);
    }
}
