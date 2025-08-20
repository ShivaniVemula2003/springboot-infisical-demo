package com.example.demo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infisical.sdk.InfisicalSdk;
import com.infisical.sdk.models.Secret;
@Service
public class SecretService {

	private final InfisicalSdk sdk;
	@Value("${infisical.project-id}")
    private String projectId;

    @Value("${infisical.env-slug}")
    private String envSlug;
	
    public SecretService(InfisicalSdk sdk) {
		this.sdk = sdk;
	}

	public String getUser(String username) throws Exception {
		var secret = sdk.Secrets().GetSecret(username, projectId, envSlug, "/", null, null, null);
		return secret.getSecretValue();
	}
	public String getPassword(String password) throws Exception {
		var secret = sdk.Secrets().GetSecret(password, projectId, envSlug, "/", null, null, null);
		return secret.getSecretValue();
	}
	public String getName(String db_name) throws Exception {
		var secret = sdk.Secrets().GetSecret(db_name, projectId, envSlug, "/", null, null, null);
		return secret.getSecretValue();
	}
	public Map<String, String> getAllSecrets() throws Exception {
		 List<Secret> secrets = sdk.Secrets().ListSecrets(projectId,envSlug,"/", null, null, null);
		 Map<String, String> props = new HashMap<>();
		 for(Secret s : secrets){
			 props.put(s.getSecretKey(), s.getSecretValue());
			 
			 
		 }
		return props;
	}
}
