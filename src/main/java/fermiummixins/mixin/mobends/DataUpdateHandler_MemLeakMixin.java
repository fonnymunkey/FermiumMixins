package fermiummixins.mixin.mobends;

import fermiummixins.wrapper.MoBendsWrapper;
import goblinbob.mobends.core.addon.Addons;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.data.EntityDatabase;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DataUpdateHandler.class)
public abstract class DataUpdateHandler_MemLeakMixin {

    /**
     * @author fonnymunkey
     * @reason fix memory leak found by Meldexun
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if(event.phase != TickEvent.Phase.END && !Minecraft.getMinecraft().isGamePaused()) {
            if(Minecraft.getMinecraft().world == null) {
                EntityDatabase.instance.refresh();
                MoBendsWrapper.clearPlayerPreview();
            }
            else {
                EntityDatabase.instance.updateClient();
                if(Minecraft.getMinecraft().player != null) Addons.onClientTick();
            }
        }
    }
}