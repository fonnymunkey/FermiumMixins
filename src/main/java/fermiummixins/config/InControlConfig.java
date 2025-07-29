package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class InControlConfig {

    @Config.Comment("InControl can apply actions on entities when they spawn like changing their stats or giving them held items. OptiFine saves entities that attempt to spawn to not have to construct them again, for performance.\n" +
            "This creates an interaction where InControl will often apply the same action multiple times on the same entity. \n" +
            "This fix makes InControl spawn actions only apply once per entity.")
    @Config.Name("Fix InControl spawn actions applied multiple times (InControl/OptiFine)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.incontrol.spawnrulefix.json", defaultValue = false)
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.InControl_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.Optifine_MODID,
            desired = true,
            reason = "Issue only arises if OptiFine is present"
    )
    public boolean fixSpawnActionRepetitionWithOptiFine = false;
}
