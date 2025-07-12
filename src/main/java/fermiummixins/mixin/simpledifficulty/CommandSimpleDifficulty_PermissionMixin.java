package fermiummixins.mixin.simpledifficulty;

import com.charles445.simpledifficulty.command.CommandSimpleDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CommandSimpleDifficulty.class)
public class CommandSimpleDifficulty_PermissionMixin {
	
	@Inject(
			method = "getRequiredPermissionLevel",
			at = @At("HEAD"),
			cancellable = true
	)
	private void fermiummixins_simpleDifficultyCommandSimpleDifficulty_getRequiredPermissionLevel(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(4);
	}
}