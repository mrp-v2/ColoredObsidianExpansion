package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import io.github.mrp_v2.coloredobsidianexpansion.util.ObjectHolder;
import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.datagen.LootTableGenerator;
import mrp_v2.additionalcolors.util.IColored;
import mrp_v2.mrplibrary.datagen.providers.TextureProvider;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.fml.RegistryObject;

public abstract class AbstractCryingObsidianBlockData<T extends Block & IColored> extends AbstractColoredBlockData<T>
{
    protected AbstractCryingObsidianBlockData(RegistryObject<? extends Block> baseBlock, ITag.INamedTag<Block>[] blockTagsToAddTo,
            ITag.INamedTag<Item>[] itemTagsToAddTo, ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, coloredBlockDataHandler);
    }

    @Override public void makeTextureGenerationPromises(TextureProvider generator)
    {
    }

    @Override public boolean requiresTinting()
    {
        return false;
    }

    @Override public void registerTextures(TextureProvider generator, TextureProvider.FinishedTextureConsumer consumer)
    {
    }

    @Override public void registerItemModels(ItemModelProvider generator)
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

    @Override public DyeColor[] getColors()
    {
        return ObjectHolder.CRYING_OBSIDIAN_COLORS;
    }
}
