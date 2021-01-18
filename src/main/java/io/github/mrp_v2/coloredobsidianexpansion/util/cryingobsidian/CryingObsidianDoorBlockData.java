package io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian;

import mrp_v2.additionalcolors.AdditionalColors;
import mrp_v2.additionalcolors.api.datagen.BlockStateGenerator;
import mrp_v2.additionalcolors.api.datagen.ExtendedBlockLootTables;
import mrp_v2.additionalcolors.api.datagen.ItemModelGenerator;
import mrp_v2.additionalcolors.api.datagen.LootTableGenerator;
import mrp_v2.additionalcolors.block.ColoredDoorBlock;
import mrp_v2.mrplibrary.datagen.providers.TextureProvider;
import mrp_v2.mrplibrary.util.IModLocProvider;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class CryingObsidianDoorBlockData extends AbstractCryingObsidianBlockData<ColoredDoorBlock>
{
    public CryingObsidianDoorBlockData(RegistryObject<? extends Block> baseBlock,
            IModLocProvider coloredBlockDataHandler)
    {
        super(baseBlock, coloredBlockDataHandler);
    }

    @Override protected ColoredDoorBlock makeNewBlock(DyeColor color)
    {
        return new ColoredDoorBlock(getBlockProperties(color), color);
    }

    @Override protected boolean hasSpecialRenderType()
    {
        return true;
    }

    @Override protected RenderType getSpecialRenderType()
    {
        return RenderType.getCutout();
    }

    @Override public void registerBlockStatesAndModels(BlockStateGenerator generator)
    {
        for (RegistryObject<ColoredDoorBlock> blockObject : blockObjectMap.values())
        {
            Function<String, ResourceLocation> doorPartLocFunction =
                    (str) -> generator.modLoc("block/" + blockObject.getId().getPath() + "_" + str);
            generator.doorBlock(blockObject.get(), doorPartLocFunction.apply("bottom"),
                    doorPartLocFunction.apply("top"));
        }
    }

    @Override public void makeTextureGenerationPromises(TextureProvider generator)
    {
        for (RegistryObject<ColoredDoorBlock> blockObject : blockObjectMap.values())
        {
            generator.promiseGeneration(generator.modLoc("block/" + blockObject.getId().getPath() + "_top"));
            generator.promiseGeneration(generator.modLoc("block/" + blockObject.getId().getPath() + "_bottom"));
            generator.promiseGeneration(generator.modLoc("item/" + blockObject.getId().getPath()));
        }
    }

    @Override public void registerTextures(TextureProvider generator, TextureProvider.FinishedTextureConsumer consumer)
    {
        for (RegistryObject<ColoredDoorBlock> blockObject : blockObjectMap.values())
        {
            Supplier<TextureProvider.Texture> baseTextureSupplier = () -> generator.getTexture(
                    new ResourceLocation(AdditionalColors.ID,
                            "block/" + blockObject.getId().getPath().replace("_door", "")));
            TextureProvider.Texture doorTop = baseTextureSupplier.get(), doorBottom = baseTextureSupplier.get();
            int hingeTop = TextureProvider.color(140, 103, 184), hingeBottom = TextureProvider.color(103, 88, 159),
                    handleEdge = TextureProvider.color(101, 88, 162);
            doorTop.getTexture().setRGB(0, 4, hingeTop);
            doorTop.getTexture().setRGB(0, 5, hingeBottom);
            doorTop.getTexture().setRGB(0, 15, hingeTop);
            doorBottom.getTexture().setRGB(0, 0, hingeBottom);
            doorBottom.getTexture().setRGB(0, 10, hingeTop);
            doorBottom.getTexture().setRGB(0, 11, hingeBottom);
            generator.finish(doorBottom, generator.modLoc("block/" + blockObject.getId().getPath() + "_bottom"),
                    consumer);
            doorTop.getTexture().setRGB(11, 14, 2, 1, TextureProvider.color(hingeTop, 2), 0, 2);
            doorTop.getTexture().setRGB(13, 14, handleEdge);
            doorTop.getTexture().setRGB(11, 15, handleEdge);
            int[] clear = TextureProvider.color(TextureProvider.color(0, 0, 0, 0), 12);
            doorTop.getTexture().setRGB(3, 3, 4, 3, clear, 0, 4);
            doorTop.getTexture().setRGB(9, 3, 4, 3, clear, 0, 4);
            doorTop.getTexture().setRGB(3, 8, 4, 3, clear, 0, 4);
            doorTop.getTexture().setRGB(9, 8, 4, 3, clear, 0, 4);
            generator.finish(doorTop, generator.modLoc("block/" + blockObject.getId().getPath() + "_top"), consumer);
            BufferedImage itemTexture = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            itemTexture.setRGB(8, 0, 16, 16, doorTop.getTexture().getRGB(0, 0, 16, 16, null, 0, 16), 0, 16);
            itemTexture.setRGB(8, 16, 16, 16, doorBottom.getTexture().getRGB(0, 0, 16, 16, null, 0, 16), 0, 16);
            generator.finish(new TextureProvider.Texture(itemTexture, Optional.empty()),
                    generator.modLoc("item/" + blockObject.getId().getPath()), consumer);
        }
    }

    @Override public void registerItemModels(ItemModelGenerator generator)
    {
        for (RegistryObject<ColoredDoorBlock> blockObject : blockObjectMap.values())
        {
            generator.singleTexture(blockObject.getId().getPath(), generator.mcLoc("item/generated"), "layer0",
                    generator.modLoc("item/" + blockObject.getId().getPath()));
        }
    }

    @Override public void registerLootTables(LootTableGenerator generator)
    {
        for (RegistryObject<ColoredDoorBlock> blockObject : blockObjectMap.values())
        {
            generator.addLootTable(blockObject.get(),
                    (block) -> generator.registerLootTable(block, ExtendedBlockLootTables::droppingDoor));
        }
    }
}
