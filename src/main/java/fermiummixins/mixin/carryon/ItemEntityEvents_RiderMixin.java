package fermiummixins.mixin.carryon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tschipp.carryon.common.event.ItemEntityEvents;

@Mixin(ItemEntityEvents.class)
public abstract class ItemEntityEvents_RiderMixin {

    @Redirect(
            method = "onEntityRightClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setPosition(DDD)V")
    )
    private void fermiummixins_carryOnItemEntityEvents_onEntityRightClick(Entity instance, double x, double y, double z) {
        //Only teleport slimes to prevent duplication
        if(instance instanceof EntitySlime) instance.setPosition(x, y, z);
    }
}