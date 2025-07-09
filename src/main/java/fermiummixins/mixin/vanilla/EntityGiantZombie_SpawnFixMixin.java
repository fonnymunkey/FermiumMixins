package fermiummixins.mixin.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Fix by Kotlin
 */
@Mixin(EntityGiantZombie.class)
public class EntityGiantZombie_SpawnFixMixin extends EntityMob {
    
    public EntityGiantZombie_SpawnFixMixin(World worldIn) {
        super(worldIn);
    }
    
    @Override
    public boolean getCanSpawnHere() {
        IBlockState state = this.world.getBlockState((new BlockPos(this)).down());
        return state.canEntitySpawn(this);
    }
}