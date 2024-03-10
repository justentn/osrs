package net.runelite.client.plugins.fatcat;

import lombok.AccessLevel;
import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.fishing.FishingConfig;
import net.runelite.client.plugins.fishing.FishingPlugin;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ProgressPieComponent;

import javax.inject.Inject;
import java.awt.*;

public class FatcatOverlay extends net.runelite.client.ui.overlay.Overlay  {
    private final FatcatPlugin plugin;
    private final FatcatConfig config;
    private final Client client;
    private final ItemManager itemManager;


    @Setter(AccessLevel.PACKAGE)
    private boolean hidden;

    @Inject
    private FatcatOverlay(FatcatPlugin plugin, FatcatConfig config, Client client, ItemManager itemManager)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        this.config = config;
        this.client = client;
        this.itemManager = itemManager;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        for (GameObject gameObject : plugin.gameObjects) {

            LocalPoint localPoint = gameObject.getLocalLocation();
            Point location = Perspective.localToCanvas(client, localPoint, client.getPlane());

            Color color = Color.ORANGE;
            if (location != null)
            {
                ProgressPieComponent pie = new ProgressPieComponent();
                pie.setFill(color);
                pie.setBorderColor(color);
                pie.setPosition(location);
                pie.render(graphics);
            }
        }
        return null;
    }
}
