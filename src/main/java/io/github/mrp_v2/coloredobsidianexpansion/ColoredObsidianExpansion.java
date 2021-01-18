package io.github.mrp_v2.coloredobsidianexpansion;

import io.github.mrp_v2.coloredobsidianexpansion.util.ObjectHolder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ColoredObsidianExpansion.ID) public class ColoredObsidianExpansion
{
    public static final String ID = "colored" + "obsidian" + "expansion";

    public ColoredObsidianExpansion()
    {
        ObjectHolder.registerListeners(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
