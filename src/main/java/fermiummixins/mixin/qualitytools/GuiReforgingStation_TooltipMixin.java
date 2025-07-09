package fermiummixins.mixin.qualitytools;

import com.tmtravlr.qualitytools.reforging.GuiButtonReforgingStation;
import com.tmtravlr.qualitytools.reforging.GuiReforgingStation;
import com.tmtravlr.qualitytools.reforging.TileEntityReforgingStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fix by cdstk
 */
@Mixin(GuiReforgingStation.class)
public abstract class GuiReforgingStation_TooltipMixin extends GuiContainer {
	
	@Shadow(remap = false)
	@Final
	private TileEntityReforgingStation tileReforgingStation;
	
	@Shadow(remap = false)
	private GuiButtonReforgingStation reforgeButton;
	
	public GuiReforgingStation_TooltipMixin(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}
	
	@Inject(
			method = "drawScreen",
			at = @At("TAIL")
	)
	private void fermiummixins_qualityToolsGuiReforgingStation_drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
		ItemStack tool = this.tileReforgingStation.getStackInSlot(0);
		if(tool != null && !tool.isEmpty()) {
			if(mouseX >= reforgeButton.x && mouseY >= reforgeButton.y && mouseX < reforgeButton.x + reforgeButton.width && mouseY < reforgeButton.y + reforgeButton.height) {
				if(this.mc.player.inventory.getItemStack().isEmpty()) {
					this.renderToolTip(tool, mouseX, mouseY + 20);
				}
			}
		}
	}
}