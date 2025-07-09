package fermiummixins.wrapper;

import net.minecraft.item.ItemStack;
import vazkii.quark.base.asm.ASMHooks;

public abstract class QuarkWrapper {
	
	public static void applyRuneColor() {
		ASMHooks.applyRuneColor();
	}
	
	public static void setColorRuneTargetStack(ItemStack stack) {
		ASMHooks.setColorRuneTargetStack(stack);
	}
}