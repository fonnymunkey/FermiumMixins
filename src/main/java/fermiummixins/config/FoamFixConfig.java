package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.HashSet;

@MixinConfig(name = FermiumMixins.MODID)
public class FoamFixConfig {
	
	@Config.Comment("Patch \"fixWorldEntityCleanup\" of FoamFix rarely creating ConcurrentModificationException crashes due to being older than the forge code it fixes.")
	@Config.Name("Patch World Entity Cleanup Fix (FoamFix)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.foamfix.unloadentities.json",
			lateMixin = "mixins.fermiummixins.late.foamfix.unloadentities.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FoamFix_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean patchUpdateEntitiesPatch = false;
}