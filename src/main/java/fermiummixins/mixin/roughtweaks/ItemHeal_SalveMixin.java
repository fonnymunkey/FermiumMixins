package fermiummixins.mixin.roughtweaks;

import lellson.roughTweaks.items.ItemHeal;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemHeal.class)
public abstract class ItemHeal_SalveMixin {
	
	@Redirect(
			method = "onUsingTick",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;setHeldItem(Lnet/minecraft/util/EnumHand;Lnet/minecraft/item/ItemStack;)V")
	)
	private void fermiummixins_roughTweaksItemHeal_onUsingTick(EntityLivingBase instance, EnumHand hand, ItemStack stack) {
		instance.setHeldItem(hand, stack == ItemStack.EMPTY ? ItemStack.EMPTY : new ItemStack(stack.getItem(), stack.getCount(), stack.getMetadata()));
	}
}