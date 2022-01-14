import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.guild.voice.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EvenListener extends ListenerAdapter {

    private static final Logger logger = LogManager.getLogger(EvenListener.class);


    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event){//Does not trigger if user moves form a different channel
        onVoiceJoin(event);
        logger.debug(String.format("onGuildVoiceJoin: %s has joined %s Voice",
                event.getMember().getEffectiveName(),
                event.getChannelJoined().getName()));

    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event){
        onVoiceLeave(event);
        onVoiceJoin(event);
        logger.debug(String.format("onGuildVoiceMove: %s has moved from %s Voice to %s Voice",
                event.getMember().getEffectiveName(),
                event.getChannelLeft().getName(),
                event.getChannelJoined().getName()));
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){//Does not trigger if user moves to a different channel
        onVoiceLeave(event);
        logger.debug(String.format("onGuildVoiceLeave: %s has left %s Voice",
                event.getMember().getEffectiveName(),
                event.getChannelLeft().getName()));

    }

    @Override
    public void onChannelCreate(ChannelCreateEvent event){ // Triggers of both voice and text channels
        switch (event.getChannelType()){
            case TEXT -> onTextCreate(event);
            case VOICE -> onVoiceCreate(event);
        }
        logger.debug(String.format("onChannelCreate: A new %s %s channel has been created.",
                event.getChannel().getName(),
                event.getChannelType()));

    }
    @Override
    public void onChannelDelete(ChannelDeleteEvent event){
        switch (event.getChannelType()){
            case TEXT -> onTextDelete(event);
            case VOICE -> onVoiceDelete(event);
        }
        logger.debug(String.format("onChannelDelete: %s %s channel has been deleted.",
                event.getChannel().getName(),
                event.getChannelType()));
    }
    public void onMessageReceived(MessageReceivedEvent event) {
        switch (event.getChannelType()){
            case PRIVATE -> onBotPrivateMessage(event);
        }
        logger.debug(String.format("onMessageReceived: %s has posted \"%s\" in %s %s channel.",
                //WARNING event.getMember return null on Private Messages
                //Using event.getMessage().getAuthor().getName() with lambda to fix it
                event.getMember() == null ? event.getMessage().getAuthor().getName()
                        : event.getMember().getEffectiveName()+"("+event.getMessage().getAuthor().getAsTag()+")",
                event.getMessage(),
                event.getChannel().getName(),
                event.getChannelType()));

    }

    //TODO List of on methods I would like to implement
    //onBotPrivateMessage() //Triggers when the bot receives a private message
    //onReact() //Triggers when someone reacts to a message

    public void onVoiceJoin(GenericGuildVoiceUpdateEvent event){//Triggers even when moving channels
        logger.debug(String.format("onVoiceJoin: %s has joined %s Voice",
                event.getMember().getEffectiveName(),
                event.getChannelJoined().getName()));
    }

    public void onVoiceLeave(GenericGuildVoiceUpdateEvent event){//Triggers even when moving channels
        logger.debug(String.format("onVoiceLeave: %s has left %s Voice",
                event.getMember().getEffectiveName(),
                event.getChannelLeft().getName()));
    }

    public void onTextCreate(ChannelCreateEvent event){
        logger.debug(String.format("onTextCreate: A new %s TEXT channel has been created.",
                event.getChannel().getName()));
    }
    public void onVoiceCreate(ChannelCreateEvent event){
        logger.debug(String.format("onVoiceCreate: A new %s VOICE channel has been created.",
                event.getChannel().getName()));
    }
    public void onTextDelete(ChannelDeleteEvent event){
        logger.debug(String.format("onTextDelete: %s TEXT channel has been deleted.",
                event.getChannel().getName()));
    }
    public void onVoiceDelete(ChannelDeleteEvent event){
        logger.debug(String.format("onVoiceDelete: %s VOICE channel has been deleted.",
                event.getChannel().getName()));
    }

    public void onBotPrivateMessage(MessageReceivedEvent event){
        logger.debug(String.format("onBotPrivateMessage: %s has PMed bot with \"%s\".",
                event.getMessage().getAuthor().getAsTag(),
                event.getMessage()));

    }
}
