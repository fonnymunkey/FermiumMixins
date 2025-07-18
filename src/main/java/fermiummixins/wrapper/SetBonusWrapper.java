package fermiummixins.wrapper;

import com.fantasticsource.mctools.potions.FantasticPotionEffect;
import net.minecraft.potion.PotionEffect;

public abstract class SetBonusWrapper {
	
	public static boolean isFantasticEffect(PotionEffect effect) {
		return effect instanceof FantasticPotionEffect;
	}
}