package fermiummixins.wrapper;

import com.charles445.simpledifficulty.api.SDDamageSources;
import net.minecraft.util.DamageSource;

import java.util.Arrays;
import java.util.List;

public abstract class SimpleDifficultyWrapper {
	
	public static List<DamageSource> getSDNonAttackDamageSources() {
		return Arrays.asList(
				SDDamageSources.DEHYDRATION,
				SDDamageSources.HYPERTHERMIA,
				SDDamageSources.HYPOTHERMIA,
				SDDamageSources.PARASITES);
	}
}