package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.entity.ModEntities;
import fermiummixins.handlers.bountifulbaubles.flare.EntityFlareNonAlbedo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModEntities.class)
public abstract class ModEntities_FlareMixin {

    @Redirect(
            method = "registerEntities",
            at = @At(value = "INVOKE", target = "Lcursedflames/bountifulbaubles/entity/ModEntities;register(Ljava/lang/String;Ljava/lang/Class;I)V"),
            remap = false
    )
    private static void fermiummixins_bountifulBaublesModEntities_registerEntities(String name, Class c, int id) {
        ModEntities.register(name, EntityFlareNonAlbedo.class, id);
    }
}