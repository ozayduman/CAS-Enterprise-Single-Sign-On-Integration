/**
 * Created by ozayd on 10.08.2017.
 */

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;

import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

public class TestAuthenticator extends AbstractUsernamePasswordAuthenticationHandler {

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential) throws GeneralSecurityException, PreventedException {
        if (isUserAuthenticated(credential)) {
            return createHandlerResult(credential, this.principalFactory.createPrincipal(credential.getUsername()), null);
        } else {
            throw new FailedLoginException();
        }
    }

    private boolean isUserAuthenticated(UsernamePasswordCredential credential) {
        if(credential.getUsername().equals(credential.getPassword())){
            return true;
        }
        return false;
    }
}
