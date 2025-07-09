package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.skill.magic.TraitGoldenOsmosis;
import fermiummixins.wrapper.DefiledLandsWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TraitGoldenOsmosis.class)
public abstract class TraitGoldenOsmosis_BookWyrmMixin {

    @Redirect(
            method = "tryRepair",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getIsRepairable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z")
    )
    private boolean fermiummixins_reskillableTraitGoldenOsmosis_tryRepair(Item instance, ItemStack toRepair, ItemStack repair) {
        return instance.getIsRepairable(toRepair, repair) || instance.getIsRepairable(toRepair, new ItemStack(DefiledLandsWrapper.getGoldenBookWyrmScale()));
    }
}