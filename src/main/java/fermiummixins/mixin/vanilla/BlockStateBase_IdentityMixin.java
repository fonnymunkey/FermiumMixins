package fermiummixins.mixin.vanilla;

import fermiummixins.wrapper.IBlockStateIdentity;
import fermiummixins.wrapper.BlockStateIdentityPatchWrapper;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockStateBase.class)
public abstract class BlockStateBase_IdentityMixin implements IBlockState, IBlockStateIdentity {
	
	//Registry is reference based, so each init is a new id
	@Unique
	private int fermiummixins$blockStateID = BlockStateIdentityPatchWrapper.getNewID();
	
	@Override
	public int fermiummixins$getIdentityKey() {
		return this.fermiummixins$blockStateID;
	}
}