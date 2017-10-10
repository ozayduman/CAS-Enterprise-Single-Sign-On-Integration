package tr.com.ozayduman;

import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

/**
 * Created by ozayd on 2.10.2017.
 */
public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler{
    public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential usernamePasswordCredential, String s) throws GeneralSecurityException, PreventedException {
        if (isUserAuthenticated(usernamePasswordCredential)) {
            return createHandlerResult(usernamePasswordCredential, this.principalFactory.createPrincipal(usernamePasswordCredential.getUsername()), null);
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
