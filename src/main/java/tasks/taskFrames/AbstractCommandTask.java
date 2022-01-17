package tasks.taskFrames;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractCommandTask {
    private static final Logger logger = LogManager.getLogger(AbstractCommandTask.class);
    private final String command; //stores command
    private CredentialWrapper credentialWrapper; // stores credential requirements

    protected AbstractCommandTask(String command) {
        this.command = command;//task command
    }
    public String getCommand(){
        return command;
    }

    public void runCommand(MessageReceivedEvent event) {
        execute(credentialCheck(event), event); //does credential check before executing
    }

    protected abstract void execute(CredentialCheckResponse response, MessageReceivedEvent event);

    public void setCredentialsWrapper(CredentialWrapper credentialWrapper){
        this.credentialWrapper = credentialWrapper; //assigns credential class to variable
    }

    /**
     * Channel permission take priority over Category permissions
     * Example: Category is ignored but one of the channels in the category has enabled permissions.Result will be
     * that the channel will still listen and respond to the command but all other channels in the category will
     * ignore them.
     **/
    private CredentialCheckResponse credentialCheck(MessageReceivedEvent event) {
        // TODO category credential check
        logger.debug("credentialCheck");
        if (credentialWrapper == null)
            return CredentialCheckResponse.OK;
        Boolean channelOK = false;
        Boolean roleOK = false;

        //Active Channel check
        //If channel not assigned command will work on all channels
        if(credentialWrapper.getCredentials(CredentialWrapper.KEYS.ACTIVE_CHANNELS) == null){
            channelOK = true;
        }else {
            for(String channelName : credentialWrapper.getCredentials(CredentialWrapper.KEYS.ACTIVE_CHANNELS)){
                if (event.getChannel().getName().equalsIgnoreCase(channelName)){
                    channelOK = true;
                }
            }

        }

        //Rank check
        //If role not assigned everyone can use command
        if(credentialWrapper.getCredentials(CredentialWrapper.KEYS.ROLES) == null){
            roleOK = true;
        }else {
            for (String roleName : credentialWrapper.getCredentials(CredentialWrapper.KEYS.ROLES)){
                for(Role role: event.getMember().getRoles()){
                    if(role.getName().equalsIgnoreCase(roleName)){
                        roleOK = true;
                    }
                }
            }
        }

        if(!channelOK){
            return CredentialCheckResponse.INVALID_CHANNEL;
        }
        if (!roleOK){
            return CredentialCheckResponse.INVALID_ROLE;
        }

        //Passed all checks
        return CredentialCheckResponse.OK;
    }

    public enum CredentialCheckResponse {
        OK(),
        INVALID_ROLE(),
        INVALID_CATEGORY,
        INVALID_CHANNEL;
    }




}
