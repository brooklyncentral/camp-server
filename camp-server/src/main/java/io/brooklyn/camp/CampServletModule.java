package io.brooklyn.camp;

import java.util.Map;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.collect.Maps;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import brooklyn.rest.apidoc.ApidocHelpMessageBodyWriter;
import io.brooklyn.camp.rest.resource.ApidocResource;

public class CampServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        // Resources. Most resources are bound through com.sun.jerey.config.property.packages
        // configuration given to GuiceContainer below.
        bind(ApidocHelpMessageBodyWriter.class).toInstance(new ApidocHelpMessageBodyWriter());
        bind(ApidocResource.class).toInstance(new ApidocResource());

        // Config
        bind(String.class)
                .annotatedWith(Names.named("uriBase"))
                .toInstance("");

        // camp-base
        bind(CampPlatform.class).to(BasicCampPlatform.class).in(Scopes.SINGLETON);

        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // Use Jackson as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

        Map<String, String> parameters = Maps.newHashMap();

        // TODO: Necessary? Note '/asserts/' in URL. Don't think this is doing what we thought.
        // configure to match empty path, or any thing which looks like a file path with /assets/
        // and extension html, css, js, or png and treat that as static content
        parameters.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "(/?|[^?]*/asserts/[^?]+\\.[A-Za-z0-9_]+)");

        // and anything which is not matched as a servlet also falls through (but more expensive than a regex check?)
        parameters.put(ServletContainer.FEATURE_FILTER_FORWARD_ON_404, "true");

        // bind all resources in io.brooklyn.camp.rest.resource.
        parameters.put("com.sun.jersey.config.property.packages", "io.brooklyn.camp.rest.resource");

        filter("/*").through(GuiceContainer.class, parameters);
    }

}
