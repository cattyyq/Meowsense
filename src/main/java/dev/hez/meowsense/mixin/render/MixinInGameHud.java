package dev.hez.meowsense.mixin.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.hez.meowsense.Client;
import dev.hez.meowsense.event.impl.render.EventRender2D;
import dev.hez.meowsense.utils.render.RenderUtils;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Inject(method = "render", at = @At("TAIL"))
    private void renderInjectEvent(DrawContext context, RenderTickCounter tickDelta, CallbackInfo ci) {
        RenderUtils.unscaledProjection();
        RenderUtils.scaledProjection();
        RenderSystem.applyModelViewMatrix();
        Client.INSTANCE.getEventManager().post(new EventRender2D(context, context.getScaledWindowWidth(), context.getScaledWindowHeight()));
    }
}
