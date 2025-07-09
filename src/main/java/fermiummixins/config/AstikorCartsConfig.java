package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class AstikorCartsConfig {
	
	@Config.Comment("Force disconnects carts when the distance between the cart and the puller in a single tick is too high (Causes carts to disappear)")
	@Config.Name("Cart Distance Fix (AstikorCarts)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.astikorcarts.cartdistance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.AstikorCarts_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean cartDistanceFix = false;
	
	@Config.Comment("Distance difference in a single tick that will cause Astikor Carts to become unpulled" + "\n" +
			"Requires \"Cart Distance Fix (AstikorCarts)\" enabled")
	@Config.Name("Cart Distance Limit")
	public double cartDistanceLimit = 32.0D;
	
	@Config.Comment("Disallows boss mobs from entering carts" + "\n" +
			"Additionally disallows Infernal/Blight/Champion mobs if InfernalMobs/ScalingHealth/Champions is loaded")
	@Config.Name("Boss Cart Prevention (AstikorCarts)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.astikorcarts.bosscart.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.AstikorCarts_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bossCartPrevention = false;
	
	@Config.Comment("Disallows all hostile mobs (IMob) from entering carts" + "\n" +
			"Requires \"Boss Cart Prevention (AstikorCarts)\" enabled")
	@Config.Name("All Mob Cart Prevention")
	public boolean allMobCartPrevention = false;
}