package net.runelite.client.plugins.fatcat;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;

@PluginDescriptor(
        name = "Jus10",
        description = "cuts wood",
        tags = {"timestamp"},
        enabledByDefault = true
)
public class FatcatPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private FatcatConfig config;

    private ArrayList<LocalPoint> trees;

    @Inject
    private FatcatOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    private Robot robot;

    public ArrayList<GameObject> gameObjects;



    @Provides
    public FatcatConfig provideConfig(final ConfigManager configManager) {
        return configManager.getConfig(FatcatConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
        robot = new Robot();
        trees = new ArrayList<>();
    }

    @Override
    protected void shutDown() throws Exception {
    }

    @Subscribe
    private void onGameTick(GameTick tick) {
        getAllGameObjectsInScene();

        if (!trees.isEmpty()) {
            for (LocalPoint tree : trees) {
            }
        }
    }

    @Subscribe
    public void onGameObjectSpawned(final GameObjectSpawned event) {
    }

    public void getAllGameObjectsInScene() {
        Scene scene = client.getScene();
        if (scene != null) {
            Tile[][][] tiles = scene.getTiles();

            for (int x = 0; x < 104; x++) {
                for (int y = 0; y < 104; y++) {
                    Tile tile = tiles[client.getPlane()][x][y];
                    // Process tile (e.g., check if it's valid, access objects, etc.)
                    // Do something with the tile...
                    for (GameObject gameObj : tile.getGameObjects()) {
                        if (gameObj != null) {
                            ObjectComposition composition = client.getObjectDefinition(gameObj.getId());
                            if (composition.getName().toLowerCase().contains("tree")) {
                                trees.add(gameObj.getLocalLocation());
                            }
                        }
                    }
                }
            }
        }
    }
}