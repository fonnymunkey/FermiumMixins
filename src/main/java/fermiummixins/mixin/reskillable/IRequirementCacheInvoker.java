package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.api.requirement.RequirementCache;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RequirementCache.class)
public interface IRequirementCacheInvoker {

    @Invoker(value = "getPlayer", remap = false)
    EntityPlayer invokeGetPlayer();
}