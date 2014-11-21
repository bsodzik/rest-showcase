package com.bsodzik;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyConfiguration extends ResourceConfig {

	public JerseyConfiguration() {
		register(RequestContextFilter.class);
		register(LoggingFilter.class);
		register(JacksonFeature.class);
		packages("com.bsodzik.resources", "com.bsodzik.mapper");
	}
}
