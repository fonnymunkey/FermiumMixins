package fermiummixins.mixin.champions;

import c4.champions.common.EventHandlerCommon;
import c4.champions.common.capability.IChampionship;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EventHandlerCommon.class)
public abstract class EventHandlerCommon_NameMixin {
    
    @Redirect(
            method = "livingDeath",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/management/PlayerList;sendMessage(Lnet/minecraft/util/text/ITextComponent;)V")
    )
    private void fermiummixins_championsEventHandlerCommon_livingDeath(PlayerList instance, ITextComponent component, @Local EntityLivingBase entity, @Local IChampionship championship) {
        instance.sendMessage(new TextComponentString(championship.getName())
                                     .appendSibling(new TextComponentString(" "))
                                     .appendSibling(entity.getCombatTracker().getDeathMessage()));
    }
}