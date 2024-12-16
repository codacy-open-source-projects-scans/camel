/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.fury;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.PackageDataFormatMojo")
@SuppressWarnings("unchecked")
public class FuryDataFormatConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer {

    @Override
    public boolean configure(CamelContext camelContext, Object target, String name, Object value, boolean ignoreCase) {
        FuryDataFormat dataformat = (FuryDataFormat) target;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "unmarshaltype":
        case "unmarshalType": dataformat.setUnmarshalType(property(camelContext, java.lang.Class.class, value)); return true;
        case "requireclassregistration":
        case "requireClassRegistration": dataformat.setRequireClassRegistration(property(camelContext, boolean.class, value)); return true;
        case "threadsafe":
        case "threadSafe": dataformat.setThreadSafe(property(camelContext, boolean.class, value)); return true;
        case "allowautowiredfury":
        case "allowAutoWiredFury": dataformat.setAllowAutoWiredFury(property(camelContext, boolean.class, value)); return true;
        default: return false;
        }
    }

}
