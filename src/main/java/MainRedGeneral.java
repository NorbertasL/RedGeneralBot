
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tasks.managers.MsgCommandManager;

import javax.security.auth.login.LoginException;


public class MainRedGeneral {
    public static JDA jda;
    private static final Logger logger = LogManager.getLogger(MainRedGeneral.class);

    public static void main(String[] args) throws LoginException, InterruptedException {

        //Checks for a token in args, if not preset will check env variable TOKEN
        final String TOKEN = (args.length >= 1 && args[0] != null) ? args[0] : System.getenv("TOKEN");
        if(TOKEN == null) {
            logger.error("Discord token not found!");
            return;
        }
        jda = JDABuilder.createDefault(TOKEN).build().awaitReady();

        //Add all event listeners
        jda.addEventListener(new MsgCommandManager());//Listens to text commands

        logger.info("\u001B[32mBot is up and running....");



    }
}
