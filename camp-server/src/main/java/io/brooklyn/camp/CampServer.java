package io.brooklyn.camp;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;

import brooklyn.util.exceptions.Exceptions;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.util.dups.NetworkUtils;

public class CampServer {

    private static final Logger log = LoggerFactory.getLogger(CampServer.class);

    private final CampPlatform platform;
    private final String uriBase;

    private DtoFactory dtoFactory;

    private WebAppContext webAppContext;
    private Server server;

    @Inject
    public CampServer(CampPlatform platform, @Named("uriBase") String uriBase) {
        this.platform = platform;
        this.uriBase = uriBase;
    }

    public CampPlatform getPlatform() {
        return platform;
    }

    public String getUriBase() {
        return uriBase;
    }
    
    public DtoFactory getDtoFactory() {
        return dtoFactory;
    }

    @Inject
    public void setDtoFactory(DtoFactory dtoFactory) {
        this.dtoFactory = dtoFactory;
    }

    public synchronized CampServer start() {
        Injector injector = Guice.createInjector(new CampServletModule());
        return startWithFilter(injector.getInstance(GuiceFilter.class));
    }

    public synchronized CampServer startWithFilter(Filter filter) {
        if (webAppContext != null)
            throw new IllegalStateException("Already started");
        
        webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(
                // TODO if there is a GUI or other war...
                //findJsguiWebapp()!=null ? findJsguiWebapp() : 
                createTempWebDirWithIndexHtml("CAMP REST API <p> (gui not available)"));

        FilterHolder filterHolder = new FilterHolder(filter);
        webAppContext.addFilter(filterHolder, "/*", EnumSet.allOf(DispatcherType.class));

        server = startServer(webAppContext, "CAMP server");
        
        return this;
    }

    public synchronized void stop() {
        try {
            server.stop();
            server = null;
            webAppContext.stop();
            webAppContext = null;
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }
    
    private Server startServer(ContextHandler context, String summary) {
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
        
    /** create a directory with a simple index.html so we have some content being served up */
    private String createTempWebDirWithIndexHtml(String indexHtmlContent) {
        File dir = Files.createTempDir();
        dir.deleteOnExit();
        try {
            Files.write(indexHtmlContent, new File(dir, "index.html"), Charsets.UTF_8);
        } catch (IOException e) {
            throw Exceptions.propagate(e);
        }
        return dir.getAbsolutePath();
    }

}
