package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.client.model.MuddyPigModel;
import baguchan.earthmobsmod.client.render.layer.FlowerColorLayer;
import baguchan.earthmobsmod.client.render.layer.MuddyPigSaddleLayer;
import baguchan.earthmobsmod.client.render.layer.PigSkinLayer;
import baguchan.earthmobsmod.entity.MuddyPigEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class MuddyPigRender extends MobRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> {
    private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");

    public MuddyPigRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MuddyPigModel<>(), 0.5F);
        this.addLayer(new MuddyPigSaddleLayer(this));
        this.addLayer(new PigSkinLayer(this));
        this.addLayer(new FlowerColorLayer(this));
    }

    @Override
    protected void applyRotations(MuddyPigEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.isRunning() && Entity.horizontalMag(entityLiving.getMotion()) > 1.0E-7D) {
            matrixStackIn.translate(0.0F, MathHelper.cos(ageInTicks * 0.64F) * 0.125F, 0.0F);
        }
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(MuddyPigEntity entity) {
        return PIG_TEXTURES;
    }
}