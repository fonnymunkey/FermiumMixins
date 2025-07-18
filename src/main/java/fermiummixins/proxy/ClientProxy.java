package fermiummixins.proxy;

import fermiummixins.handlers.bountifulbaubles.flare.EntityFlareNonAlbedo;
import fermiummixins.handlers.bountifulbaubles.flare.MovingSoundFlare;
import fermiummixins.wrapper.GammaWrapper;
import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerSubscribers() {
        super.registerSubscribers();
    }
    
    @Override
    public void setGamma(float min, float max) {
        GammaWrapper.setMinGamma(min);
        GammaWrapper.setMaxGamma(max);
    }
    
    @Override
    public void playSoundFlare(EntityFlareNonAlbedo flare) {
        if(flare != null) {
            Minecraft.getMinecraft().getSoundHandler().playSound(new MovingSoundFlare(flare));
        }
    }
}