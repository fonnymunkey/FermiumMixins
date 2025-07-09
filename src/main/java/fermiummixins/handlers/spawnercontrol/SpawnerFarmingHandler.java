package fermiummixins.handlers.spawnercontrol;

import fermiummixins.wrapper.SpawnerControlWrapper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnerFarmingHandler {
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void livingExperienceDropEvent(LivingExperienceDropEvent event) {
		if(event.getEntityLiving() != null) {
			NBTTagCompound data = event.getEntityLiving().getEntityData();
			if(data.getBoolean("fermiummixins:spawnerbroken")) {
				event.setDroppedExperience(0);
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void livingDropsEvent(LivingDropsEvent event) {
		if(event.getEntityLiving() != null) {
			NBTTagCompound data = event.getEntityLiving().getEntityData();
			if(data.getBoolean("fermiummixins:spawnerbroken")) {
				event.getDrops().clear();
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public static void worldEventUnload(WorldEvent.Unload event) {
		if(!event.getWorld().isRemote) SpawnerControlWrapper.clearSpawnerMap(event.getWorld().provider.getDimension());
	}
}