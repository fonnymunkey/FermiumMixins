package fermiummixins.handlers;

import fermiummixins.FermiumMixins;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = FermiumMixins.MODID)
public class RegistryHandler {
	
	public static SoundEvent FLARE_SHOOT = new SoundEvent(new ResourceLocation(FermiumMixins.MODID, "flare_shoot")).setRegistryName("flare_shoot");
	public static SoundEvent FLARE_BURN = new SoundEvent(new ResourceLocation(FermiumMixins.MODID, "flare_burn")).setRegistryName("flare_burn");
	
	@SubscribeEvent
	public static void registerSoundEvent(RegistryEvent.Register<SoundEvent> event) {
		if(ConfigHandler.BOUNTIFULBAUBLES_CONFIG.flareGunRework) {
			event.getRegistry().register(FLARE_SHOOT);
			event.getRegistry().register(FLARE_BURN);
		}
	}
}