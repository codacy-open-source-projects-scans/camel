/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.knative.spi;

import javax.annotation.processing.Generated;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.component.knative.spi.KnativeSinkBinding;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.GenerateConfigurerMojo")
@SuppressWarnings("unchecked")
public class KnativeSinkBindingConfigurer extends org.apache.camel.support.component.PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        org.apache.camel.component.knative.spi.KnativeSinkBinding target = (org.apache.camel.component.knative.spi.KnativeSinkBinding) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "name": target.setName(property(camelContext, java.lang.String.class, value)); return true;
        case "objectapiversion":
        case "objectApiVersion": target.setObjectApiVersion(property(camelContext, java.lang.String.class, value)); return true;
        case "objectkind":
        case "objectKind": target.setObjectKind(property(camelContext, java.lang.String.class, value)); return true;
        case "type": target.setType(property(camelContext, org.apache.camel.component.knative.spi.Knative.Type.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "name": return java.lang.String.class;
        case "objectapiversion":
        case "objectApiVersion": return java.lang.String.class;
        case "objectkind":
        case "objectKind": return java.lang.String.class;
        case "type": return org.apache.camel.component.knative.spi.Knative.Type.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        org.apache.camel.component.knative.spi.KnativeSinkBinding target = (org.apache.camel.component.knative.spi.KnativeSinkBinding) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "name": return target.getName();
        case "objectapiversion":
        case "objectApiVersion": return target.getObjectApiVersion();
        case "objectkind":
        case "objectKind": return target.getObjectKind();
        case "type": return target.getType();
        default: return null;
        }
    }
}

