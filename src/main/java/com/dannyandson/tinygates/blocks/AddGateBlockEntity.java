package com.dannyandson.tinygates.blocks;

import javax.annotation.Nullable;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class AddGateBlockEntity extends AbstractGateBlockEntity{
    public AddGateBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.ADD_GATE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
        if (this.output>0)
            return RenderHelper.TEXTURE_ADD_GATE_ON;
        return RenderHelper.TEXTURE_ADD_GATE_OFF;
    }

    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
        Direction leftDirection = getDirectionFromSide(Side.LEFT);
        Direction rightDirection = getDirectionFromSide(Side.RIGHT);
        Direction backDirection = getDirectionFromSide(Side.BACK);
        int leftSignal = getLevel().getSignal(getBlockPos().relative(leftDirection), leftDirection);
        int rightSignal =getLevel().getSignal(getBlockPos().relative(rightDirection), rightDirection);
        int bottomSignal =getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);

        int output = leftSignal + rightSignal + bottomSignal;

        if (output != this.output){
            this.output = output;
            return true;
        }

        return false;
    }
}
