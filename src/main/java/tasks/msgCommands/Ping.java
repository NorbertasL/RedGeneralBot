package tasks.msgCommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tasks.taskFrames.AbstractCommandTask;
import tasks.taskFrames.CredentialWrapper;

public class Ping extends AbstractCommandTask {
    private static final Logger logger = LogManager.getLogger(Ping.class);

    public Ping() {
        super("ping");
        //Creating credentials
        CredentialWrapper cw = new CredentialWrapper();
        cw.addCredential(CredentialWrapper.KEYS.ACTIVE_CHANNELS, "bot");

        //Assigning credentials to itself
        this.setCredentialsWrapper(cw);
    }

    @Override
    protected void execute(CredentialCheckResponse response, MessageReceivedEvent event) {
        if (response == CredentialCheckResponse.OK) {
            logger.debug("response: OK");
            //event.getMember() returns null for PMs
            String callerName = event.getMember() == null
                    ? event.getMessage().getAuthor().getName() : event.getMember().getEffectiveName();
            event.getChannel().sendMessage("I'm here " + callerName + "!").queue();
        }else if (response == CredentialCheckResponse.INVALID_CHANNEL){
            logger.debug("response: INVALID_CHANNEL");
        }

    }
}
