package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.datagen.ItemModelGenerator;
import mrp_v2.additionalcolors.api.datagen.LootTableGenerator;
import mrp_v2.additionalcolors.util.IColored;
import mrp_v2.additionalcolors.util.colored_block_data.CryingObsidianBlockData;
import mrp_v2.mrplibrary.datagen.providers.TextureProvider;
import mrp_v2.mrplibrary.util.IModLocProvider;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;

public abstract class AbstractCryingObsidianBlockData<T extends Block & IColored> extends AbstractColoredBlockData<T>
{
    protected AbstractCryingObsidianBlockData(RegistryObject<? extends Block> baseBlock,
            IModLocProvider coloredBlockDataHandler)
    {
        super(baseBlock, coloredBlockDataHandler);
    }

    @Override public void makeTextureGenerationPromises(TextureProvider generator)
    {
    }

    @Override public DyeColor[] getColors()
    {
        return CryingObsidianBlockData.CRYING_OBSIDIAN_COLORS;
    }

    @Override public boolean requiresTinting()
    {
        return false;
    }

    @Override public void registerTextures(TextureProvider generator, TextureProvider.FinishedTextureConsumer consumer)
    {
    }

    @Override public void registerItemModels(ItemModelGenerator generator)
    {
        for (RegistryObject<T> blockObject : getBlockObjects())
        {
            String path = blockObject.getId().getPath();
            generator.withExistingParent(path, generator.modLoc("block/" + path));
        }
    }

    @Override public void registerLootTables(LootTableGenerator generator)
    {
        for (RegistryObject<T> blockObject : getBlockObjects())
        {
            generator.addLootTable(blockObject.get(), generator::registerDropSelfLootTable);
        }
    }
}
