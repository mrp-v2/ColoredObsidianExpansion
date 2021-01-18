package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import io.github.mrp_v2.coloredobsidianexpansion.util.ObjectHolder;
import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.colored_block_data.BasicColoredStairsBlockData;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.block.ColoredStairsBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.fml.RegistryObject;

public abstract class CryingObsidianStairsBlockData extends BasicColoredStairsBlockData
{
    public CryingObsidianStairsBlockData(RegistryObject<? extends Block> baseBlock, ITag.INamedTag<Block>[] blockTagsToAddTo,
            ITag.INamedTag<Item>[] itemTagsToAddTo, AbstractColoredBlockData<?> baseBlockData,
            ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, baseBlockData, coloredBlockDataHandler);
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredStairsBlock> blockObject : blockObjectMap.values())
        {
            generator.stairsBlock(blockObject.get(), new ResourceLocation(coloredBlockDataHandler.getModId(),
                    "block/" + blockObject.getId().getPath().replace("_stairs", "")));
        }
    }

    @Override protected ColoredStairsBlock makeNewBlock(DyeColor color)
    {
        return new ColoredStairsBlock(() -> ObjectHolder.CRYING_OBSIDIAN_BLOCK_MAP.get(color).get().getDefaultState(),
                getBlockProperties(), color);
    }

    @Override public boolean requiresTinting()
    {
        return false;
    }

    @Override public void registerItemModels(ItemModelProvider generator)
    {
        for (RegistryObject<ColoredStairsBlock> blockObject : blockObjectMap.values())
        {
            String path = blockObject.getId().getPath();
            generator.withExistingParent(path, generator.modLoc("block/" + path));
        }
    }

    @Override public DyeColor[] getColors()
    {
        return ObjectHolder.CRYING_OBSIDIAN_COLORS;
    }
}
