package API;

import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//An api to generate bot events
public class EventGenerator {
    private static final Logger logger = LogManager.getLogger(EventGenerator.class);
    //TODO Events I would like to create
    //createTextChannel()
    //createVoiceChannel()
    //sentPrivateMessage()
    //reactToMessage()

    public void sendMessage(TextChannel textChannel, String msg){
        logger.debug(String.format("sendMessage: bot sending \"%s\" in %s", msg, textChannel.getName()));
        textChannel.sendMessage(msg);
    }
}
