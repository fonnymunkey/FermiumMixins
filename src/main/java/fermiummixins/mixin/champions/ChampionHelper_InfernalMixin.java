package fermiummixins.mixin.champions;

import c4.champions.common.rank.Rank;
import c4.champions.common.rank.RankManager;
import c4.champions.common.util.ChampionHelper;
import fermiummixins.wrapper.InfernalMobsWrapper;
import net.minecraft.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChampionHelper.class)
public abstract class ChampionHelper_InfernalMixin {
	
	@Inject(
			method = "generateRank",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private static void fermiummixins_championsChampionHelper_generateRank_Infernal(EntityLiving entityLivingIn, CallbackInfoReturnable<Rank> cir) {
		if(entityLivingIn != null && InfernalMobsWrapper.isEntityInfernal(entityLivingIn)) cir.setReturnValue(RankManager.getEmptyRank());
	}
}