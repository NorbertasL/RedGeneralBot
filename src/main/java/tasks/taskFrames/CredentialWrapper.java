/**
 * Using Builder Design Pattern to build up a CredentialWrapper that stored all the roles that can access the commands
 * and channels/categories that listen for them or the ones that ignore them.
 */

package tasks.taskFrames;
import java.util.HashMap;

public class CredentialWrapper {
    //All credential data is stored as a String array inside a hashmap with keys based on KEYS enum
    private HashMap<KEYS, String[]> credentials = new HashMap<>();

    //Making sure that all KEYS are initialized in HashMap
    public CredentialWrapper(){
        for (KEYS key: KEYS.values()){
           credentials.put(key, null);
        }
    }

    public CredentialWrapper addCredential(KEYS key, String...names){
        credentials.put(key, names);
        return this;
    }

    public String[] getCredentials(KEYS key){
        return credentials.get(key);
    }

    public enum KEYS{
        ROLES, ACTIVE_CHANNELS, ACTIVE_CATEGORIES;
    }
}
