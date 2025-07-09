package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.item.ItemFlareGun;
import fermiummixins.handlers.bountifulbaubles.flare.EntityFlareNonAlbedo;
import fermiummixins.handlers.RegistryHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFlareGun.class)
public abstract class ItemFlareGun_FlareMixin {

    @Inject(
            method = "onItemRightClick",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isRemote:Z"),
            cancellable = true
    )
    private void fermiummixins_bountifulBaublesItemFlareGun_onItemRightClick(World world, EntityPlayer player, EnumHand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        if(!world.isRemote) {
            EntityFlareNonAlbedo flare = new EntityFlareNonAlbedo(world, player);
            flare.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.25F, 1.0F);
            world.spawnEntity(flare);
            world.playSound(null, player.posX, player.posY, player.posZ, RegistryHandler.FLARE_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.rand.nextFloat() * 0.4F + 1.2F)* 2.0F);
        }
		cir.setReturnValue(new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand)));
    }
}