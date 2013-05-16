package io.brooklyn.camp.rest.resource;


import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.google.common.collect.Iterables;

public class CampRestResources {

    public static Iterable<AbstractCampRestResource> getCampRestResources() {
        List<AbstractCampRestResource> resources = new ArrayList<AbstractCampRestResource>();
        resources.add(new PlatformRestResource());
        resources.add(new PlatformComponentTemplateRestResource());
        return resources;
    }

    public static Iterable<Object> getApidocResources() {
        List<Object> resources = new ArrayList<Object>();
//        resources.add(new ApidocHelpMessageBodyWriter());
//        resources.add(new ApidocResource());
        return resources;
    }

    public static Iterable<Object> getMiscResources() {
        List<Object> resources = new ArrayList<Object>();
        resources.add(new JacksonJsonProvider());
        return resources;
    }

    public static Iterable<Object> getAllResources() {
        return Iterables.concat(getCampRestResources(), getApidocResources(), getMiscResources());
    }

}
