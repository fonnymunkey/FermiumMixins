package fermiummixins.mixin.vanilla;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import fermiummixins.wrapper.BlockStateIdentityPatchWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObserver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

//This could be cleaner but im lazy, ATs slow gradle, ASM is too much effort
//If theres going to be an overwrite conflict its better to crash than explode the registry while in world
@Mixin(targets = "net.minecraftforge.registries.GameData$BlockCallbacks")
public abstract class GameData_IdentityMixin {
	
	@Unique
	private static final ResourceLocation fermiummixins$BLOCKSTATE_TO_ID = new ResourceLocation("minecraft:blockstatetoid");
	
	@Unique
	private static final ResourceLocation fermiummixins$BLOCK_TO_ITEM = new ResourceLocation("minecraft:blocktoitemmap");
	
	/**
	 * @author fonnymunkey
	 * @reason get
	 */
	@Overwrite(remap = false)
	public void onAdd(IForgeRegistryInternal<Block> owner, RegistryManager stage, int id, Block block, @Nullable Block oldBlock) {
		BlockStateIdentityPatchWrapper.ClearableObjectIntIdentityMapPatched blockstateMap = owner.getSlaveMap(fermiummixins$BLOCKSTATE_TO_ID, BlockStateIdentityPatchWrapper.ClearableObjectIntIdentityMapPatched.class);
		
		if(oldBlock != null) {
			for(IBlockState state : oldBlock.getBlockState().getValidStates()) {
				blockstateMap.remove(state);
			}
		}
		
		if("minecraft:tripwire".equals(block.getRegistryName().toString())) {
			for(int meta = 0; meta < 15; meta++) {
				blockstateMap.put(block.getStateFromMeta(meta), id << 4|meta);
			}
		}
		
		final boolean[] usedMeta = new boolean[16];
		for(IBlockState state : block.getBlockState().getValidStates()) {
			final int meta = block.getMetaFromState(state);
			blockstateMap.put(state, id << 4 | meta);
			usedMeta[meta] = true;
		}
		
		for(int meta = 0; meta < 16; meta++) {
			if(block.getClass() == BlockObserver.class) {
				continue;
			}
			if(usedMeta[meta]) {
				blockstateMap.put(block.getStateFromMeta(meta), id << 4|meta);
			}
		}
		
		if(oldBlock != null) {
			@SuppressWarnings("unchecked")
			BiMap<Block,Item> blockToItem = owner.getSlaveMap(fermiummixins$BLOCK_TO_ITEM, BiMap.class);
			Item item = blockToItem.get(oldBlock);
			if(item != null) {
				blockToItem.forcePut(block, item);
			}
		}
	}
	
	/**
	 * @author fonnymunkey
	 * @reason silly
	 */
	@Overwrite(remap = false)
	public void onClear(IForgeRegistryInternal<Block> owner, RegistryManager stage) {
		owner.getSlaveMap(fermiummixins$BLOCKSTATE_TO_ID, BlockStateIdentityPatchWrapper.ClearableObjectIntIdentityMapPatched.class).clear();
	}
	
	/**
	 * @author fonnymunkey
	 * @reason wit it
	 */
	@Overwrite(remap = false)
	public void onCreate(IForgeRegistryInternal<Block> owner, RegistryManager stage) {
		final BlockStateIdentityPatchWrapper.ClearableObjectIntIdentityMapPatched idMap = new BlockStateIdentityPatchWrapper.ClearableObjectIntIdentityMapPatched();
		owner.setSlaveMap(fermiummixins$BLOCKSTATE_TO_ID, idMap);
		owner.setSlaveMap(fermiummixins$BLOCK_TO_ITEM, HashBiMap.create());
	}
}