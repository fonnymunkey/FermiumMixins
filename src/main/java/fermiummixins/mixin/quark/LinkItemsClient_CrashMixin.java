package fermiummixins.mixin.quark;

import com.llamalad7.mixinextras.sugar.Local;
import io.netty.buffer.Unpooled;
import net.minecraft.inventory.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.management.feature.LinkItems;

@Mixin(LinkItems.class)
public abstract class LinkItemsClient_CrashMixin {

    @Inject(
            method = "keyboardEvent",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;"),
            cancellable = true
    )
    private void fermiummixins_quarkLinkItems_keyboardEvent(GuiScreenEvent.KeyboardInputEvent.Post event, CallbackInfo ci, @Local Slot slot) {
        if(slot.getStack().getTagCompound() != null) {
            PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
            int length = buf.writeCompoundTag(slot.getStack().getTagCompound()).writerIndex();
            if(length > 8000) ci.cancel();//Arbitrary, no real use for accurate max nothing should be this big
        }
    }
}