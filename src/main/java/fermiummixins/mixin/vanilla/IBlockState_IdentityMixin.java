package fermiummixins.mixin.vanilla;

import fermiummixins.wrapper.IBlockStateIdentity;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IBlockState.class)
public interface IBlockState_IdentityMixin extends IBlockStateIdentity {

}