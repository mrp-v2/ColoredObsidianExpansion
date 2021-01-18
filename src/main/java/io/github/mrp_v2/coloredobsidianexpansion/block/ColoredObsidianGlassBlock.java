package io.github.mrp_v2.coloredobsidianexpansion.block;

import com.allkillernofiller.obsidianexpansion.block.ObsidianGlass;
import io.github.mrp_v2.coloredobsidianexpansion.ColoredObsidianExpansion;
import mrp_v2.additionalcolors.util.IColored;
import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class ColoredObsidianGlassBlock extends ObsidianGlass implements IColored
{
    private final DyeColor color;

    public ColoredObsidianGlassBlock(Properties properties, DyeColor color)
    {
        super(properties);
        this.color = color;
    }

    @Override public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.getBlock().asItem().isIn(ItemTags.getCollection()
                .getTagByID(new ResourceLocation(ColoredObsidianExpansion.ID, "crying_obsidian_glass"))) ||
                super.isSideInvisible(state, adjacentBlockState, side);
    }

    @Override public DyeColor getColor()
    {
        return color;
    }
}
