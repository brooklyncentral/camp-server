package io.brooklyn.camp.rest.util;

import javax.servlet.ServletContext;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.CampServer;

public class CampRestUtils {

    private final ServletContext servletContext;
    private CampPlatform platform;
    private DtoFactory dto;
    
    public CampRestUtils(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public synchronized CampPlatform camp() {
        if (platform!=null) return platform;
        platform = (CampPlatform) servletContext.getAttribute(CampServer.CAMP_PLATFORM_ATTRIBUTE);
        return platform;
    }

    public DtoFactory dto() {
        if (dto!=null) return dto;
        dto = (DtoFactory) servletContext.getAttribute(CampServer.DTO_FACTORY);
        return dto;
    }
    
}
