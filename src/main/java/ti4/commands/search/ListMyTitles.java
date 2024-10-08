package ti4.commands.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.generator.Mapper;
import ti4.helpers.Constants;
import ti4.helpers.Emojis;
import ti4.helpers.Helper;
import ti4.map.Game;
import ti4.map.GameManager;
import ti4.map.Player;
import ti4.message.MessageHelper;

public class ListMyTitles extends SearchSubcommandData {

    public ListMyTitles() {
        super(Constants.SEARCH_MY_TITLES, "List all the titles you've acquired");
        addOptions(new OptionData(OptionType.USER, Constants.PLAYER, "Player to Show"));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getOption(Constants.PLAYER, event.getUser(), OptionMapping::getAsUser);
        StringBuilder sb = getPlayerTitles(user.getId(), user.getName());
        MessageHelper.sendMessageToThread(event.getChannel(), user.getName() + "'s Titles List", sb.toString());
    }

    public StringBuilder getPlayerTitles(String userID, String userName) {
        Predicate<Game> ignoreSpectateFilter = game -> game.getRealPlayerIDs().contains(userID);
        Predicate<Game> endedGamesFilter = game -> game.isHasEnded();
        Predicate<Game> allFilterPredicates = ignoreSpectateFilter.and(endedGamesFilter);

        Comparator<Game> mapSort = Comparator.comparing(Game::getGameNameForSorting);

        List<Game> games = GameManager.getInstance().getGameNameToGame().values().stream()
            .filter(allFilterPredicates)
            .sorted(mapSort)
            .toList();

        int index = 1;
        StringBuilder sb = new StringBuilder("**__").append(userName).append("'s Titles__**\n");
        Map<String, Integer> titles = new HashMap<String, Integer>();
        for (Game playerGame : games) {
            String singularGameTiles = playerGame.getFactionsThatReactedToThis("TitlesFor"+userID);
            if(!singularGameTiles.isEmpty()){
                for(String title : singularGameTiles.split("_")){
                    if(titles.containsKey(title)){
                        int amount = titles.get(title)+1;
                        titles.put(title, amount);
                    }else{
                        titles.put(title, 1);
                    }
                }
            }
        }
        for(String title : titles.keySet()){
            sb.append("`").append(Helper.leftpad("" + index, 2)).append(".`");
            sb.append("**"+title+"** x"+titles.get(title));
            sb.append("\n");
            index++;
        }
        if(titles.keySet().size() == 0){
            sb = new StringBuilder("No titles yet");
        }
       
        return sb;
    }


}
