/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.main;

import javax.annotation.processing.Generated;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.main.LraConfigurationProperties;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.GenerateConfigurerMojo")
@SuppressWarnings("unchecked")
public class LraConfigurationPropertiesConfigurer extends org.apache.camel.support.component.PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        org.apache.camel.main.LraConfigurationProperties target = (org.apache.camel.main.LraConfigurationProperties) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "coordinatorcontextpath":
        case "coordinatorContextPath": target.setCoordinatorContextPath(property(camelContext, java.lang.String.class, value)); return true;
        case "coordinatorurl":
        case "coordinatorUrl": target.setCoordinatorUrl(property(camelContext, java.lang.String.class, value)); return true;
        case "enabled": target.setEnabled(property(camelContext, boolean.class, value)); return true;
        case "localparticipantcontextpath":
        case "localParticipantContextPath": target.setLocalParticipantContextPath(property(camelContext, java.lang.String.class, value)); return true;
        case "localparticipanturl":
        case "localParticipantUrl": target.setLocalParticipantUrl(property(camelContext, java.lang.String.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "coordinatorcontextpath":
        case "coordinatorContextPath": return java.lang.String.class;
        case "coordinatorurl":
        case "coordinatorUrl": return java.lang.String.class;
        case "enabled": return boolean.class;
        case "localparticipantcontextpath":
        case "localParticipantContextPath": return java.lang.String.class;
        case "localparticipanturl":
        case "localParticipantUrl": return java.lang.String.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        org.apache.camel.main.LraConfigurationProperties target = (org.apache.camel.main.LraConfigurationProperties) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "coordinatorcontextpath":
        case "coordinatorContextPath": return target.getCoordinatorContextPath();
        case "coordinatorurl":
        case "coordinatorUrl": return target.getCoordinatorUrl();
        case "enabled": return target.isEnabled();
        case "localparticipantcontextpath":
        case "localParticipantContextPath": return target.getLocalParticipantContextPath();
        case "localparticipanturl":
        case "localParticipantUrl": return target.getLocalParticipantUrl();
        default: return null;
        }
    }
}

