package ti4.buttons;

import java.util.List;

import net.dv8tion.jda.api.interactions.components.buttons.Button;
import ti4.helpers.Constants;

public class Buttons {
    public static final Button GET_A_TECH = green("acquireATech", "Get a Tech");
    public static final Button GET_A_FREE_TECH = green("acquireAFreeTech", "Get a Tech");
    public static final Button REDISTRIBUTE_CCs = green("redistributeCCButtons", "Redistribute CCs");

    // Cards Info Buttons
    public static final Button REFRESH_INFO = green("refreshInfoButtons", "Other Info");
    public static final Button REFRESH_AC_INFO = green("refreshACInfo", "Action Card Info");
    public static final Button REFRESH_PN_INFO = green("refreshPNInfo", "Promissory Notes Info");
    public static final Button REFRESH_SO_INFO = green("refreshSOInfo", "Secret Objectives Info");
    public static final Button REFRESH_ABILITY_INFO = green("refreshAbilityInfo", "Ability Info");
    public static final Button REFRESH_RELIC_INFO = green(Constants.REFRESH_RELIC_INFO, "Relic Info");
    public static final Button REFRESH_LEADER_INFO = green(Constants.REFRESH_LEADER_INFO, "Leader Info");
    public static final Button REFRESH_UNIT_INFO = green(Constants.REFRESH_UNIT_INFO, "Unit Info");
    public static final Button REFRESH_TECH_INFO = green(Constants.REFRESH_TECH_INFO, "Tech Info");
    public static final Button REFRESH_PLANET_INFO = green(Constants.REFRESH_PLANET_INFO, "Planet Info");

    public static final List<Button> REFRESH_INFO_BUTTONS = List.of(
        REFRESH_AC_INFO,
        REFRESH_PN_INFO,
        REFRESH_SO_INFO,
        REFRESH_ABILITY_INFO,
        REFRESH_RELIC_INFO,
        REFRESH_LEADER_INFO,
        REFRESH_UNIT_INFO,
        REFRESH_TECH_INFO,
        REFRESH_PLANET_INFO
    );


    public static Button blue(String buttonID, String buttonLabel) {
        return Button.primary(buttonID, buttonLabel);
    }

    public static Button gray(String buttonID, String buttonLabel) {
        return Button.secondary(buttonID, buttonLabel);
    }

    public static Button green(String buttonID, String buttonLabel) {
        return Button.success(buttonID, buttonLabel);
    }

    public static Button red(String buttonID, String buttonLabel) {
        return Button.danger(buttonID, buttonLabel);
    }
}
