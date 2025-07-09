package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.handlers.ConfigHandler;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

import java.util.HashMap;
import java.util.Map;

@MixinConfig(name = FermiumMixins.MODID)
public class AdvancedRocketryConfig {
	
	@Config.Comment("Allows for setting orbital period overrides")
	@Config.Name("Orbital Overrides Patch (AdvancedRocketry)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.advancedrocketry.orbitaloverride.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.AdvancedRocketry_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean orbitalOverridesPatch = false;
	
	@Config.Comment("Allows for overriding the orbital period calculation result" + "\n" +
			"Format: String name, double value" + "\n" +
			"Requires \"Orbital Overrides Patch (AdvancedRocketry)\" enabled")
	@Config.Name("Orbital Period Overrides")
	public String[] orbitalPeriodOverrides = {};
	
	@Config.Comment("Allows for multiplying the orbital period calculation result if not overridden" + "\n" +
			"Format: String name, double multiplier" + "\n" +
			"Requires \"Orbital Overrides Patch (AdvancedRocketry)\" enabled")
	@Config.Name("Orbital Period Multipliers")
	public String[] orbitalPeriodMults = {};
	
	private Map<String, Double> orbitalPeriodOverrideMap = null;
	private Map<String, Double> orbitalPeriodMultMap = null;
	
	public Double getOrbitalPeriodOverride(String nameIn) {
		if(this.orbitalPeriodOverrideMap == null) {
			this.orbitalPeriodOverrideMap = new HashMap<>();
			for(String entry : ConfigHandler.ADVANCEDROCKETRY_CONFIG.orbitalPeriodOverrides) {
				try {
					if(entry.isEmpty()) continue;
					String[] arr = entry.split(",");
					if(arr.length != 2) continue;
					String name = arr[0].trim();
					if(name.isEmpty()) continue;
					double mult = Double.parseDouble(arr[1].trim());
					
					orbitalPeriodOverrideMap.put(name, mult);
				}
				catch(Exception ignored) {}
			}
		}
		return orbitalPeriodOverrideMap.get(nameIn);
	}
	
	public Double getOrbitalPeriodMult(String nameIn) {
		if(orbitalPeriodMultMap == null) {
			orbitalPeriodMultMap = new HashMap<>();
			for(String entry : ConfigHandler.ADVANCEDROCKETRY_CONFIG.orbitalPeriodMults) {
				try {
					if(entry.isEmpty()) continue;
					String[] arr = entry.split(",");
					if(arr.length != 2) continue;
					String name = arr[0].trim();
					if(name.isEmpty()) continue;
					double mult = Double.parseDouble(arr[1].trim());
					
					orbitalPeriodMultMap.put(name, mult);
				}
				catch(Exception ignored) {}
			}
		}
		return orbitalPeriodMultMap.get(nameIn);
	}
	
	public void refreshConfig() {
		this.orbitalPeriodOverrideMap = null;
		this.orbitalPeriodMultMap = null;
	}
}