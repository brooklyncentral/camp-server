package io.brooklyn.camp.rest.resource;

import javax.inject.Inject;

import io.brooklyn.camp.CampPlatform;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.rest.util.WebResourceUtils;
import io.brooklyn.camp.spi.AbstractResource;
import io.brooklyn.camp.spi.collection.ResourceLookup;

public abstract class AbstractCampRestResource {

    @Inject
    DtoFactory dtoFactory;

    @Inject
    CampPlatform campPlatform;

    public static <T extends AbstractResource> T lookup(ResourceLookup<T> list, String id) {
        T result = list.get(id);
        if (result==null)
            throw WebResourceUtils.notFound("No such element: %s", id);
        return result;
    }
    
}
