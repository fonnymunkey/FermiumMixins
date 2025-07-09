package fermiummixins.handlers.bountifulbaubles.flare;

import fermiummixins.handlers.RegistryHandler;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.SoundCategory;

public class MovingSoundFlare extends MovingSound {

    private final EntityFlareNonAlbedo flare;

    public MovingSoundFlare(EntityFlareNonAlbedo flare) {
        super(RegistryHandler.FLARE_BURN, SoundCategory.NEUTRAL);
        this.flare = flare;
        this.repeat = true;
        this.repeatDelay = 0;
    }

    @Override
    public void update() {
        if(!this.flare.isDead) {
            this.xPosF = (float)this.flare.posX;
            this.yPosF = (float)this.flare.posY;
            this.zPosF = (float)this.flare.posZ;
            this.volume = 0.3F;
            this.pitch = 2.0F;
        }
        else this.donePlaying = true;
    }
}