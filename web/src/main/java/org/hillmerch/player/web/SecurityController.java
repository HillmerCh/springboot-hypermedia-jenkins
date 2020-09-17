package org.hillmerch.player.web;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SecurityController implements SecurityControllerDefinition {
	@Override
	public String getUserInfo(OidcUser oidcUser){
		return oidcUser.toString();
	}

	@Override
	public String getUserToken(OidcUser oidcUser){
		return oidcUser.getIdToken().getTokenValue();
	}
}
