package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.CampServer;
import io.brooklyn.camp.rest.util.CampRestUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

public abstract class AbstractCampRestResource {

    // can be injected by jersey when ManagementContext in not injected manually
    // (seems there is no way to make this optional so note it _must_ be injected;
    // most of the time that happens for free, but with test framework it doesn't,
    // so we have set up a NullServletContextProvider in our tests) 
    @Context ServletContext servletContext;
    
    private CampPlatform platform;
    private CampRestUtils util;
    
    public synchronized CampPlatform camp() {
        if (platform!=null) return platform;
        platform = (CampPlatform) servletContext.getAttribute(CampServer.CAMP_PLATFORM_ATTRIBUTE);
        return platform;
    }

    public synchronized CampRestUtils util() {
        if (util!=null) return util;
        util = new CampRestUtils(camp());
        return util;
    }
    

//    private ManagementContext managementContext;
//    private BrooklynRestResourceUtils brooklynRestResourceUtils;
//
//    public synchronized ManagementContext mgmt() {
//        if (managementContext!=null) return managementContext;
//        managementContext = (ManagementContext) servletContext.getAttribute(BrooklynServiceAttributes.BROOKLYN_MANAGEMENT_CONTEXT);
//        if (managementContext!=null) return managementContext;
//        
//        throw new IllegalStateException("ManagementContext not supplied for Brooklyn Jersey Resource "+this);
//    }
//    
//    public void injectManagementContext(ManagementContext managementContext) {
//        if (this.managementContext!=null) {
//            if (this.managementContext.equals(managementContext)) return;
//            throw new IllegalStateException("ManagementContext cannot be changed: specified twice for Brooklyn Jersey Resource "+this);
//        }
//        this.managementContext = managementContext;
//    }
//
//    public synchronized BrooklynRestResourceUtils brooklyn() {
//        if (brooklynRestResourceUtils!=null) return brooklynRestResourceUtils;
//        brooklynRestResourceUtils = new BrooklynRestResourceUtils(mgmt());
//        return brooklynRestResourceUtils;
//    }
    
}
