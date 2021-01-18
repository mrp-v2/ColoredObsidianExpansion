package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import io.github.mrp_v2.coloredobsidianexpansion.block.ColoredCryingObsidianBlock;
import io.github.mrp_v2.coloredobsidianexpansion.util.ObjectHolder;
import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.mrplibrary.datagen.providers.TextureProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class CryingObsidianBlockData extends AbstractColoredBlockData<ColoredCryingObsidianBlock>
{
    public static final Map<DyeColor, Consumer<BufferedImage>> TEXTURE_MAKER_FUNCTION_MAP;

    static
    {
        TEXTURE_MAKER_FUNCTION_MAP = new HashMap<>();
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.WHITE, (texture) ->
        {
            TextureProvider.makeGrayscale(texture);
            TextureProvider.adjustLevels(texture, 0.6d, 0, 70, 0, 255);
        });
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.LIGHT_GRAY, (texture) ->
        {
            TextureProvider.makeGrayscale(texture);
            TextureProvider.adjustLevels(texture, 0.5d);
        });
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.GRAY, TextureProvider::makeGrayscale);
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.BLACK, (texture) ->
        {
            TextureProvider.makeGrayscale(texture);
            TextureProvider.adjustLevels(texture, 1.5d);
        });
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.RED, (texture) -> TextureProvider.adjustHSB(texture, 90, 100, 0));
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.ORANGE, (texture) -> TextureProvider.adjustHSB(texture, 120, 100, 0));
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.YELLOW, (texture) -> TextureProvider.adjustHSB(texture, 150, 100, 0));
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.GREEN, (texture) -> TextureProvider.adjustHSB(texture, -165, 100, 0));
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.CYAN, (texture) -> TextureProvider.adjustHSB(texture, -90, 100, 0));
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.BLUE, (texture) -> TextureProvider.adjustHSB(texture, -30, 100, 0));
        TEXTURE_MAKER_FUNCTION_MAP.put(DyeColor.BROWN, (texture) -> TextureProvider.adjustHSB(texture, 120, 50, -40));
    }

    public CryingObsidianBlockData(RegistryObject<? extends Block> baseBlock, ITag.INamedTag<Block>[] blockTagsToAddTo,
            ITag.INamedTag<Item>[] itemTagsToAddTo, ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, coloredBlockDataHandler);
    }

    @Override public void makeTextureGenerationPromises(TextureProvider generator)
    {
        for (RegistryObject<ColoredCryingObsidianBlock> blockObject : blockObjectMap.values())
        {
            generator.promiseGeneration(
                    new ResourceLocation(coloredBlockDataHandler.getModId(), "block/" + blockObject.getId().getPath()));
        }
    }

    @Override public Map<DyeColor, RegistryObject<ColoredCryingObsidianBlock>> register(DeferredRegister<Block> blocks,
            DeferredRegister<Item> items)
    {
        Map<DyeColor, RegistryObject<ColoredCryingObsidianBlock>> map = super.register(blocks, items);
        ObjectHolder.CRYING_OBSIDIAN_BLOCK_MAP.putAll(map);
        return map;
    }

    @Override public boolean requiresTinting()
    {
        return false;
    }

    @Override public void registerTextures(TextureProvider generator, TextureProvider.FinishedTextureConsumer consumer)
    {
        for (RegistryObject<ColoredCryingObsidianBlock> blockObject : blockObjectMap.values())
        {
            ColoredCryingObsidianBlock block = blockObject.get();
            TextureProvider.Texture texture = generator
                    .getTexture(new ResourceLocation("block/" + Blocks.CRYING_OBSIDIAN.getRegistryName().getPath()));
            TEXTURE_MAKER_FUNCTION_MAP.get(block.getColor()).accept(texture.getTexture());
            generator.finish(texture,
                    new ResourceLocation(coloredBlockDataHandler.getModId(), "block/" + blockObject.getId().getPath()),
                    consumer);
        }
    }

    @Override public void registerItemModels(ItemModelProvider generator)
    {
        for (RegistryObject<ColoredCryingObsidianBlock> blockObject : blockObjectMap.values())
        {
            String path = blockObject.get().getRegistryName().getPath();
            generator.withExistingParent(path, generator.modLoc("block/" + path));
        }
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredCryingObsidianBlock> blockObject : blockObjectMap.values())
        {
            generator.simpleBlock(blockObject.get());
        }
    }

    @Override public DyeColor[] getColors()
    {
        return ObjectHolder.CRYING_OBSIDIAN_COLORS;
    }

    @Override protected ColoredCryingObsidianBlock makeNewBlock(DyeColor color)
    {
        return new ColoredCryingObsidianBlock(color, getBlockProperties());
    }
}
