package fermiummixins.mixin.lostcities;

import mcjty.lostcities.dimensions.world.LostWorldProvider;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LostWorldProvider.class)
public abstract class LostWorldProvider_RespawnMixin extends WorldProvider {

    @Override
    public boolean canRespawnHere() {
        return false;
    }
}