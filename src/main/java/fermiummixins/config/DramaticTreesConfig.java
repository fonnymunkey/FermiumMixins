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
public class DramaticTreesConfig {
	
	@Config.Comment("Disables unnecessary lighting checks on leaves during worldgen for performance")
	@Config.Name("WorldGen Leaves Lighting Lag Fix (DramaticTrees)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dramatictrees.lighting.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DramaticTrees_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean worldGenLeavesLighting = false;
	
	@Config.Comment("Cache leaf and branch AABBs to save on memory allocation usage")
	@Config.Name("Collision Box Cache (DramaticTrees)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dramatictrees.aabbcache.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DramaticTrees_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean collisionBoxCache = false;
	
	@Config.Comment("Overhauls tree falling such as making volume dependant on speed/size and allowing for passing through or breaking additional blocks")
	@Config.Name("Tree Falling Overhaul (DramaticTrees)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dramatictrees.overhaul.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DramaticTrees_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean treeFallingOverhaul = false;
	
	@Config.Comment("Prints the class names of solid blocks during tree collisions to console" + "\n" +
			"Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
	@Config.Name("Debug Tree Collision Names")
	public boolean treesCollisionNameDebug = false;
	
	@Config.Comment("List of blocks for falling trees to treat as non-solid when falling" + "\n" +
			"Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
	@Config.Name("Tree Falling Non-Solid Blocks")
	public String[] treeFallingNonSolidBlocks = {
			"dynamictrees:leaves0",
			"minecraft:leaves",
			"minecraft:vine",
			"minecraft:double_plant",
			"minecraft:tallgrass",
			"notreepunching:loose_rock/stone",
			"notreepunching:loose_rock/sandstone"
	};
	
	@Config.Comment("List of blocks from the non-solid list for falling trees to break while falling" + "\n" +
			"Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
	@Config.Name("Tree Falling Non-Solid Breakable Blocks")
	public String[] treeFallingNonSolidBreakableBlocks = {
			"dynamictrees:leaves0",
			"minecraft:leaves",
			"minecraft:vine",
			"minecraft:double_plant",
			"minecraft:tallgrass"
	};
	
	@Config.Comment("List of blocks for falling trees to treat as solid but still break while falling" + "\n" +
			"Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
	@Config.Name("Tree Falling Solid Breakable Blocks")
	public String[] treeFallingSolidBreakableBlocks = {
	};
	
	private HashSet<Block> treeFallingNonSolidList = null;
	private HashSet<Block> treeFallingNonSolidBreakableList = null;
	private HashSet<Block> treeFallingSolidBreakableList = null;
	
	public HashSet<Block> getTreeFallingNonSolidList() {
		if(this.treeFallingNonSolidList == null) {
			this.treeFallingNonSolidList = new HashSet<>();
			for(String string : this.treeFallingNonSolidBlocks) {
				Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string));
				if(block == null || block == Blocks.AIR) {
					FermiumMixins.LOGGER.log(Level.WARN, "FermiumMixins DramaticTree Non-Solid list invalid block: " + string + ", ignoring.");
					continue;
				}
				this.treeFallingNonSolidList.add(block);
			}
		}
		return this.treeFallingNonSolidList;
	}
	
	public HashSet<Block> getTreeFallingNonSolidBreakableList() {
		if(this.treeFallingNonSolidBreakableList == null) {
			this.treeFallingNonSolidBreakableList = new HashSet<>();
			for(String string : this.treeFallingNonSolidBreakableBlocks) {
				Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string));
				if(block == null || block == Blocks.AIR) {
					FermiumMixins.LOGGER.log(Level.WARN, "FermiumMixins DramaticTree Non-Solid Breakable list invalid block: " + string + ", ignoring.");
					continue;
				}
				this.treeFallingNonSolidBreakableList.add(block);
			}
		}
		return this.treeFallingNonSolidBreakableList;
	}
	
	public HashSet<Block> getTreeFallingSolidBreakableList() {
		if(this.treeFallingSolidBreakableList == null) {
			this.treeFallingSolidBreakableList = new HashSet<>();
			for(String string : this.treeFallingSolidBreakableBlocks) {
				Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string));
				if(block == null || block == Blocks.AIR) {
					FermiumMixins.LOGGER.log(Level.WARN, "FermiumMixins DramaticTree Solid Breakable list invalid block: " + string + ", ignoring.");
					continue;
				}
				this.treeFallingSolidBreakableList.add(block);
			}
		}
		return this.treeFallingSolidBreakableList;
	}
	
	public void refreshConfig() {
		this.treeFallingNonSolidList = null;
		this.treeFallingNonSolidBreakableList = null;
		this.treeFallingSolidBreakableList = null;
	}
}