package xyz.dreeks.modularflyships;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import xyz.dreeks.modularflyships.blocks.MFSBlocks;
import xyz.dreeks.modularflyships.entities.MFSEntities;
import xyz.dreeks.modularflyships.items.MFSItems;

/**
 * Modular Flyships - Copyright (C) 2020 - Team Dreeks (Nathan <Oxodao> JANCZEWSKI, Guillaume <cylgom>)
 *
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it under certain conditions;
 * Take a look at README.md for more details;
 */

public class ModularFlyships implements ModInitializer {

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Constants.MOD_ID, "main"), () -> new ItemStack(MFSItems.SpawnFlyshipItem));

	@Override
	public void onInitialize() {
        new MFSBlocks().init();
		new MFSItems().init();
		new MFSEntities().init();
	}

}
