package fermiummixins.wrapper;

import fermiummixins.util.ModLoadedUtil;
import net.minecraft.util.DamageSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class FirstAidWrapper {
	
	private static Set<DamageSource> nonAttackDamageSources = null;
	
	public static Set<DamageSource> getNonAttackDamageSources() {
		if(nonAttackDamageSources == null) {
			nonAttackDamageSources = new HashSet<>();
			nonAttackDamageSources.addAll(Arrays.asList(
					DamageSource.FALL,
					DamageSource.OUT_OF_WORLD,
					DamageSource.DROWN,
					DamageSource.HOT_FLOOR,
					DamageSource.IN_FIRE,
					DamageSource.ON_FIRE,
					DamageSource.LAVA,
					DamageSource.STARVE));
			if(ModLoadedUtil.isSimpleDifficultyLoaded()) {
				nonAttackDamageSources.addAll(SimpleDifficultyWrapper.getSDNonAttackDamageSources());
			}
		}
		return nonAttackDamageSources;
	}
}