package fermiummixins.mixin.notreepunching;

import com.alcatrazescapee.notreepunching.common.items.ItemClayTool;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemClayTool.class)
public abstract class ItemClayTool_EnchantMixin {

    /**
     * @author fonnymunkey
     * @reason fix clay tool being abuse-able for guaranteed unbreaking enchants
     */
    @Overwrite(remap = false)
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}