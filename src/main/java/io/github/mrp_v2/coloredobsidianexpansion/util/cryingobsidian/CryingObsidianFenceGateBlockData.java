package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.block.ColoredFenceGateBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public abstract class CryingObsidianFenceGateBlockData extends AbstractCryingObsidianBlockData<ColoredFenceGateBlock>
{
    public CryingObsidianFenceGateBlockData(RegistryObject<? extends Block> baseBlock, ITag.INamedTag<Block>[] blockTagsToAddTo,
            ITag.INamedTag<Item>[] itemTagsToAddTo, ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, coloredBlockDataHandler);
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredFenceGateBlock> blockObject : blockObjectMap.values())
        {
            generator.fenceGateBlock(blockObject.get(), new ResourceLocation(coloredBlockDataHandler.getModId(),
                    "block/" + blockObject.getId().getPath().replace("_fence_gate", "")));
        }
    }

    @Override protected ColoredFenceGateBlock makeNewBlock(DyeColor color)
    {
        return new ColoredFenceGateBlock(getBlockProperties(), color);
    }
}
