package fermiummixins.mixin.switchbow;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.Whitedraco.switchbow.entity.EntityArrowLauncher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityArrowLauncher.class)
public class EntityArrowLauncherMixin {
    @ModifyReturnValue(method = "getArmorInventoryList", at = @At("RETURN"))
    private Iterable<ItemStack> fermiumMixins_switchBowEntityArrowLauncher_getArmorInventoryList(Iterable<ItemStack> original){
        if(original != null) return original;
        return NonNullList.withSize(4, ItemStack.EMPTY); //could return an empty list but if any mod assumes it's 4 entries, i'd rather have it be 4 entries
    }

    @ModifyReturnValue(method = "getItemStackFromSlot", at = @At("RETURN"))
    private ItemStack fermiumMixins_switchBowEntityArrowLauncher_getItemStackFromSlot(ItemStack original){
        if(original != null) return original;
        return ItemStack.EMPTY;
    }

    @ModifyReturnValue(method = "getPrimaryHand", at = @At("RETURN"))
    private EnumHandSide fermiumMixins_switchBowEntityArrowLauncher_getPrimaryHand(EnumHandSide original){
        if(original != null) return original;
        return EnumHandSide.RIGHT;
    }
}
