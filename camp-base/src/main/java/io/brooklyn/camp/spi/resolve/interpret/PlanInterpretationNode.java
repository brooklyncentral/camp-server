package io.brooklyn.camp.spi.resolve.interpret;

import io.brooklyn.camp.spi.resolve.PlanInterpreter;

import java.util.Map;

import brooklyn.util.collections.MutableList;
import brooklyn.util.collections.MutableMap;

/** Helper class for {@link PlanInterpreter} instances, doing the recursive work */
public class PlanInterpretationNode {

    public enum Role { MAP_KEY, MAP_VALUE, LIST_ENTRY, YAML_PRIMITIVE }

    protected final PlanInterpretationNode parent;
    protected final Role roleInParent;
    protected final Object originalItem;
    protected final PlanInterpretationContext context;
    protected Object newItem = null;
    protected Boolean changed = null;

    /** creates a root node with {@link #apply()} called */
    public PlanInterpretationNode(PlanInterpretationContext context) {
        this.parent = null;
        this.roleInParent = null;
        this.originalItem = context.getOriginalDeploymentPlan();
        this.context = context;
        apply();
    }

    /** internal use: creates an internal node on which {@link #apply()} has *not* been called */
    protected PlanInterpretationNode(PlanInterpretationNode parent, Role roleInParent, Object originalItem) {
        this.parent = parent;
        this.roleInParent = roleInParent;
        this.originalItem = originalItem;
        this.context = parent.getContext();
    }

    public PlanInterpretationContext getContext() {
        return context;
    }

    protected void apply() {
        if (changed!=null) throw new IllegalStateException("can only be applied once");

        if (originalItem instanceof Map) {
            applyToMap();
        } else if (originalItem instanceof Iterable) {
            applyToIterable();
        } else {
            applyToYamlPrimitive();
        }
        
        if (changed==null) changed = false;
    }

    /** convenience for interpreters, returns true if node is a string and starts with the given prefix */
    public boolean startsWith(String prefix) {
        return (get() instanceof CharSequence) && (get().toString().startsWith(prefix));
    }
    
    public Object get() {
        if (changed==null || !isChanged()) return originalItem;
        return newItem;
    }

    public boolean isChanged() {
        if (changed==null) throw new IllegalStateException("not yet applied");
        return changed;
    }

    public void setNewItem(Object newItem) {
        this.newItem = newItem;
        this.changed = true;
    }

    protected PlanInterpretationNode newPlanInterpretation(PlanInterpretationNode parent, Role roleInParent, Object item) {
        return new PlanInterpretationNode(parent, roleInParent, item);
    }

    protected void applyToMap() {
        Map<Object, Object> input = MutableMap.<Object,Object>copyOf((Map<?,?>)originalItem);
        Map<Object, Object> result = MutableMap.<Object,Object>of();
        newItem = result;

        // first do a "whole-node" application
        if (getContext().getAllInterpreter().applyMapBefore(this, input)) {

            for (Map.Entry<Object,Object> entry: input.entrySet()) {
                // then recurse in to this node and do various in-the-node applications
                PlanInterpretationNode value = newPlanInterpretation(this, Role.MAP_VALUE, entry.getValue());
                value.apply();

                PlanInterpretationNode key = newPlanInterpretation(this, Role.MAP_KEY, entry.getKey());
                key.apply();

                if (key.isChanged() || value.isChanged()) 
                    changed = true;

                if (getContext().getAllInterpreter().applyMapEntry(this, input, result, key, value))
                    result.put(key.get(), value.get());
                else
                    changed = true;
            }

            // finally try applying to this node again
            getContext().getAllInterpreter().applyMapAfter(this, input, result);
        }

        if (changed==null) changed = false;
    }

    protected void applyToIterable() {
        MutableList<Object> input = MutableList.copyOf((Iterable<?>)originalItem);
        MutableList<Object> result = new MutableList<Object>();
        newItem = result;

        // first do a "whole-node" application
        if (getContext().getAllInterpreter().applyListBefore(this, input)) {

            for (Object entry: input) {
                // then recurse in to this node and do various in-the-node applications
                PlanInterpretationNode value = newPlanInterpretation(this, Role.LIST_ENTRY, entry);
                value.apply();

                if (value.isChanged()) 
                    changed = true;

                if (getContext().getAllInterpreter().applyListEntry(this, input, result, value))
                    result.add(value);
            }

            // finally try applying to this node again
            getContext().getAllInterpreter().applyListAfter(this, input, result);
        }

        if (changed==null) changed = false;
    }

    protected void applyToYamlPrimitive() {
        getContext().getAllInterpreter().applyYamlPrimitive(this);
    }

}
