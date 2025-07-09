package fermiummixins.mixin.simpledifficulty;

import com.charles445.simpledifficulty.api.thirst.ThirstEnum;
import com.charles445.simpledifficulty.item.ItemCanteen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemCanteen.class)
public abstract class ItemCanteen_RainMixin {

    @ModifyVariable(
            method = "onItemRightClick",
            at = @At(value = "STORE"),
            index = 7
    )
    private ThirstEnum fermiummixins_simpleDifficultyItemCanteen_onItemRightClick(ThirstEnum value) {
        if(value.equals(ThirstEnum.RAIN)) return ThirstEnum.PURIFIED;
        return value;
    }
}