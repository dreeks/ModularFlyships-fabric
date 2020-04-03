package xyz.dreeks.modularflyships;

import net.minecraft.util.Identifier;

/**
 * Modular Flyships - Copyright (C) 2020 - Team Dreeks (Nathan <Oxodao> JANCZEWSKI, Guillaume <cylgom>)
 *
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it under certain conditions;
 * Take a look at README.md for more details;
 */
public class Constants {

    public static final String MOD_ID = "modularflyships";
    public static final String MOD_NAME = "Modular Flyships";

    public static final Identifier PACKET_SET_INPUTS = id("set_inputs");

    public static Identifier id(String unlocalizedName) {
        return new Identifier(MOD_ID, unlocalizedName);
    }

}
