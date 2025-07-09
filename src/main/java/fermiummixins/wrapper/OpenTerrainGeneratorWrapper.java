package fermiummixins.wrapper;

import java.util.concurrent.locks.Lock;

public abstract class OpenTerrainGeneratorWrapper {
	
	public interface IObjectSpawner {
		
		Lock fermiummixins$getLock();
	}
}