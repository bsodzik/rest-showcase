package com.bsodzik;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.DeflateEncoder;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyConfiguration extends ResourceConfig {

	public JerseyConfiguration() {

		// Spring integration
		register(RequestContextFilter.class);

		// Logging requests and responses to console
		register(LoggingFilter.class);

		// GZIP encoding
		register(EncodingFilter.class);
		register(GZipEncoder.class);
		register(DeflateEncoder.class);

		// Conversion to JSON/XML
		register(JacksonFeature.class);

		packages("com.bsodzik.resources", "com.bsodzik.mapper");
	}
}
