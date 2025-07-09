package fermiummixins.mixin.bountifulbaubles.vanilla;

import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityArrow.class)
public interface IEntityArrowAccessor {
	
	@Accessor("xTile")
	int getXTile();
	
	@Accessor("xTile")
	void setXTile(int xTile);
	
	@Accessor("yTile")
	int getYTile();
	
	@Accessor("yTile")
	void setYTile(int yTile);
	
	@Accessor("zTile")
	int getZTile();
	
	@Accessor("zTile")
	void setZTile(int zTile);
	
	@Accessor("inTile")
	Block getInTile();
	
	@Accessor("inTile")
	void setInTile(Block inTile);
	
	@Accessor("ticksInGround")
	int getTicksInGround();
	
	@Accessor("ticksInGround")
	void setTicksInGround(int ticksInGround);
}