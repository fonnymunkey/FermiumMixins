package fermiummixins.mixin.champions;

import c4.champions.common.affix.affix.AffixReflecting;
import c4.champions.common.capability.IChampionship;
import c4.champions.common.config.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Fix by Nischhelm
 */
@Mixin(AffixReflecting.class)
public abstract class AffixReflecting_DamageMixin {

	/**
	 * @author fonnymunkey/nischhelm
	 * @reason fix reflection modifying original damage to champion
	 */
	@Overwrite(remap = false)
	public float onDamaged(EntityLiving entity, IChampionship cap, DamageSource source, float amount, float newAmount) {
		Entity attacker = source.getTrueSource();
		if(attacker instanceof EntityLivingBase) {
			if(source.damageType.equals("reflecting") || (source instanceof EntityDamageSourceIndirect && ((EntityDamageSourceIndirect)source).getIsThornsDamage())) {
				return newAmount;
			}
			
			EntityDamageSource newSource = new EntityDamageSourceIndirect("reflecting", entity, null).setIsThornsDamage();
			float min = (float)ConfigHandler.affix.reflecting.minimumPerc;
			attacker.attackEntityFrom(newSource, (float)Math.min((double)amount * ((double)entity.getRNG().nextFloat() * (ConfigHandler.affix.reflecting.maximumPerc - (double)min) + (double)min), ConfigHandler.affix.reflecting.maxDamage));
		}
		return newAmount;
	}
}