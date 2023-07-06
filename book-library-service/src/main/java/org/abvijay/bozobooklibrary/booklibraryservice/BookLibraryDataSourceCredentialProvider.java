package org.abvijay.bozobooklibrary.booklibraryservice;

import io.quarkus.arc.Unremovable;
import io.quarkus.credentials.CredentialsProvider;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.eclipse.microprofile.opentracing.Traced;

@Traced
@ApplicationScoped
@Unremovable
@Named("booklibrary-db-credentials-provider")
public class BookLibraryDataSourceCredentialProvider  implements CredentialsProvider {
    
	@Override
	public Map<String, String> getCredentials(String credentialsProviderName) {

		Map<String, String> properties = new HashMap<>();

        String dbUser = System.getenv("dbUser");
        String dbPassword = System.getenv("dbPassword");

        System.out.println(dbUser);

		properties.put(USER_PROPERTY_NAME, dbUser);
		properties.put(PASSWORD_PROPERTY_NAME, dbPassword);
		return properties;
	}

}