package io.brooklyn.camp.test.fixture;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

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
import io.brooklyn.camp.dto.PlatformDto;
import io.brooklyn.camp.test.mock.web.MockWebPlatform;

public class AbstractResourceTest extends JerseyTest {

    final String contextPath = "/camp";

    /**
     * TestNG creates all test class instances before running any tests. JerseyTest creates
     * each test server immediately on class instantiation. Together, these cause port
     * clashes and test failures. We work around this by giving each test its own port.
     */
    static final AtomicInteger perTestPort = new AtomicInteger(9080);

    @Override
    public AppDescriptor configure() {
        Injector injector = Guice.createInjector(new CampServletModule(contextPath));
        CampPlatform platform = injector.getInstance(CampPlatform.class);
        MockWebPlatform.populate((BasicCampPlatform) platform);

        return new WebAppDescriptor.Builder()
            .filterClass(GuiceFilter.class)
            .contextPath(contextPath)
            .servletPath("/")
            .clientConfig(new DefaultClientConfig(JacksonJaxbJsonProvider.class))
            .build();
    }

    @Override
    protected int getPort(int defaultPort) {
        return perTestPort.incrementAndGet();
    }

    public WebResource resource(String path) {
        return resource(path, Maps.<String, String>newHashMap());
    }

    public WebResource resource(String path, Map<String, String> queryParams) {
        if (path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }
        WebResource resource = resource().path(path);
        for (Entry<String, String> entry : queryParams.entrySet()) {
            resource = resource.queryParam(entry.getKey(), entry.getValue());
        }
        return resource;
    }

    public PlatformDto loadPlatform() {
        return resource("/v11").get(PlatformDto.class);
    }
}
