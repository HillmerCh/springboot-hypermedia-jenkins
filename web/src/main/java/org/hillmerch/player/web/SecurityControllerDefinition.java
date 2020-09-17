package org.hillmerch.player.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "user", description = "Controller to get user logged info")
public interface SecurityControllerDefinition {


	@Operation(summary = "Gets info of the user Principal that is logged",
			operationId = "userInfo")
	@GetMapping("/info")
	String getUserInfo(@AuthenticationPrincipal OidcUser oidcUser);

	@Operation(summary = "Gets the JWT of the user Principal that is logged",
			operationId = "getUserToken")
	@GetMapping("/token")
	String getUserToken(@AuthenticationPrincipal OidcUser oidcUser);
}
