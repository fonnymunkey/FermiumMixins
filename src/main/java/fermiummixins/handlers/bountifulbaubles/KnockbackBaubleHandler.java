package fermiummixins.handlers.bountifulbaubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import cursedflames.bountifulbaubles.item.ItemShieldCobalt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KnockbackBaubleHandler {

    @SubscribeEvent
    public static void onLivingKnockback(LivingKnockBackEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntity();
            if(player.getHeldItemMainhand().getItem() instanceof ItemShieldCobalt || player.getHeldItemOffhand().getItem() instanceof ItemShieldCobalt) {
                event.setCanceled(true);
                return;
            }
            
            IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
            for(int a = 0; a < handler.getSlots(); ++a) {
                if(handler.getStackInSlot(a).getItem() instanceof ItemShieldCobalt) {
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }
}