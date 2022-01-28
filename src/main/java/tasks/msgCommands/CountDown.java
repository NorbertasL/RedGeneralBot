package tasks.msgCommands;

import helperTools.MsgHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tasks.taskFrames.AbstractCommandTask;
import tasks.taskFrames.CredentialWrapper;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountDown extends AbstractCommandTask {
    private static final Logger logger = LogManager.getLogger(CountDown.class);
    public CountDown() {
        super("cd");
        this.setCredentialsWrapper(new CredentialWrapper()
                .addCredential(CredentialWrapper.KEYS.ROLES, "event manager"));
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

            logger.debug("System time is:"+ OffsetDateTime.now());

            String msg = msgArr[1].strip();
            if(msg.startsWith("to")){
                OffsetDateTime eventTS = event.getMessage().getTimeCreated();
                logger.debug("Msg local time is:"+eventTS);
                msg = msg.substring(2).strip();
                //-extract time if present
                //TODO do a check format and null check
                String timeString = msg.split(" ", 2)[0];
                logger.debug("Extracted time is :"+timeString);

                //Converts the string time into int array for h/m/s
                //TODO catch exception
                List<Integer> timeParts = Stream.of(timeString.split(":")).map(Integer::valueOf)
                        .collect(Collectors.toList());
                //TODO check if numbers are in the correct range
                if(timeParts.size() < 3){// No seconds so we add 0
                    timeParts.add(0);//Adding Seconds
                }
                timeParts.add(0);//Adding milliseconds, it will be needed for making OffsetDateTime object


                //-extract date if present
                //TODO do a check format and null check
                String dateString = null;//This is kind of bad
                try {
                    dateString = msg.split(" ",3)[1];
                }catch (ArrayIndexOutOfBoundsException e){
                    //Don't care, just means user didn't specify time
                }

                String message = "";
                List<Integer> dateParts = new ArrayList<>();
                if(dateString != null && dateString.matches("[0-9/]+")){
                    logger.debug("Extracted date is :"+dateString);
                    //Order we follow is DD/MM/YY or YYYY and DD/MM (no year we just assume this year)
                    dateParts = Stream.of(dateString.split("/")).map(Integer::parseInt)
                            .collect(Collectors.toList());
                    try{
                        message = msg.split(" ", 3)[2];
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Don't care, just mean there is no message
                        message = " ";
                    }


                }
                if(dateParts.isEmpty()){
                    dateParts.add(eventTS.getDayOfMonth()); //Adding day
                    dateParts.add(eventTS.getMonthValue()); //Adding month
                    dateParts.add(eventTS.getYear()); //Adding year
                }else if (dateParts.size() == 2){ // Need to add the tear
                    dateParts.add(eventTS.getYear()); //Adding year
                }

                //Constructing the new OffsetDateTime base dof user input
                OffsetDateTime eventTime = OffsetDateTime.of(dateParts.get(2), dateParts.get(1), dateParts.get(0)
                        , timeParts.get(0), timeParts.get(1), timeParts.get(2), timeParts.get(3), eventTS.getOffset());

                if (message.isEmpty()) {
                    try{
                        message = msg.split(" ", 2)[1];
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Don't care, just mean there is no message
                    }

                }
                // Printing out the CD
                event.getChannel().sendMessage("<t:"+eventTime.toEpochSecond()+":R> "+ message).queue();

            }


        }
    }


}
