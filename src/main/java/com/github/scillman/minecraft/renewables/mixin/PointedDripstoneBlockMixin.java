package com.github.scillman.minecraft.renewables.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LandingBlock;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.block.Waterloggable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin extends Block implements LandingBlock, Waterloggable
{
    public PointedDripstoneBlockMixin(AbstractBlock.Settings settings)
    {
        super(settings);
    }

    /**
     * Checks if the BlockState is of a supported block.
     * @param instance The BlockState of the current block.
     * @param block The original block. (Blocks.MUD)
     * @param original The origianl function call.
     * @param currentBlock The block that has passed the isOf check.
     * @return True if the BlockState is of a supported block; otherwise, false.
     * @see {@link net.minecraft.block.PointedDripstoneBlock#dripTick dripTick}
     */
    @WrapOperation(method = "dripTick", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"
    ))
    private static boolean dripstone$sand$dripTick$isOf(BlockState instance, Block block, Operation<Boolean> original, @Share("currentBlock") LocalRef<Block> currentBlock)
    {
        if (instance.isOf(Blocks.DIRT))
        {
            currentBlock.set(Blocks.DIRT);
            return true;
        }

        currentBlock.set(block);
        return original.call(instance, block);
    }

    /**
     * Get the default state of the converted block. (e.g. Blocks.MUD > Blocks.CLAY)
     * @param instance The current block instance.
     * @param original The original function.
     * @param currentBlock The block that passed the isOf check. (e.g. current block)
     * @return The default BlockState of the block instance.
     * @see {@link net.minecraft.block.PointedDripstoneBlock#dripTick dripTick}
     */
    @WrapOperation(method = "dripTick", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/BlockState;"
    ))
    private static BlockState dripstone$sand$dripTick$getDefaultState(Block instance, Operation<BlockState> original, @Share("currentBlock") LocalRef<Block> currentBlock)
    {
        if (currentBlock.get() == Blocks.DIRT)
        {
            return Blocks.SAND.getDefaultState();
        }

        return original.call(instance);
    }

    /**
     * Get the fluid associated with the BlockState.
     * @param instance The BlockState of the block above the PointedDripstoneBlock.
     * @param block The original Block. (Blocks.MUD)
     * @param original The original function.
     * @return True if the fluid is Fluids.WATER; otherwise, false.
     * @see {@link net.minecraft.block.PointedDripstoneBlock#getFluid getFluid}
     */
    @WrapOperation(method = "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/PointedDripstoneBlock$DrippingFluid;", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"
    ))
    private static boolean dripstone$sand$getFluid$isOf(BlockState instance, Block block, Operation<Boolean> original)
    {
        if (instance.isOf(Blocks.DIRT))
        {
            return true;
        }
    
        return original.call(instance, block);
    }
}
