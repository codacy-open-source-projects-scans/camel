/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.activemq6;

import javax.annotation.processing.Generated;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.component.jms.JmsComponentConfigurer;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointSchemaGeneratorMojo")
@SuppressWarnings("unchecked")
public class ActiveMQComponentConfigurer extends JmsComponentConfigurer implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        ActiveMQComponent target = (ActiveMQComponent) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "brokerurl":
        case "brokerURL": target.setBrokerURL(property(camelContext, java.lang.String.class, value)); return true;
        case "embedded": target.setEmbedded(property(camelContext, boolean.class, value)); return true;
        case "trustallpackages":
        case "trustAllPackages": target.setTrustAllPackages(property(camelContext, boolean.class, value)); return true;
        case "usepooledconnection":
        case "usePooledConnection": target.setUsePooledConnection(property(camelContext, boolean.class, value)); return true;
        case "usesingleconnection":
        case "useSingleConnection": target.setUseSingleConnection(property(camelContext, boolean.class, value)); return true;
        default: return super.configure(camelContext, obj, name, value, ignoreCase);
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "brokerurl":
        case "brokerURL": return java.lang.String.class;
        case "embedded": return boolean.class;
        case "trustallpackages":
        case "trustAllPackages": return boolean.class;
        case "usepooledconnection":
        case "usePooledConnection": return boolean.class;
        case "usesingleconnection":
        case "useSingleConnection": return boolean.class;
        default: return super.getOptionType(name, ignoreCase);
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        ActiveMQComponent target = (ActiveMQComponent) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "brokerurl":
        case "brokerURL": return target.getBrokerURL();
        case "embedded": return target.isEmbedded();
        case "trustallpackages":
        case "trustAllPackages": return target.isTrustAllPackages();
        case "usepooledconnection":
        case "usePooledConnection": return target.isUsePooledConnection();
        case "usesingleconnection":
        case "useSingleConnection": return target.isUseSingleConnection();
        default: return super.getOptionValue(obj, name, ignoreCase);
        }
    }
}

