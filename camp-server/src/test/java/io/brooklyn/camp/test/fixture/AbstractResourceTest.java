package io.brooklyn.camp.test.fixture;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

import io.brooklyn.camp.BasicCampPlatform;
import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.CampServletModule;
import io.brooklyn.camp.test.mock.web.MockWebPlatform;

public class AbstractResourceTest extends JerseyTest {

    @Override
    public AppDescriptor configure() {
        Injector injector = Guice.createInjector(new CampServletModule());
        CampPlatform platform = injector.getInstance(CampPlatform.class);
        MockWebPlatform.populate((BasicCampPlatform) platform);

        return new WebAppDescriptor.Builder()
            .filterClass(GuiceFilter.class)
            .servletPath("/")
            .clientConfig(new DefaultClientConfig(JacksonJaxbJsonProvider.class))
            .build();
    }

    public WebResource resource(String path) {
        return resource(path, Maps.<String, String>newHashMap());
    }

    public WebResource resource(String path, Map<String, String> queryParams) {
        WebResource resource = resource().path(path);
        for (Entry<String, String> entry : queryParams.entrySet()) {
            resource = resource.queryParam(entry.getKey(), entry.getValue());
        }
        return resource;
    }

}
