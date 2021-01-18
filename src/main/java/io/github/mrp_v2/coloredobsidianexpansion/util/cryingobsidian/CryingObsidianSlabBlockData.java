package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import io.github.mrp_v2.coloredobsidianexpansion.ColoredObsidianExpansion;
import io.github.mrp_v2.coloredobsidianexpansion.util.ObjectHolder;
import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.colored_block_data.BasicColoredSlabBlockData;
import mrp_v2.additionalcolors.api.colored_block_data.IColoredBlockData;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.block.ColoredSlabBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.fml.RegistryObject;

public abstract class CryingObsidianSlabBlockData extends BasicColoredSlabBlockData
{
    public CryingObsidianSlabBlockData(RegistryObject<? extends Block> baseBlock,
            ITag.INamedTag<Block>[] blockTagsToAddTo, ITag.INamedTag<Item>[] itemTagsToAddTo,
            IColoredBlockData<?> baseBlockData, ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, baseBlockData, coloredBlockDataHandler);
    }

    @Override public boolean requiresTinting()
    {
        return false;
    }

    @Override public void registerItemModels(ItemModelProvider generator)
    {
        for (RegistryObject<ColoredSlabBlock> blockObject : blockObjectMap.values())
        {
            String path = blockObject.getId().getPath();
            generator.withExistingParent(path, generator.modLoc("block/" + path));
        }
    }

    @Override public DyeColor[] getColors()
    {
        return ObjectHolder.CRYING_OBSIDIAN_COLORS;
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredSlabBlock> blockObject : blockObjectMap.values())
        {
            ResourceLocation blockLoc = new ResourceLocation(ColoredObsidianExpansion.ID,
                    "block/" + blockObject.getId().getPath().replace("_slab", ""));
            generator.slabBlock(blockObject.get(), blockLoc, blockLoc);
        }
    }
}
