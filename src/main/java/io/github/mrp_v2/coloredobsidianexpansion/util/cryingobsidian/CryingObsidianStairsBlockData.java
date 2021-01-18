package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.AdditionalColors;
import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.colored_block_data.ColoredStairsBlockData;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.api.datagen.ItemModelGenerator;
import mrp_v2.additionalcolors.block.ColoredStairsBlock;
import mrp_v2.additionalcolors.util.colored_block_data.CryingObsidianBlockData;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class CryingObsidianStairsBlockData extends ColoredStairsBlockData
{
    public CryingObsidianStairsBlockData(RegistryObject<? extends Block> baseBlock,
            AbstractColoredBlockData<?> baseBlockData)
    {
        super(baseBlock, baseBlockData);
    }

    @Override protected ColoredStairsBlock makeNewBlock(DyeColor color)
    {
        return new ColoredStairsBlock(() -> mrp_v2.additionalcolors.util.ObjectHolder.COLORED_BLOCK_DATA_HANDLER
                .getColoredBlockData(Blocks.CRYING_OBSIDIAN.getRegistryName()).getBlockObject(color).get()
                .getDefaultState(), getBlockProperties(color), color);
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredStairsBlock> blockObject : blockObjectMap.values())
        {
            generator.stairsBlock(blockObject.get(), new ResourceLocation(AdditionalColors.ID,
                    "block/" + blockObject.getId().getPath().replace("_stairs", "")));
        }
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
        for (RegistryObject<ColoredStairsBlock> blockObject : blockObjectMap.values())
        {
            String path = blockObject.getId().getPath();
            generator.withExistingParent(path, generator.modLoc("block/" + path));
        }
    }
}
