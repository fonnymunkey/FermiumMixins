package fermiummixins.mixin.fancymenu;

import de.keksuccino.fancymenu.FancyMenu;
import fermiummixins.wrapper.FancyMenuWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.File;

@Mixin(FancyMenu.class)
public abstract class FancyMenu_CrashMixin {
	
	/**
	 * @author fonnymunkey
	 * @reason fix crashing when loaded on server
	 */
	@Overwrite(remap = false)
	public static File getGameDirectory() {
		return FancyMenu.isClientSide() ? FancyMenuWrapper.getGameDir() : new File("");
	}
}