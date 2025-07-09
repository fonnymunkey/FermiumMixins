package fermiummixins.mixin.vanilla;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGameOver.class)
public abstract class GuiGameOver_UnloadingFixMixin extends GuiScreen {
	
	@Inject(
			method = "actionPerformed",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", ordinal = 1)
	)
	private void fermiummixins_vanillaGuiGameOver_actionPerformed(GuiButton button, CallbackInfo ci) {
		if(this.mc.world != null) {
			this.mc.world.sendQuittingDisconnectingPacket();
		}
		this.mc.loadWorld(null);
	}
}