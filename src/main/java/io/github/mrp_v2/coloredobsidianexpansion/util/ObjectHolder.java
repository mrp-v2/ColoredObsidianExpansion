package io.github.mrp_v2.coloredobsidianexpansion.util;

import com.allkillernofiller.obsidianexpansion.ObsidianExpansion;
import com.allkillernofiller.obsidianexpansion.init.BlockInit;
import io.github.mrp_v2.coloredobsidianexpansion.ColoredObsidianExpansion;
import io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian.*;
import mrp_v2.additionalcolors.api.block_properties.BlockBasedPropertiesProvider;
import mrp_v2.additionalcolors.api.colored_block_data.AbstractColoredBlockData;
import mrp_v2.additionalcolors.api.colored_block_data.ColoredBlockDataHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ObjectHolder
{
    public static final ColoredBlockDataHandler COLORED_BLOCK_DATA_HANDLER;
    public static final Map<DyeColor, RegistryObject<? extends Block>> CRYING_OBSIDIAN_BLOCK_MAP = new HashMap<>();

    static
    {
        COLORED_BLOCK_DATA_HANDLER = new ColoredBlockDataHandler(ColoredObsidianExpansion.ID, ObsidianExpansion.TAB);
        addColorizedBlockDatas();
    }

    private static void addColorizedBlockDatas()
    {
        AbstractColoredBlockData<?> coloredCryingObsidianBlockData =
                mrp_v2.additionalcolors.util.ObjectHolder.COLORED_BLOCK_DATA_HANDLER
                        .getColoredBlockData(Blocks.CRYING_OBSIDIAN.getRegistryName());
        new CryingObsidianSlabBlockData(BlockInit.CRYING_OBSIDIAN_SLAB, coloredCryingObsidianBlockData)
                .setBlockPropertiesProvider(new BlockBasedPropertiesProvider(Blocks.CRYING_OBSIDIAN))
                .add(COLORED_BLOCK_DATA_HANDLER);
        new CryingObsidianStairsBlockData(BlockInit.CRYING_OBSIDIAN_STAIRS, coloredCryingObsidianBlockData)
                .setBlockPropertiesProvider(new BlockBasedPropertiesProvider(Blocks.CRYING_OBSIDIAN))
                .add(COLORED_BLOCK_DATA_HANDLER);
        new CryingObsidianDoorBlockData(BlockInit.CRYING_OBSIDIAN_DOOR, COLORED_BLOCK_DATA_HANDLER)
                .setBlockPropertiesProvider(new BlockBasedPropertiesProvider(Blocks.CRYING_OBSIDIAN))
                .addBlockTags(BlockTags.DOORS).addItemTags(ItemTags.DOORS).add(COLORED_BLOCK_DATA_HANDLER);
        new CryingObsidianGlassBlockData(BlockInit.CRYING_OBSIDIAN_GLASS, COLORED_BLOCK_DATA_HANDLER)
                .setBlockPropertiesProvider(new BlockBasedPropertiesProvider(Blocks.CRYING_OBSIDIAN))
                .addBlockTags(Tags.Blocks.GLASS, Tags.Blocks.GLASS_COLORLESS)
                .addItemTags(Tags.Items.GLASS, Tags.Items.GLASS_COLORLESS).add(COLORED_BLOCK_DATA_HANDLER);
        new CryingObsidianFenceBlockData(BlockInit.CRYING_OBSIDIAN_FENCE, coloredCryingObsidianBlockData)
                .setBlockPropertiesProvider(new BlockBasedPropertiesProvider(Blocks.CRYING_OBSIDIAN))
                .add(COLORED_BLOCK_DATA_HANDLER);
        new CryingObsidianFenceGateBlockData(BlockInit.CRYING_OBSIDIAN_FENCE_GATE, coloredCryingObsidianBlockData)
                .setBlockPropertiesProvider(new BlockBasedPropertiesProvider(Blocks.CRYING_OBSIDIAN))
                .add(COLORED_BLOCK_DATA_HANDLER);
    }

    public static void registerListeners(IEventBus modEventBus)
    {
        COLORED_BLOCK_DATA_HANDLER.register(modEventBus);
    }
}
