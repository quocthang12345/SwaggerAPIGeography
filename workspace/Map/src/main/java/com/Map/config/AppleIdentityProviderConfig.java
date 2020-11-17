package com.Map.config;

import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

class AppleIdentityProviderConfig extends OIDCIdentityProviderConfig {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

    AppleIdentityProviderConfig(IdentityProviderModel identityProviderModel) {
        super(identityProviderModel);
    }


	public String getKeyId() {
        return getConfig().get("XL38M52TAV");
    }

    public String getTeamId() {
        return getConfig().get("C9QT66NNBA");
    }
}