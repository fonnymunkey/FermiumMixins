package fermiummixins.mixin.vanilla;

import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockDynamicLiquid.class)
public abstract class BlockDynamicLiquid_TickingMixin extends BlockLiquid {

    protected BlockDynamicLiquid_TickingMixin(Material materialIn) {
        super(materialIn);
    }

    @Override
    public boolean requiresUpdates() {
        return false;
    }
}