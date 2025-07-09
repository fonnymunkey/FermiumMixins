package fermiummixins.mixin.charm.vanilla;

import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.CharmWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityShulkerBox.class)
public abstract class TileEntityShulkerBox_InsertionMixin {

    @Inject(
            method = "canInsertItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaTileEntityShulkerBox_canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction, CallbackInfoReturnable<Boolean> cir) {
        if(ModLoadedUtil.isCharmLoaded()) {
            if(CharmWrapper.isBlockCrate(Block.getBlockFromItem(itemStackIn.getItem()))) cir.setReturnValue(false);
        }
    }
}