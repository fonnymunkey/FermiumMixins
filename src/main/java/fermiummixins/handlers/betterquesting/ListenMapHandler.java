package fermiummixins.handlers.betterquesting;

import fermiummixins.mixin.betterquesting.IPlayerContainerListenerAccessor;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Fix thanks to Meldexun
 */
public class ListenMapHandler {
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		if(event.player == null || event.player.world.isRemote) return;
		IPlayerContainerListenerAccessor.getListenMap().entrySet()
										.removeIf(entry -> event.player == ((IPlayerContainerListenerAccessor)entry.getValue()).getPlayer());
	}
}