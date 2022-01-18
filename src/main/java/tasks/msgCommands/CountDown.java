package tasks.msgCommands;

import helperTools.MsgHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import tasks.taskFrames.AbstractCommandTask;
import tasks.taskFrames.CredentialWrapper;

public class CountDown extends AbstractCommandTask {
    protected CountDown() {
        super("cd");
        this.setCredentialsWrapper(new CredentialWrapper()
                .addCredential(CredentialWrapper.KEYS.ROLES, "event_manager"));
    }

    @Override
    protected void execute(CredentialCheckResponse response, MessageReceivedEvent event) {
        if(response == CredentialCheckResponse.OK){
            /**
             * TODO
             * - Remove the cmd chars DONE
             * - Check if command is not empty DONE
             *
             * - if keyword 'to' is used:
             *      -extract date if present DD/MM/YYYY or DD/MM/YY or DD/MM.
             *          -DD can be single digit between 1 and 31(inclusive)
             *          -MM can be single digit between 1 and 12(inclusive)
             *          -YYYY can be between THIS YEAR + 1000(inclusive)
             *          -YY can be between 00 to 99(inclusive)
             *      -extract time if present hh:mm:ss or hh:mm
             *          -hh can be between 0 to 24(inclusive), 00 and 24 will be midnight
             *          -mm can be between 0 to 59(inclusive)
             *          -ss can be between 0 to 59(inclusive)
             *
             * - extract hh:mm:ss or hh:mm or hh
             *      -hh can be between 0 to 24(inclusive)
             *      -mm can be between 0 to 59(inclusive)
             *      -ss can be between 0 to 59(inclusive)
             *
             * - if number ends with h/m/s format time accordingly
             *      -h can be between 0 to 1 000(inclusive)
             *      -m can be between 0 to 50 000(inclusive)
             *      -s can be between 0 to 3 000 000(inclusive)
             *
             * - add msg to countdown
             * - add user that ran command to countdown
             */

            //Removing the cmd from msg
            String [] msgArr = event.getMessage().getContentRaw().split(" ", 2);
            if(msgArr.length != 2){
                //TODO Error, no cmd body
                event.getChannel().sendMessage("!cd has no body.\n"
                        + MsgHelper.getMsgUserNickOrName(event) +" do you need help?").queue();
                /**TODO reacti with :thumbsup: and :thumbsdown:
                 * if :thumbsup: PM user with command info and delete post
                 * if :thumbsdown: delete the post
                 */

                return;
            }
            String msg = msgArr[1].strip();
            if(msg.startsWith("to")){
                //-extract date if present
                //-extract time if present
            }


        }
    }


}
