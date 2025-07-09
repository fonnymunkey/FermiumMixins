package fermiummixins.mixin.vanilla;

import fermiummixins.wrapper.IWorldProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(WorldProvider.class)
public abstract class WorldProvider_SleepWeatherMixin implements IWorldProvider {

    @Shadow protected World world;
    
    @Unique
    @Override
    public void fermiummixins$resetWeatherConditionally() {
        if(this.world.getWorldInfo().isRaining()) {
            this.world.getWorldInfo().setRainTime(0);
            this.world.getWorldInfo().setRaining(false);
        }
        if(this.world.getWorldInfo().isThundering()) {
            this.world.getWorldInfo().setThunderTime(0);
            this.world.getWorldInfo().setThundering(false);
        }
    }
}