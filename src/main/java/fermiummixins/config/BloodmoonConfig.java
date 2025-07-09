package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BloodmoonConfig {
	
	@Config.Comment("Prevents mobs spawned from Bloodmoon from being able to pick up loot (And then despawn with the loot)")
	@Config.Name("Bloodmoon Loot Pickup Fix (Bloodmoon)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.bloodmoon.lootpickup.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Bloodmoon_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bloodmoonLootPickupFix = false;
	
	@Config.Comment("Improves some checks in Bloodmoon spawning for performance")
	@Config.Name("Bloodmoon Spawner Performance (Bloodmoon)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bloodmoon.spawnerperformance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Bloodmoon_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bloodmoonSpawnerPerformance = false;
	
	@Config.Comment("Patches Bloodmoon's red light rendering to work properly when Optifine is installed")
	@Config.Name("Bloodmoon Optifine Compat Patch (Bloodmoon)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.bloodmoon.optifinerender.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Bloodmoon_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bloodmoonOptifineCompat = false;
}