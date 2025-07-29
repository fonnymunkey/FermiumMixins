package fermiummixins.mixin.incontrol;

import fermiummixins.handlers.vanilla.InControlOptiFineHandler;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import mcjty.incontrol.rules.SpawnRule;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(SpawnRule.class)
public class SpawnRuleMixin {

    @Inject(
            method = "match(Lnet/minecraftforge/event/entity/living/LivingSpawnEvent$CheckSpawn;)Z",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void fermiumMixins_inControlSpawnRule_matchCheckSpawn(LivingSpawnEvent.CheckSpawn event, CallbackInfoReturnable<Boolean> cir){
        UUID uuid = event.getEntity().getUniqueID();
        if(InControlOptiFineHandler.savedUUIDS.contains(uuid)){
            cir.setReturnValue(false); //Don't apply actions on mobs that already have had those actions applied
            //Only needed when using OptiFine, as OptiFine caches constructed entities and reuses them for CheckSpawn until spawn the entity succeeds (risky fix by OptiFine but alright)
        } else
            InControlOptiFineHandler.savedUUIDS.add(uuid);
    }
}
