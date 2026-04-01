package com.dannyandson.tinygates.gates;

import java.util.List;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.blocks.PanelCellNeighbor;
import com.dannyandson.tinyredstone.blocks.PanelCellPos;
import com.dannyandson.tinyredstone.blocks.PanelTileRenderer;
import com.dannyandson.tinyredstone.blocks.Side;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public class ADDGate extends AbstractGate {

    private int output = 0;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_add_gate = output > 0 ? RenderHelper.getSprite(RenderHelper.TEXTURE_ADD_GATE_ON):RenderHelper.getSprite(RenderHelper.TEXTURE_ADD_GATE_OFF);

        RenderHelper.drawQuarterSlab(poseStack,builder,sprite_add_gate,sprite,combinedLight,alpha);
    }

    @Override
    public int getWeakRsOutput(Side side) {
        return (side==Side.FRONT)?this.output:0;
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {

        int output = 0;

        for (int i = 1; i < 4; i++) {
            //Just so happens that right, back, front are 1, 2, 3 in the enum
            PanelCellNeighbor input = cellPos.getNeighbor(Side.values()[i]);
            if (input != null) {
                output += input.getWeakRsOutput();
            }
        }

        if (output != this.output){
            this.output = Math.min(output, 15);
            return true;
        }

        return false;
    }
}
