package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import io.github.mrp_v2.coloredobsidianexpansion.block.ColoredObsidianGlassBlock;
import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.api.datagen.LootTableGenerator;
import mrp_v2.mrplibrary.datagen.providers.TextureProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public abstract class CryingObsidianGlassBlockData extends AbstractCryingObsidianBlockData<ColoredObsidianGlassBlock>
{
    public CryingObsidianGlassBlockData(RegistryObject<? extends Block> baseBlock, ITag.INamedTag<Block>[] blockTagsToAddTo,
            ITag.INamedTag<Item>[] itemTagsToAddTo, ColoredBlockDataHandler coloredBlockDataHandler)
    {
        super(baseBlock, blockTagsToAddTo, itemTagsToAddTo, coloredBlockDataHandler);
    }

    @Override public void clientSetup(FMLClientSetupEvent event)
    {
        for (RegistryObject<ColoredObsidianGlassBlock> blockObject : blockObjectMap.values())
        {
            RenderTypeLookup.setRenderLayer(blockObject.get(), RenderType.getCutout());
        }
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredObsidianGlassBlock> blockObject : blockObjectMap.values())
        {
            generator.simpleBlock(blockObject.get());
        }
    }

    @Override protected ColoredObsidianGlassBlock makeNewBlock(DyeColor color)
    {
        return new ColoredObsidianGlassBlock(getBlockProperties().sound(SoundType.GLASS).notSolid(), color);
    }

    @Override public void makeTextureGenerationPromises(TextureProvider generator)
    {
        for (RegistryObject<ColoredObsidianGlassBlock> blockObject : blockObjectMap.values())
        {
            generator.promiseGeneration(
                    new ResourceLocation(coloredBlockDataHandler.getModId(), "block/" + blockObject.getId().getPath()));
        }
    }

    @Override public void registerTextures(TextureProvider generator, TextureProvider.FinishedTextureConsumer consumer)
    {
        for (RegistryObject<ColoredObsidianGlassBlock> blockObject : blockObjectMap.values())
        {
            TextureProvider.Texture texture = generator.getTexture(
                    new ResourceLocation(coloredBlockDataHandler.getModId(),
                            "block/" + blockObject.getId().getPath().replace("_glass", "")));
            int[] newColors = TextureProvider.color(TextureProvider.color(0, 0, 0, 0), 14 * 14);
            newColors[14 + 3] = texture.getTexture().getRGB(4, 2);
            newColors[14 * 2 + 2] = texture.getTexture().getRGB(3, 3);
            newColors[14 * 3 + 1] = texture.getTexture().getRGB(2, 4);
            newColors[14 * 11 + 12] = texture.getTexture().getRGB(13, 12);
            newColors[14 * 12 + 11] = texture.getTexture().getRGB(12, 13);
            texture.getTexture().setRGB(1, 1, 14, 14, newColors, 0, 14);
            generator.finish(texture,
                    new ResourceLocation(coloredBlockDataHandler.getModId(), "block/" + blockObject.getId().getPath()),
                    consumer);
        }
    }

    @Override public void registerLootTables(LootTableGenerator generator)
    {
        for (RegistryObject<ColoredObsidianGlassBlock> blockObject : blockObjectMap.values())
        {
            generator.addLootTable(blockObject.get(), generator::registerSilkTouch);
        }
    }
}
