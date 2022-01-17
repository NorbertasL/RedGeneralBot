package tasks.managers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tasks.msgCommands.Ping;
import tasks.taskFrames.AbstractCommandTask;

import java.util.ArrayList;
import java.util.List;

public class MsgCommandManager extends ListenerAdapter {
    private static final Logger logger = LogManager.getLogger(MsgCommandManager.class);

    //Stores all the tasks for this category
    List<AbstractCommandTask> tasks = new ArrayList<>();

    public MsgCommandManager(){
        //Add in all commandTasks
        tasks.add(new Ping());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        logger.debug("onMessageReceived");
        //Null check, if length() < 2 it cannot be a command, commands have to start with !
        if(event.getMessage() == null
                || event.getMessage().getContentRaw().length() < 2
                || event.getMessage().getContentRaw().charAt(0) != '!'){
            logger.debug("Not a command");
            return;
        }
        //removing the '!' from the command
        String callerCmd = event.getMessage().getContentRaw().split(" ")[0].substring(1);

        //Looping tough all commandTask and executing if command matches
        for (AbstractCommandTask task : tasks){
            if(task.getCommand().equalsIgnoreCase(callerCmd))
                task.runCommand(event);
        }

    }

}
