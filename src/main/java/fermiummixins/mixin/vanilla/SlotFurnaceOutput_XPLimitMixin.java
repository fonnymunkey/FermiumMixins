package fermiummixins.mixin.vanilla;

import net.minecraft.inventory.SlotFurnaceOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SlotFurnaceOutput.class)
public abstract class SlotFurnaceOutput_XPLimitMixin {
	
	@ModifyConstant(
			method = "onCrafting(Lnet/minecraft/item/ItemStack;)V",
			constant = @Constant(floatValue = 1.0F)
	)
	private float fermiummixins_vanillaSlotFurnaceOutput_onCrafting(float constant) {
		return Float.MAX_VALUE;
	}
}