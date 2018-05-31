package com.fhi.fjl.log;

import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author fhi
 * @since 20 juin 2012
 *
 */
public class Log4jConfigurer
{
	private static Log     slogger= LogFactory.getLog("com.fhi.fjl.log");


	private static boolean isLog4jAlreadyConfigured = false;

	/**
	 * Configures Log4j by loading its properties from overridable .properties files.                      <br />
	 *                                                                                                      <br />
	 *                                                                                                      <br />
	 * It is is assumed the log4j configuration files are at the root of the project structure :            <br />
	 *   /log4j.properties                                                                                  <br />
	 *   /log4j.{overridePrefix}.properties                                                                 <br />
	 *                                                                                                      <br />
	 * Overriding properties is achieved using Apache Commons Configuration's 'CompositeConfiguration'.     <br />
	 * Default properties are defined in                                                                    <br />
	 *   /log4j.properties                                                                                  <br />
	 * and can be overridden individually in file                                                           <br />
	 *   /log4j.{overridePrefix}.properties                                                                 <br />
	 *                                                                                                      <br />
	 * overridePrefix can be used, for example, to distinguish between environment-specific configurations. <br />
	 * Example : /log4j.PROD.properties would override some settings from /log4j.properties . For example,  <br />
	 * the logging level of all loggers could be overridden to INFO instead of DEBUG.                       <br />
	 *                                                                                                      <br />
	 *
	 * @param overrideSufix Part of the configuration file name defining the overriding configuration file.
	 * @return
	 */
	public static void configureLog4j(String overrideSuffix)
	{	/*        */	log("<< configureLog4j()");

		if (! isLog4jAlreadyConfigured)
		{
			// 1. Compute log4j properties
			// -----------------------------
			String                 configFilePath         = "/log4j.properties";
			String                 configFilePathOverride = "log4j." + overrideSuffix + ".properties";
			CompositeConfiguration config                 = new CompositeConfiguration();

			// Careful with the order for composite : commons.configuration searches for the property in the added files,
			// in THE ORDER in which they are added.

			// 1.1 Try to load the overriding config file :
			//     If results in an error, overriding file is probably not present, which is fine -> silence exception

			/*        */	slogger.debug("Loading config file = " + configFilePath);
			/*        */	try
			/*        */	{
			/*        */		slogger.debug("Loading config file = " + configFilePathOverride);

			                       config            . addConfiguration(new PropertiesConfiguration(configFilePathOverride));

			/*        */	}
			/*        */	catch (ConfigurationException e)
			/*        */	{	String msg = "Could not find overriding log4j configuration file : " + configFilePathOverride + ". This MAY or MAY not be a bug.";
			/*        */		slogger.info(msg);
			/*        */	}

			/*        */	try
			/*        */	{

			                       config         . addConfiguration(new PropertiesConfiguration(configFilePath));

			// 2. Load computed properties into log4j
			// ---------------------------------------
			// Convert the configuration computed here above into a Properties object :
			Properties log4j_props = ConfigurationConverter.getProperties(config);

			// This configures "the whole" of log4j
			// (i.e. no need to reconfigure every time we 'extract' a 'category' (=slogger name)
			PropertyConfigurator.configure(log4j_props);

			/*        */	}
			/*        */	// Also silence if log4j default config file is not found.
			/*        */	catch (ConfigurationException e)
			/*        */	{	String msg = "Could not find default configuration log4j file : " + configFilePath + ". Please check your setup. " +
			/*        */		             "This problem will be silenced and it will be up to org.apache.commons.logging.LogFactory.getLog() to deal with it.";
			/*        */		slogger.warn(msg);
			/*        */	}

			isLog4jAlreadyConfigured = true;
		}
		/*        */	log(">> configureLog4j()");
	}


	private static void  log(String msg)
	{	System.out.println(msg);
	}

}
