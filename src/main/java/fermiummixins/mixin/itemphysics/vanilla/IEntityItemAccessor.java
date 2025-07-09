package fermiummixins.mixin.itemphysics.vanilla;

import net.minecraft.entity.item.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityItem.class)
public interface IEntityItemAccessor {

	@Accessor(value = "age")
	int getAgeFixed();
}