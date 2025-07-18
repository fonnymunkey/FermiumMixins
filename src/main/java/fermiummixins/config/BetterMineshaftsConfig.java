package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@MixinConfig(name = FermiumMixins.MODID)
public class BetterMineshaftsConfig {
	
	@Config.Comment("Fixes mineshaft biome checks causing excess chunk generation")
	@Config.Name("Excess Mineshaft Chunk Generation Fix (BetterMineshafts)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bettermineshafts.performance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterMineshafts_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean excessMineshaftChunkGenerationFix = false;

	@Config.Comment("Allows for blacklisting biomes to prevent spawning of Mineshafts")
	@Config.Name("Mineshaft Biome Blacklist Patch (BetterMineshafts)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bettermineshafts.blacklist.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterMineshafts_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean betterMineshaftBiomeBlacklistPatch = false;
	
	@Config.Comment("Biome name blacklist to prevent Mineshafts from spawning" + "\n" +
			"Requires \"Mineshaft Biome Blacklist Patch (BetterMineshafts)\" enabled")
	@Config.Name("Mineshaft Biome Name Blacklist")
	public String[] betterMineshaftBiomeNameBlacklist = {""};
	
	@Config.Comment("Biome type blacklist to prevent Mineshafts from spawning" + "\n" +
			"Requires \"Mineshaft Biome Blacklist Patch (BetterMineshafts)\" enabled")
	@Config.Name("Mineshaft Biome Type Blacklist")
	public String[] betterMineshaftBiomeTypeBlacklist = {""};
	
	private Map<Biome, Boolean> mineshaftBiomeBlacklistMap = null;
	private Set<BiomeDictionary.Type> mineshaftBiomeTypeBlacklistSet = null;
	
	public boolean isMineshaftsBlacklistedFromBiome(Biome biome) {
		if(biome == null) return false;
		if(this.mineshaftBiomeBlacklistMap == null) {
			this.mineshaftBiomeBlacklistMap = new HashMap<>();
			for(String name : this.betterMineshaftBiomeNameBlacklist) {
				Biome reg = ForgeRegistries.BIOMES.getValue(new ResourceLocation(name));
				if(reg != null) this.mineshaftBiomeBlacklistMap.put(reg, true);
			}
		}
		Boolean val = this.mineshaftBiomeBlacklistMap.get(biome);
		if(val != null) return val;
		
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
		for(BiomeDictionary.Type type : this.getMineshaftBiomeTypesBlacklist()) {
			if(types.contains(type)) {
				this.mineshaftBiomeBlacklistMap.put(biome, true);
				return true;
			}
		}
		
		this.mineshaftBiomeBlacklistMap.put(biome, false);
		return false;
	}
	
	private Set<BiomeDictionary.Type> getMineshaftBiomeTypesBlacklist() {
		if(this.mineshaftBiomeTypeBlacklistSet == null) {
			this.mineshaftBiomeTypeBlacklistSet = new HashSet<>();
			for(String string : this.betterMineshaftBiomeTypeBlacklist) {
				this.mineshaftBiomeTypeBlacklistSet.add(BiomeDictionary.Type.getType(string));
			}
		}
		return this.mineshaftBiomeTypeBlacklistSet;
	}
	
	public void refreshConfig() {
		this.mineshaftBiomeBlacklistMap = null;
		this.mineshaftBiomeTypeBlacklistSet = null;
	}
}