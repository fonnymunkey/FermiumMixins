package fermiummixins.wrapper;

import fermiummixins.util.ModLoadedUtil;
import lumien.bloodmoon.client.ClientBloodmoonHandler;

public abstract class BloodmoonWrapper {
	
	public static int manipulateRed(int position, int originalValue) {
		if(ModLoadedUtil.isOptifineLoaded()) return ClientBloodmoonHandler.INSTANCE.manipulateRed(position, originalValue);
		return originalValue;
	}
	
	public static int manipulateGreen(int position, int originalValue) {
		if(ModLoadedUtil.isOptifineLoaded()) return ClientBloodmoonHandler.INSTANCE.manipulateGreen(position, originalValue);
		return originalValue;
	}
	
	public static int manipulateBlue(int position, int originalValue) {
		if(ModLoadedUtil.isOptifineLoaded()) return ClientBloodmoonHandler.INSTANCE.manipulateBlue(position, originalValue);
		return originalValue;
	}
}