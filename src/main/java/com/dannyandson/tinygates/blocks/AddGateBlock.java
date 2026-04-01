package com.dannyandson.tinygates.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AddGateBlock extends AbstractGateBlock{

    public AddGateBlock() {
        super();
    }

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new AddGateBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side == Side.LEFT || side == Side.RIGHT || side == Side.FRONT || side == Side.BACK;
    }
}
