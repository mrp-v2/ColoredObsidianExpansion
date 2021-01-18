package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.AdditionalColors;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.block.ColoredFenceGateBlock;
import mrp_v2.mrplibrary.util.IModLocProvider;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class CryingObsidianFenceGateBlockData extends AbstractCryingObsidianBlockData<ColoredFenceGateBlock>
{
    public CryingObsidianFenceGateBlockData(RegistryObject<? extends Block> baseBlock,
            IModLocProvider coloredBlockDataHandler)
    {
        super(baseBlock, coloredBlockDataHandler);
    }

    @Override protected ColoredFenceGateBlock makeNewBlock(DyeColor color)
    {
        return new ColoredFenceGateBlock(getBlockProperties(color), color);
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredFenceGateBlock> blockObject : blockObjectMap.values())
        {
            generator.fenceGateBlock(blockObject.get(), new ResourceLocation(AdditionalColors.ID,
                    "block/" + blockObject.getId().getPath().replace("_fence_gate", "")));
        }
    }
}
