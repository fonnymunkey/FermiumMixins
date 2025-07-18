package fermiummixins.wrapper;

import fermiummixins.FermiumMixins;
import org.apache.logging.log4j.Level;

public interface IBlockStateIdentity {
	
	default int fermiummixins$getIdentityKey() {
		FermiumMixins.LOGGER.log(Level.ERROR, "FermiumMixins BlockState Identity Registry Patch has encountered unexpected behavior, disable this patch.");
		return 0;
	}
}