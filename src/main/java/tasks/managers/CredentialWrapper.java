/**
 * Using Builder Design Pattern to build up a CredentialWrapper that stored all the roles that can access the commands
 * and channels/categories that listen for them or the ones that ignore them.
 */

package tasks.managers;
import java.util.HashMap;

public class CredentialWrapper {
    private HashMap<KEYS, String[]> credentials = new HashMap<>();

    public CredentialWrapper(){
        for (KEYS key: KEYS.values()){
           credentials.put(key, null);
        }
    }

    public CredentialWrapper addCredential(KEYS key, String...names){
        credentials.put(key, names);
        return this;
    }

    public HashMap getCredentials(){
        return credentials;
    }

    public enum KEYS{
        ROLES, ACTIVE_CHANNELS, IGNORED_CHANNELS, ACTIVE_CATEGORIES, IGNORED_CATEGORIES;
    }
}
