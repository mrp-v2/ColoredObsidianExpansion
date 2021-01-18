package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.AdditionalColors;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.api.datagen.ItemModelGenerator;
import mrp_v2.additionalcolors.block.ColoredFenceBlock;
import mrp_v2.mrplibrary.util.IModLocProvider;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class CryingObsidianFenceBlockData extends AbstractCryingObsidianBlockData<ColoredFenceBlock>
{
    public CryingObsidianFenceBlockData(RegistryObject<? extends Block> baseBlock,
            IModLocProvider coloredBlockDataHandler)
    {
        super(baseBlock, coloredBlockDataHandler);
    }

    @Override public void registerItemModels(ItemModelGenerator generator)
    {
        for (RegistryObject<ColoredFenceBlock> blockObject : blockObjectMap.values())
        {
            generator.fenceInventory(blockObject.getId().getPath(), new ResourceLocation(AdditionalColors.ID,
                    "block/" + blockObject.getId().getPath().replace("_fence", "")));
        }
    }

    @Override protected ColoredFenceBlock makeNewBlock(DyeColor color)
    {
        return new ColoredFenceBlock(getBlockProperties(color), color);
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredFenceBlock> blockObject : blockObjectMap.values())
        {
            generator.fenceBlock(blockObject.get(), new ResourceLocation(AdditionalColors.ID,
                    "block/" + blockObject.getId().getPath().replace("_fence", "")));
        }
    }
}
