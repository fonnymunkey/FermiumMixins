package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.item.ItemAmuletSinGluttony;
import net.minecraft.item.EnumAction;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemAmuletSinGluttony.class)
public abstract class ItemAmuletSinGluttony_DrinkMixin {

    @Inject(
            method = "onItemUse(Lnet/minecraftforge/event/entity/living/LivingEntityUseItemEvent$Tick;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/LivingEntityUseItemEvent$Tick;setDuration(I)V", shift = At.Shift.BEFORE),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_bountifulBaublesItemAmuletSingGluttony_onItemUseTick(LivingEntityUseItemEvent.Tick event, CallbackInfo ci) {
        if(event.getItem().getItem().getItemUseAction(event.getItem()) == EnumAction.DRINK) ci.cancel();
    }

    @Inject(
            method = "onItemUse(Lnet/minecraftforge/event/entity/living/LivingEntityUseItemEvent$Start;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/LivingEntityUseItemEvent$Start;setDuration(I)V", shift = At.Shift.BEFORE),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_bountifulBaublesItemAmuletSingGluttony_onItemUseStart(LivingEntityUseItemEvent.Start event, CallbackInfo ci) {
        if(event.getItem().getItem().getItemUseAction(event.getItem()) == EnumAction.DRINK) ci.cancel();
    }
}