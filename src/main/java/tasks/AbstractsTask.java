package tasks;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import tasks.managers.CredentialWrapper;

public abstract class AbstractsTask {
    public abstract class AbstractCommand {
        public void runCommand(Message eventMessage, String vars) {
            CommandResponse response = credentialCheck(eventMessage.getMember(), eventMessage.getTextChannel());
            if (response == CommandResponse.OK) {
                execute(eventMessage, vars);
            }
        }

        protected abstract void execute(Message eventMessage, String vars);

        public abstract CredentialWrapper getCredentials();

        private CommandResponse credentialCheck(Member member, TextChannel channel) {
            // DB check the credentials, if empty use default

            return CommandResponse.OK;
        }


        public enum CommandResponse {
            OK(),
            INVALID_RANK(),
            INVALID_CATEGORY,
            INVALID_CHANNEL,
            IGNORED_CHANNEL;
        }
    }
}