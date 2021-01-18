package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.block.ColoredFenceBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.fml.RegistryObject;

public abstract class CryingObsidianFenceBlockData extends AbstractCryingObsidianBlockData<ColoredFenceBlock>
{
    public CryingObsidianFenceBlockData(RegistryObject<? extends Block> baseBlock, ITag.INamedTag<Block>[] blockTagsToAddTo,
            ITag.INamedTag<Item>[] itemTagsToAddTo, ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, coloredBlockDataHandler);
    }

    @Override public void registerItemModels(ItemModelProvider generator)
    {
        for (RegistryObject<ColoredFenceBlock> blockObject : blockObjectMap.values())
        {
            generator.fenceInventory(blockObject.getId().getPath(),
                    new ResourceLocation(coloredBlockDataHandler.getModId(),
                            "block/" + blockObject.getId().getPath().replace("_fence", "")));
        }
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredFenceBlock> blockObject : blockObjectMap.values())
        {
            generator.fenceBlock(blockObject.get(), new ResourceLocation(coloredBlockDataHandler.getModId(),
                    "block/" + blockObject.getId().getPath().replace("_fence", "")));
        }
    }

    @Override protected ColoredFenceBlock makeNewBlock(DyeColor color)
    {
        return new ColoredFenceBlock(getBlockProperties(), color);
    }
}
