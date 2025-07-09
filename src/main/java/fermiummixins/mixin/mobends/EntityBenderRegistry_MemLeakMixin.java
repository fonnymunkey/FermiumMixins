package fermiummixins.mixin.mobends;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.standard.main.ModConfig;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Fix thanks to Meldexun
 */
@Mixin(EntityBenderRegistry.class)
public abstract class EntityBenderRegistry_MemLeakMixin {

    @Shadow(remap = false)
    @Final
    private Map<Class<? extends EntityLivingBase>, EntityBender<?>> entityClassToBenderMap;

    @Unique
    private final Map<Class<? extends EntityLivingBase>, EntityBender<?>> fermiummixins$entityClassToBenderMapCache = new HashMap<>();

    /**
     * @author fonnymunkey
     * @reason fix memory leak found by Meldexun
     */
    @SuppressWarnings("unchecked")
    @Overwrite(remap = false)
    public <E extends EntityLivingBase> EntityBender<E> getForEntity(E entity) {
        return (EntityBender<E>)this.fermiummixins$entityClassToBenderMapCache.computeIfAbsent(entity.getClass(), key -> {
            if(ModConfig.shouldKeepEntityAsVanilla(entity)) {
                return null;
            }
            else {
                Class<? extends EntityLivingBase> entityClass = entity.getClass();

                for(EntityBender<?> entityBender : this.entityClassToBenderMap.values()) {
                    if(entityBender.entityClass.equals(entityClass)) {
                        return entityBender;
                    }
                }
                for(EntityBender<?> entityBender : this.entityClassToBenderMap.values()) {
                    if(entityBender.entityClass.isInstance(entity)) {
                        return entityBender;
                    }
                }
                return null;
            }
        });
    }

    /**
     * @author fonnymunkey
     * @reason fix memory leak found by Meldexun
     */
    @Overwrite(remap = false)
    public <E extends EntityLivingBase> void clearCache(E entity) {
        //noop
    }

    /**
     * @author fonnymunkey
     * @reason fix memory leak found by Meldexun
     */
    @Overwrite(remap = false)
    public void clearCache() {
        this.fermiummixins$entityClassToBenderMapCache.clear();
    }
}