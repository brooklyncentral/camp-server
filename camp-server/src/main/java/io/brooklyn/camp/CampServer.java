package io.brooklyn.camp;

import io.brooklyn.camp.rest.resource.CampRestResources;
import io.brooklyn.camp.util.NetworkUtils;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brooklyn.util.exceptions.Exceptions;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class CampServer {

    private static final Logger log = LoggerFactory.getLogger(CampServer.class);

    public static final String CAMP_PLATFORM_ATTRIBUTE = "io.brooklyn.camp.CampPlatform";
    
    final CampPlatform platform;
    WebAppContext webAppConcontext;
    
    public CampServer(CampPlatform platform) {
        this.platform = platform;
    }

    public CampPlatform getPlatform() {
        return platform;
    }
    
    public synchronized CampServer start() {
        if (webAppConcontext!=null)
            throw new IllegalStateException("Already started");
        
        webAppConcontext = new WebAppContext();
        webAppConcontext.setAttribute(CAMP_PLATFORM_ATTRIBUTE, getPlatform());
        webAppConcontext.setContextPath("/");
        //      webAppConcontext.setWar(findJsguiWebapp()!=null ? findJsguiWebapp() : createTempWebDirWithIndexHtml("Brooklyn REST API <p> (gui not available)"));
        CampServerUtils.installAsServletFilter(webAppConcontext);
        
        return this;
    }

    public static class CampServerUtils {

        public static void installAsServletFilter(ServletContextHandler context) {
            // TODO security
            //        installBrooklynPropertiesSecurityFilter(context);

            // now set up the REST servlet resources
            ResourceConfig config = new DefaultResourceConfig();
            // load all our REST API modules, JSON, and Swagger
            for (Object r: CampRestResources.getAllResources())
                config.getSingletons().add(r);

            // configure to match empty path, or any thing which looks like a file path with /assets/ and extension html, css, js, or png
            // and treat that as static content
            config.getProperties().put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "(/?|[^?]*/asserts/[^?]+\\.[A-Za-z0-9_]+)");

            // and anything which is not matched as a servlet also falls through (but more expensive than a regex check?)
            config.getFeatures().put(ServletContainer.FEATURE_FILTER_FORWARD_ON_404, true);

            // finally create this as a _filter_ which falls through to a web app or something (optionally)
            FilterHolder filterHolder = new FilterHolder(new ServletContainer(config));
            context.addFilter(filterHolder, "/*", EnumSet.allOf(DispatcherType.class));
        }

        public static Server startServer(ContextHandler context, String summary) {
            // FIXME port hardcoded
            Server server = new Server(NetworkUtils.nextAvailablePort(8080));
            server.setHandler(context);
            try {
                server.start();
            } catch (Exception e) {
                throw Exceptions.propagate(e);
            } 
            log.info("CAMP REST server started ("+summary+") on");
            log.info("  http://localhost:"+server.getConnectors()[0].getLocalPort()+"/");

            return server;
        }
    }

}
