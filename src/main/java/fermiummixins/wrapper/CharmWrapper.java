package fermiummixins.wrapper;

import net.minecraft.block.Block;
import svenhjol.charm.crafting.block.BlockCrate;

public abstract class CharmWrapper {
    public static boolean isBlockCrate(Block block) {
        return block instanceof BlockCrate;
    }
}