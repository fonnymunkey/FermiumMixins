package fermiummixins.wrapper;

import c4.champions.common.capability.CapabilityChampionship;
import c4.champions.common.capability.IChampionship;
import c4.champions.common.util.ChampionHelper;
import net.minecraft.entity.EntityLiving;

public abstract class ChampionsWrapper {
	
	public static boolean isEntityChampion(EntityLiving entity) {
		IChampionship championship = CapabilityChampionship.getChampionship(entity);
		return championship != null && ChampionHelper.isElite(championship.getRank());
	}
}