package io.github.mrp_v2.coloredobsidianexpansion.util;

import com.allkillernofiller.obsidianexpansion.ObsidianExpansion;
import com.allkillernofiller.obsidianexpansion.init.BlockInit;
import io.github.mrp_v2.coloredobsidianexpansion.ColoredObsidianExpansion;
import io.github.mrp_v2.coloredobsidianexpansion.util.cryingobsidian.*;
import mrp_v2.additionalcolors.api.ColoredBlockDataHandler;
import mrp_v2.additionalcolors.util.Util;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
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
    public static final DyeColor[] CRYING_OBSIDIAN_COLORS =
            new DyeColor[]{DyeColor.WHITE, DyeColor.ORANGE, DyeColor.YELLOW, DyeColor.GRAY, DyeColor.LIGHT_GRAY,
                    DyeColor.CYAN, DyeColor.BLUE, DyeColor.BROWN, DyeColor.GREEN, DyeColor.RED, DyeColor.BLACK};
    public static final Map<DyeColor, RegistryObject<? extends Block>> CRYING_OBSIDIAN_BLOCK_MAP = new HashMap<>();

    static
    {
        COLORED_BLOCK_DATA_HANDLER = new ColoredBlockDataHandler(ColoredObsidianExpansion.ID, ObsidianExpansion.TAB);
        addColorizedBlockDatas();
    }

    private static void addColorizedBlockDatas()
    {
        CryingObsidianBlockData coloredCryingObsidianBlockData =
                new CryingObsidianBlockData(Util.makeRegistryObject(Blocks.CRYING_OBSIDIAN), Util.makeTagArray(),
                        Util.makeTagArray(), COLORED_BLOCK_DATA_HANDLER)
                {
                    @Override protected AbstractBlock.Properties getBlockProperties()
                    {
                        return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN);
                    }
                };
        COLORED_BLOCK_DATA_HANDLER.add(coloredCryingObsidianBlockData);
        COLORED_BLOCK_DATA_HANDLER
                .add(new CryingObsidianSlabBlockData(BlockInit.CRYING_OBSIDIAN_SLAB, Util.makeTagArray(),
                        Util.makeTagArray(), coloredCryingObsidianBlockData, COLORED_BLOCK_DATA_HANDLER)
                {
                    @Override protected AbstractBlock.Properties getBlockProperties()
                    {
                        return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN);
                    }
                });
        COLORED_BLOCK_DATA_HANDLER
                .add(new CryingObsidianStairsBlockData(BlockInit.CRYING_OBSIDIAN_STAIRS, Util.makeTagArray(),
                        Util.makeTagArray(), coloredCryingObsidianBlockData, COLORED_BLOCK_DATA_HANDLER)
                {
                    @Override protected AbstractBlock.Properties getBlockProperties()
                    {
                        return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN);
                    }
                });
        COLORED_BLOCK_DATA_HANDLER
                .add(new CryingObsidianDoorBlockData(BlockInit.CRYING_OBSIDIAN_DOOR, Util.makeTagArray(BlockTags.DOORS),
                        Util.makeTagArray(ItemTags.DOORS), COLORED_BLOCK_DATA_HANDLER)
                {
                    @Override protected AbstractBlock.Properties getBlockProperties()
                    {
                        return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN);
                    }
                });
        COLORED_BLOCK_DATA_HANDLER.add(new CryingObsidianGlassBlockData(BlockInit.CRYING_OBSIDIAN_GLASS,
                Util.makeTagArray(Tags.Blocks.GLASS, Tags.Blocks.GLASS_COLORLESS),
                Util.makeTagArray(Tags.Items.GLASS, Tags.Items.GLASS_COLORLESS), COLORED_BLOCK_DATA_HANDLER)
        {
            @Override protected AbstractBlock.Properties getBlockProperties()
            {
                return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN).sound(SoundType.GLASS).notSolid();
            }
        });
        COLORED_BLOCK_DATA_HANDLER.add(new CryingObsidianFenceBlockData(BlockInit.CRYING_OBSIDIAN_FENCE,
                Util.makeTagArray(BlockTags.FENCES, Tags.Blocks.FENCES),
                Util.makeTagArray(ItemTags.FENCES, Tags.Items.FENCES), COLORED_BLOCK_DATA_HANDLER)
        {
            @Override protected AbstractBlock.Properties getBlockProperties()
            {
                return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN);
            }
        });
        COLORED_BLOCK_DATA_HANDLER.add(new CryingObsidianFenceGateBlockData(BlockInit.CRYING_OBSIDIAN_FENCE_GATE,
                Util.makeTagArray(BlockTags.FENCE_GATES, Tags.Blocks.FENCE_GATES),
                Util.makeTagArray(Tags.Items.FENCE_GATES), COLORED_BLOCK_DATA_HANDLER)
        {
            @Override protected AbstractBlock.Properties getBlockProperties()
            {
                return AbstractBlock.Properties.from(Blocks.CRYING_OBSIDIAN);
            }
        });
    }

    public static void registerListeners(IEventBus modEventBus)
    {
        COLORED_BLOCK_DATA_HANDLER.register(modEventBus);
    }
}
