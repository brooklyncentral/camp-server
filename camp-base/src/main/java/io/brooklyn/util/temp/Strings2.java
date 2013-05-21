package io.brooklyn.util.temp;

import com.google.common.base.Preconditions;

/** utility routines, to move to brooklyn-utils */
public class Strings2 {

    /** wraps a call to {@link String#format(String, Object...)} in a toString, i.e. using %s syntax,
     * useful for places where we want deferred evaluation 
     * (e.g. as message to {@link Preconditions} to skip concatenation when not needed) */
    public static FormattedString format(String pattern, Object... args) {
        return new FormattedString(pattern, args);
    }

    /** wraps a call to {@link String#format(String, Object...)} in a toString, i.e. using %s syntax,
     * useful for places where we want deferred evaluation 
     * (e.g. as message to {@link Preconditions} to skip concatenation when not needed) */
    public static class FormattedString {
        private final String pattern;
        private final Object[] args;
        public FormattedString(String pattern, Object[] args) {
            this.pattern = pattern;
            this.args = args;
        }
        @Override
        public String toString() {
            return String.format(pattern, args);
        }
        public String getPattern() {
            return pattern;
        }
        public Object[] getArgs() {
            return args;
        }
    }
}
