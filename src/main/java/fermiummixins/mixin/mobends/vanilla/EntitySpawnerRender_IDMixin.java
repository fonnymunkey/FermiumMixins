package fermiummixins.mixin.mobends.vanilla;

import fermiummixins.wrapper.IEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class EntitySpawnerRender_IDMixin implements IEntity {
	
	@Unique
	private boolean fermiummixins$isFakeRender = false;
	
	@Unique
	@Override
	public void fermiummixins$setFakeEntity(boolean val) {
		this.fermiummixins$isFakeRender = val;
	}
	
	@Unique
	@Override
	public boolean fermiummixins$isFakeEntity() {
		return this.fermiummixins$isFakeRender;
	}
}