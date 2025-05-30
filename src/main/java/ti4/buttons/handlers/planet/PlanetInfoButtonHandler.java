package ti4.buttons.handlers.planet;

import lombok.experimental.UtilityClass;
import ti4.helpers.Constants;
import ti4.listeners.annotations.ButtonHandler;
import ti4.map.Player;
import ti4.service.planet.PlanetInfoService;

@UtilityClass
class PlanetInfoButtonHandler {

    @ButtonHandler(value = Constants.REFRESH_PLANET_INFO, save = false)
    public static void sendPlanetInfo(Player player) {
        PlanetInfoService.sendPlanetInfo(player);
    }
}
