package io.brooklyn.camp.rest.resource;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.impl.BasicResource;
import io.brooklyn.camp.rest.util.CampRestUtils;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.rest.util.WebResourceUtils;
import io.brooklyn.camp.util.collection.AbstractResourceListProvider;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

public abstract class AbstractCampRestResource {

    // can be injected by jersey when ManagementContext in not injected manually
    // (seems there is no way to make this optional so note it _must_ be injected;
    // most of the time that happens for free, but with test framework it doesn't,
    // so we have set up a NullServletContextProvider in our tests) 
    @Context ServletContext servletContext;
    
    private CampRestUtils util;
    
    public synchronized CampRestUtils util() {
        if (util!=null) return util;
        util = new CampRestUtils(servletContext);
        return util;
    }
    
    public CampPlatform camp() { return util().camp(); }
    public DtoFactory dto() { return util().dto(); }

    public static <T extends BasicResource> T lookup(AbstractResourceListProvider<T> list, String id) {
        T result = list.get(id);
        if (result==null)
            throw WebResourceUtils.notFound("No such element: %s", id);
        return result;
    }
    
}
