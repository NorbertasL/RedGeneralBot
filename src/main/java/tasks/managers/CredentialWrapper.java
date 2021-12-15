package tasks.managers;

public class CredentialWrapper {
    public final String[] ALLOWED_ROLES;
    public final String[] ACTIVE_CHANNELS;
    public final String[] IGNORED_CHANNELS;


    public CredentialWrapper(String[] ALLOWED_ROLES, String[] ACTIVE_CHANNELS, String[] IGNORED_CHANNELS) {
        this.ALLOWED_ROLES = ALLOWED_ROLES;
        this.ACTIVE_CHANNELS = ACTIVE_CHANNELS;
        this.IGNORED_CHANNELS = IGNORED_CHANNELS;
    }
}
