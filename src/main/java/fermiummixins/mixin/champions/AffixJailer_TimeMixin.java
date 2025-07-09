package fermiummixins.mixin.champions;

import c4.champions.common.affix.affix.AffixJailer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AffixJailer.class)
public abstract class AffixJailer_TimeMixin {

    @ModifyConstant(
            method = "onAttack",
            constant = @Constant(intValue = 5),
            remap = false
    )
    private int fermiummixins_championsAffixJailer_onAttack(int constant) {
        return 100;
    }
}