package fermiummixins.mixin.advancedrocketry;

import fermiummixins.handlers.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import zmaster587.advancedRocketry.AdvancedRocketry;
import zmaster587.advancedRocketry.dimension.DimensionProperties;
import zmaster587.advancedRocketry.util.AstronomicalBodyHelper;

@Mixin(DimensionProperties.class)
public abstract class DimensionProperties_OverrideMixin {
	
	@Shadow(remap = false)
	public abstract String getName();
	
	@Redirect(
			method = "updateOrbit",
			at = @At(value = "INVOKE", target = "Lzmaster587/advancedRocketry/util/AstronomicalBodyHelper;getMoonOrbitalTheta(IF)D"),
			remap = false
	)
	private double fermiummixins_advancedRocketryDimensionProperties_updateOrbit0(int orbitalDistance, float parentGravitationalMultiplier) {
		return this.fermiummixins$getMoonOrbitalThetaModified(orbitalDistance, parentGravitationalMultiplier);
	}
	
	@Redirect(
			method = "updateOrbit",
			at = @At(value = "INVOKE", target = "Lzmaster587/advancedRocketry/util/AstronomicalBodyHelper;getOrbitalTheta(IF)D"),
			remap = false
	)
	private double fermiummixins_advancedRocketryDimensionProperties_updateOrbit1(int orbitalDistance, float solarSize) {
		return this.fermiummixins$getOrbitalThetaModified(orbitalDistance, solarSize);
	}
	
	@Unique
	private double fermiummixins$getOrbitalThetaModified(int orbitalDistance, float solarSize) {
		double orbitalPeriod = AstronomicalBodyHelper.getOrbitalPeriod(orbitalDistance, solarSize);
		Double mult = ConfigHandler.ADVANCEDROCKETRY_CONFIG.getOrbitalPeriodMult(this.getName());
		Double override = ConfigHandler.ADVANCEDROCKETRY_CONFIG.getOrbitalPeriodOverride(this.getName());
		if(mult != null) orbitalPeriod *= mult;
		if(override != null) orbitalPeriod = override;
		return (double)AdvancedRocketry.proxy.getWorldTimeUniversal(0) % (24000.0 * orbitalPeriod) / (24000.0 * orbitalPeriod) * 6.283185307179586;
	}
	
	@Unique
	private double fermiummixins$getMoonOrbitalThetaModified(int orbitalDistance, float parentGravitationalMultiplier) {
		double orbitalPeriod = AstronomicalBodyHelper.getMoonOrbitalPeriod((float)orbitalDistance, parentGravitationalMultiplier);
		Double mult = ConfigHandler.ADVANCEDROCKETRY_CONFIG.getOrbitalPeriodMult(this.getName());
		Double override = ConfigHandler.ADVANCEDROCKETRY_CONFIG.getOrbitalPeriodOverride(this.getName());
		if(mult != null) orbitalPeriod *= mult;
		if(override != null) orbitalPeriod = override;
		return (double)AdvancedRocketry.proxy.getWorldTimeUniversal(0) % (24000.0 * orbitalPeriod) / (24000.0 * orbitalPeriod) * 6.283185307179586;
	}
}