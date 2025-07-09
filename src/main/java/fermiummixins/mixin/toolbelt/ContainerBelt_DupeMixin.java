package fermiummixins.mixin.toolbelt;

import gigaherz.toolbelt.belt.ItemToolBelt;
import gigaherz.toolbelt.common.ContainerBelt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerBelt.class)
public abstract class ContainerBelt_DupeMixin extends Container {
    
    @Unique
    private int fermiummixins$blockedSlot;
    
    @Unique
    private ItemStack fermiummixins$heldStack;

    @Inject(
            method = "<init>",
            at = @At("RETURN"),
            remap = false
    )
    private void fermiummixins_toolbeltContainerBelt_init(IInventory playerInventory, int blockedSlot, ItemStack heldItem, CallbackInfo ci) {
        this.fermiummixins$blockedSlot = blockedSlot;
        this.fermiummixins$heldStack = heldItem;
    }

    /**
     * @author fonnymunkey
     * @reason fix interacting with gui while belt is not in slot
     */
    @Overwrite
    public boolean canInteractWith(EntityPlayer player) {
        return !this.fermiummixins$heldStack.isEmpty() && player.inventory.getStackInSlot(fermiummixins$blockedSlot).equals(this.fermiummixins$heldStack);
    }

    @Override
    public ItemStack slotClick(int slot, int dragType, ClickType clickType, EntityPlayer player) {
        return slot >= 0 && this.getSlot(slot) != null && this.getSlot(slot).getStack().getItem() instanceof ItemToolBelt
                ? ItemStack.EMPTY
                : super.slotClick(slot, dragType, clickType, player);
    }
}