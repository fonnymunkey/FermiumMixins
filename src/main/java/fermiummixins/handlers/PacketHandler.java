package fermiummixins.handlers;

import fermiummixins.FermiumMixins;
import fermiummixins.handlers.quark.RightClickSignEditHandler;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    
    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(FermiumMixins.MODID);

    public static void init() {
        if(ConfigHandler.QUARK_CONFIG.syncSignEditConfig && ModLoadedUtil.isQuarkLoaded()) {
            instance.registerMessage(RightClickSignEditHandler.MessageSyncConfig.Handler.class, RightClickSignEditHandler.MessageSyncConfig.class, 0, Side.CLIENT);
        }
    }
}