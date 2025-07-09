package fermiummixins.mixin.vanilla;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayer_LowerEyeMixin {

    /**
     * Lowers the eye height while crouched to better match the model like newer MC versions
     * Scales based on height different for compat with size change mods like Trinkets and Baubles
     */
    @ModifyConstant(
            method = "getEyeHeight",
            constant = @Constant(floatValue = 0.08F)
    )
    private float rlmixins_vanillaEntityPlayer_getEyeHeight(float constant) {
        return (((EntityPlayer)(Object)this).eyeHeight/((EntityPlayer)(Object)this).getDefaultEyeHeight()) * 0.30F;
    }
}