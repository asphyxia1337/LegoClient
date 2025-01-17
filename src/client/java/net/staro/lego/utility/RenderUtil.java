package net.staro.lego.utility;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.experimental.UtilityClass;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;

@UtilityClass
public class RenderUtil {
    public final BufferAllocator BUFFER = new BufferAllocator(2048);
    public final VertexConsumerProvider.Immediate IMMEDIATE = VertexConsumerProvider.immediate(BUFFER);

    public void minusrect(DrawContext drawContext, float x, float y, float width, float height, int color) {
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ONE_MINUS_DST_COLOR, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        rect(drawContext, x, y, width, height, color);
    }

    public void rect(DrawContext drawContext, float x, float y, float width, float height, int color) {
        Matrix4f matrix4f = drawContext.getMatrices().peek().getPositionMatrix();
        float a = (float) ColorHelper.Argb.getAlpha(color) / 255.0f;
        float r = (float) ColorHelper.Argb.getRed(color) / 255.0f;
        float g = (float) ColorHelper.Argb.getGreen(color) / 255.0f;
        float b = (float) ColorHelper.Argb.getBlue(color) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        GlStateManager._blendFuncSeparate(770, 771, 1, 0);
        bufferBuilder.vertex(matrix4f, x, y, 0f).color(r, g, b, a);
        bufferBuilder.vertex(matrix4f, x, y + height, 0f).color(r, g, b, a);
        bufferBuilder.vertex(matrix4f, x + width, y + height, 0f).color(r, g, b, a);
        bufferBuilder.vertex(matrix4f, x + width, y, 0f).color(r, g, b, a);
        GlStateManager._enableCull();
        RenderSystem.disableBlend();
    }

}
