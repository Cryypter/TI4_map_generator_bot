package ti4.commands.cardsac;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.helpers.Constants;
import ti4.helpers.Helper;
import ti4.map.Game;
import ti4.map.Player;
import ti4.message.MessageHelper;

public class DrawSpecificAC extends ACCardsSubcommandData {
    public DrawSpecificAC() {
        super(Constants.DRAW_SPECIFIC_AC, "Draw Specific Action Card");
        addOptions(new OptionData(OptionType.STRING, Constants.AC_ID, "ID of the card you want to draw").setRequired(true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Game activeGame = getActiveGame();
        Player player = activeGame.getPlayer(getUser().getId());
        player = Helper.getGamePlayer(activeGame, player, event, null);
        if (player == null) {
            MessageHelper.sendMessageToChannel(event.getChannel(), "Player could not be found");
            return;
        }
        OptionMapping option = event.getOption(Constants.AC_ID);
        int ac = player.getAc();
        if (option != null) {
            String providedID = option.getAsString();
            activeGame.drawSpecificActionCard(providedID, player.getUserID());
        }
        if(ac == player.getAc()){
            MessageHelper.sendMessageToChannel(event.getChannel(), "Card not drawn. It could be in someone's hand, or you could be using the wrong ID. Remember, you need the word ID (i.e scramble for scramble frequency) and not the number ID. You can find the word ID by proper usage of the /search command");
            return;
        }
        ACInfo.sendActionCardInfo(activeGame, player);
    }
}
