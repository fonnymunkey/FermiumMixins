package fermiummixins.wrapper;

import goblinbob.mobends.standard.previewer.PlayerPreviewer;

public abstract class MoBendsWrapper {
    public static void clearPlayerPreview() {
        PlayerPreviewer.deletePreviewData();
    }
}