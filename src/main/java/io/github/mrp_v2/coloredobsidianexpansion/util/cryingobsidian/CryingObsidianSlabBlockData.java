package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.AdditionalColors;
import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.colored_block_data.ColoredSlabBlockData;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.api.datagen.ItemModelGenerator;
import mrp_v2.additionalcolors.block.ColoredSlabBlock;
import mrp_v2.additionalcolors.util.colored_block_data.CryingObsidianBlockData;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class CryingObsidianSlabBlockData extends ColoredSlabBlockData
{
    public CryingObsidianSlabBlockData(RegistryObject<? extends Block> baseBlock,
            AbstractColoredBlockData<?> baseBlockData)
    {
        super(baseBlock, baseBlockData);
    }

    @Override public DyeColor[] getColors()
    {
        return CryingObsidianBlockData.CRYING_OBSIDIAN_COLORS;
    }

    @Override public boolean requiresTinting()
    {
        return false;
    }

    @Override public void registerItemModels(ItemModelGenerator generator)
    {
        for (RegistryObject<ColoredSlabBlock> blockObject : blockObjectMap.values())
        {
            String path = blockObject.getId().getPath();
            generator.withExistingParent(path, generator.modLoc("block/" + path));
        }
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredSlabBlock> blockObject : blockObjectMap.values())
        {
            ResourceLocation blockLoc = new ResourceLocation(AdditionalColors.ID,
                    "block/" + blockObject.getId().getPath().replace("_slab", ""));
            generator.slabBlock(blockObject.get(), blockLoc, blockLoc);
        }
    }
}
