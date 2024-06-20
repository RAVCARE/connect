/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.server.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration2.PropertiesConfiguration;

import com.mirth.commons.encryption.Digester;
import com.mirth.commons.encryption.Encryptor;
import com.mirth.connect.client.core.ControllerException;
import com.mirth.connect.model.ChannelDependency;
import com.mirth.connect.model.ChannelMetadata;
import com.mirth.connect.model.ChannelTag;
import com.mirth.connect.model.DatabaseSettings;
import com.mirth.connect.model.DriverInfo;
import com.mirth.connect.model.EncryptionSettings;
import com.mirth.connect.model.PasswordRequirements;
import com.mirth.connect.model.PublicServerSettings;
import com.mirth.connect.model.ServerConfiguration;
import com.mirth.connect.model.ServerSettings;
import com.mirth.connect.model.UpdateSettings;
import com.mirth.connect.util.ConfigurationProperty;
import com.mirth.connect.util.ConnectionTestResponse;

/**
 * The ConfigurationController provides access to the Mirth configuration.
 * 
 */
public abstract class ConfigurationController extends Controller {
    // status codes

    public static final int STATUS_OK = 0;
    public static final int STATUS_UNAVAILABLE = 1;
    public static final int STATUS_ENGINE_STARTING = 2;
    public static final int STATUS_INITIAL_DEPLOY = 3;
    public static Class<?> HTTP_DISPATCHER_PROPERTIES_CLASS;

    public static ConfigurationController getInstance() {
        return ControllerFactory.getFactory().createConfigurationController();
    }

    /**
     * Initializes several items relates to security. Specifically:
     * 
     * <ol>
     * <li>Instantiates the default encryptor and digester</li>
     * <li>Loads or generates the default keystore and certificate</li>
     * <li>Loads or generates the default truststore</li>
     * </ol>
     * 
     */
    public abstract void initializeSecuritySettings();

    /**
     * Initializes the DatabaseSettings from the properties configuration.
     */
    public abstract void initializeDatabaseSettings();

    /**
     * Migrates the encryption key from the database to a new JCEKS keystore. This should only be
     * run once during the migration from pre-2.2 to 2.2.
     */
    public abstract void migrateKeystore();

    /**
     * Copies the current server configuration into the specified object.
     */
    public abstract void updatePropertiesConfiguration(PropertiesConfiguration config);

    /**
     * Returns the default encryptor.
     * 
     * @return the default encryptor
     */
    public abstract Encryptor getEncryptor();

    /**
     * Returns the default digester.
     * 
     * @return the default digester
     */
    public abstract Digester getDigester();

    /**
     * Returns the database type (ex. derby)
     * 
     * @return the database type
     */
    public abstract String getDatabaseType();

    /**
     * Returns the server's unique ID
     * 
     * @return the server's unique ID
     */
    public abstract String getServerId();

    public abstract String getServerName();

    public abstract String getServerTimezone(Locale locale);

    public abstract Calendar getServerTime();

    /**
     * Returns all of the charset encodings available on the server.
     * 
     * @return a list of charset encoding names
     * @throws ControllerException
     */
    public abstract List<String> getAvailableCharsetEncodings() throws ControllerException;

    /**
     * Returns the base directory for the server.
     * 
     * @return the base directory for the server.
     */
    public abstract String getBaseDir();

    /**
     * Returns the conf directory for the server. This is where configuration files and database
     * mapping scripts are stored.
     * 
     * @return the conf directory for the server.
     */
    public abstract String getConfigurationDir();

    /**
     * Returns the app data directory for the server. This is where files generated by the server
     * are stored.
     * 
     * @return the app data directory for the server.
     */
    public abstract String getApplicationDataDir();

    /**
     * Returns all server settings.
     * 
     * @return server settings
     * @throws ControllerException
     */
    public abstract ServerSettings getServerSettings() throws ControllerException;

    /**
     * Returns all encryption settings.
     * 
     * @return encryption settings
     * @throws ControllerException
     */
    public abstract EncryptionSettings getEncryptionSettings() throws ControllerException;

    /**
     * Returns all database settings.
     * 
     * @return encryption settings
     * @throws ControllerException
     */
    public abstract DatabaseSettings getDatabaseSettings() throws ControllerException;

    /**
     * Sets all server settings.
     * 
     * @param server
     *            settings
     * @throws ControllerException
     */
    public abstract void setServerSettings(ServerSettings settings) throws ControllerException;

    /**
     * Returns all public server settings.
     * 
     * @return public server settings
     * @throws ControllerException
     */
    public abstract PublicServerSettings getPublicServerSettings() throws ControllerException;
    
    /**
     * Returns all update settings.
     * 
     * @return update settings
     * @throws ControllerException
     */
    public abstract UpdateSettings getUpdateSettings() throws ControllerException;

    /**
     * Sets all update settings.
     * 
     * @param update
     *            settings
     * @throws ControllerException
     */
    public abstract void setUpdateSettings(UpdateSettings settings) throws ControllerException;

    /**
     * Generates a new GUID.
     * 
     * @return a new GUID
     */
    public abstract String generateGuid();

    /**
     * Returns the database driver list used for the Database Reader/Writer connectors.
     * 
     * @return a list of database driver metadata
     * @throws ControllerException
     *             if the list could not be retrieved or parsed
     */
    public abstract List<DriverInfo> getDatabaseDrivers() throws ControllerException;

    /**
     * Updates the list of database drivers used for the Database Reader/Writer connectors.
     * 
     * @param drivers
     *            The list of database drivers to update.
     * @throws ControllerException
     *             if the list could not be updated or parsed
     */
    public abstract void setDatabaseDrivers(List<DriverInfo> drivers) throws ControllerException;

    /**
     * Returns the server version (ex. 1.8.2).
     * 
     * @return the server version
     */
    public abstract String getServerVersion();

    /**
     * Returns the server build date.
     * 
     * @return the server build date.
     */
    public abstract String getBuildDate();

    /**
     * The maximum amount of time that a session can be idle before it is invalidated.
     */
    public abstract int getMaxInactiveSessionInterval();

    public abstract String[] getHttpsClientProtocols();

    public abstract String[] getHttpsServerProtocols();

    public abstract String[] getHttpsCipherSuites();

    public abstract boolean isStartupDeploy();

    public abstract int getStatsUpdateInterval();

    public abstract Integer getRhinoLanguageVersion();

    public abstract int getStartupLockSleep();

    /**
     * Returns the server configuration, which contains:
     * <ul>
     * <li>Channels</li>
     * <li>Users</li>
     * <li>Alerts</li>
     * <li>Code templates</li>
     * <li>Server properties</li>
     * <li>Scripts</li>
     * </ul>
     * 
     * @return the server configuration
     * @throws ControllerException
     */
    public abstract ServerConfiguration getServerConfiguration() throws ControllerException;

    /**
     * Restores the server configuration.
     * 
     * @param serverConfiguration
     *            the server configuration to restore
     * @throws ControllerException
     *             if the server configuration could not be restored
     * @throws InterruptedException
     */
    public abstract void setServerConfiguration(ServerConfiguration serverConfiguration, boolean deploy, boolean overwriteConfigMap) throws ControllerException;

    /**
     * Returns the password requirements specified in the mirth.properties file (ex. min length).
     * 
     * @return the password requriements
     */
    public abstract PasswordRequirements getPasswordRequirements();

    public abstract boolean isBypasswordEnabled();

    public abstract boolean checkBypassword(String password);

    // status

    /**
     * Returns the current status of the server. See status constants in ConfigurationController.
     */
    public abstract int getStatus();

    public abstract int getStatus(boolean checkDatabase);

    /**
     * Sets the current status of the server. See status constants in ConfigurationController.
     */
    public abstract void setStatus(int status);

    /**
     * Returns the configuration map
     */
    public abstract Map<String, String> getConfigurationMap();

    /**
     * Returns the configuration map properties containing the values and comments for each key
     */
    public abstract Map<String, ConfigurationProperty> getConfigurationProperties() throws ControllerException;

    /**
     * Sets the configuration map properties
     */
    public abstract void setConfigurationProperties(Map<String, ConfigurationProperty> map, boolean persist) throws ControllerException;

    // properties
    public Properties getPropertiesForGroup(String group) {
        return getPropertiesForGroup(group, null);
    }

    public abstract Properties getPropertiesForGroup(String group, Set<String> propertyKeys);

    public abstract void removePropertiesForGroup(String group);

    public abstract String getProperty(String group, String name);

    public abstract void saveProperty(String group, String name, String property);

    public abstract void removeProperty(String group, String name);

    public abstract String getResources();

    public abstract void setResources(String resources);

    public abstract Set<ChannelDependency> getChannelDependencies();

    public abstract void setChannelDependencies(Set<ChannelDependency> dependencies);

    public abstract Map<String, ChannelMetadata> getChannelMetadata();

    public abstract void setChannelMetadata(Map<String, ChannelMetadata> channelMetadata);

    public abstract ConnectionTestResponse sendTestEmail(Properties properties) throws Exception;

    public abstract void setChannelTags(Set<ChannelTag> tags);

    public abstract Set<ChannelTag> getChannelTags();
}
