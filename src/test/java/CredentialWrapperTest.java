import org.junit.Test;
import tasks.managers.CredentialWrapper;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CredentialWrapperTest {
    private CredentialWrapper credentialWrapper = new CredentialWrapper();
    private HashMap<CredentialWrapper.KEYS, String[]> credentials = credentialWrapper.getCredentials();

    @Test
    public void defaultCredentialSizeTest(){
        assertEquals(credentialWrapper.getCredentials().size(), CredentialWrapper.KEYS.values().length);
    }

    @Test
    public void emptyCredentialTest(){
        for (String[] s: credentials.values()){
            assertEquals(s, null);
        }
    }

    @Test
    public void addCredentialTest(){
        credentialWrapper.addCredential(CredentialWrapper.KEYS.ROLES, new String[]{"Admin", "Troll"});
        String[] roles = credentials.get(CredentialWrapper.KEYS.ROLES);
        assertEquals(roles.length, 2);
        assertEquals(roles[0], "Admin");
        assertEquals(roles[1], "Troll");

    }
}
