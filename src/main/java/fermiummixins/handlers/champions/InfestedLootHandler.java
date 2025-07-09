package fermiummixins.handlers.champions;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InfestedLootHandler {
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void livingExperienceDropEvent(LivingExperienceDropEvent event) {
		if(event.getEntityLiving() != null) {
			NBTTagCompound data = event.getEntityLiving().getEntityData();
			if(data.getBoolean("fermiummixins:summoned")) {
				event.setDroppedExperience(0);
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void livingDropsEvent(LivingDropsEvent event) {
		if(event.getEntityLiving() != null) {
			NBTTagCompound data = event.getEntityLiving().getEntityData();
			if(data.getBoolean("fermiummixins:summoned")) {
				event.getDrops().clear();
				event.setCanceled(true);
			}
		}
	}
}