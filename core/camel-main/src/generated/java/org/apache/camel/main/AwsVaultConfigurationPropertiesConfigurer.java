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
import org.apache.camel.main.AwsVaultConfigurationProperties;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.GenerateConfigurerMojo")
@SuppressWarnings("unchecked")
public class AwsVaultConfigurationPropertiesConfigurer extends org.apache.camel.support.component.PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        org.apache.camel.main.AwsVaultConfigurationProperties target = (org.apache.camel.main.AwsVaultConfigurationProperties) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "accesskey":
        case "accessKey": target.setAccessKey(property(camelContext, java.lang.String.class, value)); return true;
        case "awsvaultconfiguration":
        case "awsVaultConfiguration": target.setAwsVaultConfiguration(property(camelContext, org.apache.camel.vault.AwsVaultConfiguration.class, value)); return true;
        case "azurevaultconfiguration":
        case "azureVaultConfiguration": target.setAzureVaultConfiguration(property(camelContext, org.apache.camel.vault.AzureVaultConfiguration.class, value)); return true;
        case "defaultcredentialsprovider":
        case "defaultCredentialsProvider": target.setDefaultCredentialsProvider(property(camelContext, boolean.class, value)); return true;
        case "gcpvaultconfiguration":
        case "gcpVaultConfiguration": target.setGcpVaultConfiguration(property(camelContext, org.apache.camel.vault.GcpVaultConfiguration.class, value)); return true;
        case "hashicorpvaultconfiguration":
        case "hashicorpVaultConfiguration": target.setHashicorpVaultConfiguration(property(camelContext, org.apache.camel.vault.HashicorpVaultConfiguration.class, value)); return true;
        case "profilecredentialsprovider":
        case "profileCredentialsProvider": target.setProfileCredentialsProvider(property(camelContext, boolean.class, value)); return true;
        case "profilename":
        case "profileName": target.setProfileName(property(camelContext, java.lang.String.class, value)); return true;
        case "refreshenabled":
        case "refreshEnabled": target.setRefreshEnabled(property(camelContext, boolean.class, value)); return true;
        case "refreshperiod":
        case "refreshPeriod": target.setRefreshPeriod(property(camelContext, long.class, value)); return true;
        case "region": target.setRegion(property(camelContext, java.lang.String.class, value)); return true;
        case "secretkey":
        case "secretKey": target.setSecretKey(property(camelContext, java.lang.String.class, value)); return true;
        case "secrets": target.setSecrets(property(camelContext, java.lang.String.class, value)); return true;
        case "sqsqueueurl":
        case "sqsQueueUrl": target.setSqsQueueUrl(property(camelContext, java.lang.String.class, value)); return true;
        case "usesqsnotification":
        case "useSqsNotification": target.setUseSqsNotification(property(camelContext, boolean.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "accesskey":
        case "accessKey": return java.lang.String.class;
        case "awsvaultconfiguration":
        case "awsVaultConfiguration": return org.apache.camel.vault.AwsVaultConfiguration.class;
        case "azurevaultconfiguration":
        case "azureVaultConfiguration": return org.apache.camel.vault.AzureVaultConfiguration.class;
        case "defaultcredentialsprovider":
        case "defaultCredentialsProvider": return boolean.class;
        case "gcpvaultconfiguration":
        case "gcpVaultConfiguration": return org.apache.camel.vault.GcpVaultConfiguration.class;
        case "hashicorpvaultconfiguration":
        case "hashicorpVaultConfiguration": return org.apache.camel.vault.HashicorpVaultConfiguration.class;
        case "profilecredentialsprovider":
        case "profileCredentialsProvider": return boolean.class;
        case "profilename":
        case "profileName": return java.lang.String.class;
        case "refreshenabled":
        case "refreshEnabled": return boolean.class;
        case "refreshperiod":
        case "refreshPeriod": return long.class;
        case "region": return java.lang.String.class;
        case "secretkey":
        case "secretKey": return java.lang.String.class;
        case "secrets": return java.lang.String.class;
        case "sqsqueueurl":
        case "sqsQueueUrl": return java.lang.String.class;
        case "usesqsnotification":
        case "useSqsNotification": return boolean.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        org.apache.camel.main.AwsVaultConfigurationProperties target = (org.apache.camel.main.AwsVaultConfigurationProperties) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "accesskey":
        case "accessKey": return target.getAccessKey();
        case "awsvaultconfiguration":
        case "awsVaultConfiguration": return target.getAwsVaultConfiguration();
        case "azurevaultconfiguration":
        case "azureVaultConfiguration": return target.getAzureVaultConfiguration();
        case "defaultcredentialsprovider":
        case "defaultCredentialsProvider": return target.isDefaultCredentialsProvider();
        case "gcpvaultconfiguration":
        case "gcpVaultConfiguration": return target.getGcpVaultConfiguration();
        case "hashicorpvaultconfiguration":
        case "hashicorpVaultConfiguration": return target.getHashicorpVaultConfiguration();
        case "profilecredentialsprovider":
        case "profileCredentialsProvider": return target.isProfileCredentialsProvider();
        case "profilename":
        case "profileName": return target.getProfileName();
        case "refreshenabled":
        case "refreshEnabled": return target.isRefreshEnabled();
        case "refreshperiod":
        case "refreshPeriod": return target.getRefreshPeriod();
        case "region": return target.getRegion();
        case "secretkey":
        case "secretKey": return target.getSecretKey();
        case "secrets": return target.getSecrets();
        case "sqsqueueurl":
        case "sqsQueueUrl": return target.getSqsQueueUrl();
        case "usesqsnotification":
        case "useSqsNotification": return target.isUseSqsNotification();
        default: return null;
        }
    }
}

