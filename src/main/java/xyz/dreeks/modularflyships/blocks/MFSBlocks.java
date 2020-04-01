package xyz.dreeks.modularflyships.blocks;

import xyz.dreeks.modularflyships.api.Initializable;

import java.util.ArrayList;

public class MFSBlocks extends Initializable<BaseBlock> {

    public static final ArrayList<BaseBlock> blocks = new ArrayList<>();

    public void init() {
        super.init();

        // Do non standard block registration
    }

    @Override
    protected ArrayList<BaseBlock> getElements() {
        return blocks;
    }

}
