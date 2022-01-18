package helperTools;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MsgHelper {
    public static String getMsgUserNickOrName(MessageReceivedEvent event){
        //event.getMember() returns null for PMs
        return event.getMember() == null
                ? event.getMessage().getAuthor().getName() : event.getMember().getEffectiveName();
    }

}
