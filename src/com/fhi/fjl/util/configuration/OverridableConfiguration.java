package com.fhi.fjl.util.configuration;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.util.configuration.excp.OverridableConfigurationException;



/**
 * Class aiming to provide mechanisms to retrieve configuration files, and properties from a java properties file,    <br />
 * with overriding mechanisms.                                                                                        <br />
 *                                                                                                                    <br />
 * Example for properties:                                                                                            <br />
 *   File 'my_properties.properties' defines :                                                                        <br />
 *      value_1 = jim                                                                                                 <br />
 *      value_2 = tim                                                                                                 <br />
 *   File 'my_properties.OVER.properties' defines :                                                                   <br />
 *      value_2 = pam                                                                                                 <br />
 *   The resulting properties object we're looking for would have the following properties :                          <br />
 *      value_1 = jim                                                                                                 <br />
 *      value_2 = pam                                                                                                 <br />
 *   Value_2 defined in file my_properties.properties has been overridden by value_2 defined in file my_properties.OVER.properties.  <br />
 *                                                                                                                    <br />
 *   The mechanism used is based on the CompositeConfiguration of Apache Common's Configuration solution.             <br />
 *   (http://commons.apache.org/configuration)
 *
 *
 *
 * @author fhi
 * @since 2 août 2012
 *
 */
public class OverridableConfiguration
{
	// ==========================================================================
	// INTERFACE (public)
	// ==========================================================================


	/**
	 * Return a properties object (or equivalent) loaded from a properties file, with an overriding mechanism.
	 * Example of use : file 'my_properties.PROD.properties' should override file 'my_properties.properties'
	 *
	 * @param fileName as used by org.apache.commons.configuration.PropertiesConfiguration(fileName). Full file path is
	 *        probably a good option.
	 * @param overridingMidfix Part between the file base and the file suffix (aka postfix) -all parts being separated by
	 *                         a dot (.)- indicating the overriding properties file, i.e. the properties file with this midfix
	 *                         will override the properties file without the midfix.
	 *                         The actual mechanism of loading the files and defining the overriding is delegated to
	 *                         org.apache.commons.configuration.PropertiesConfiguration.
	 *                         Ex : properties defined in
	 *                                my_properties.PROD.properties
	 *                              will override properties defined in
	 *                                my_properties.properties
	 *                              provided that passed 'fileName' and 'overridingMidfix' are resp. : 'my_properties.properties
	 *                              and 'PROD'.
	 *                              The overriding properties file is optional.
	 * @return the resulting Properties file, or an equivalent object (see also org.apache.commons.configuration.PropertiesConfiguration)
	 * @throws OverridableConfigurationException If properties could not be read from the given file.
	 */
	public static CompositeConfiguration loadConfiguration (String fileName, String overridingMidfix)
	{
		/*        */	String err_msg = "Error loading the properties for the given property file and midfix = {"
		/*        */	               + fileName + "," + overridingMidfix + "}";
		/*        */	try
		/*        */	{

		// 1. Compute the overriding file's name : by inserting the midfix :
		String fileName_overriding = computeOverridingFilename(fileName, overridingMidfix);

		// 2. Load
		CompositeConfiguration config            = new CompositeConfiguration();

		// Careful with the order for composite : commons.configuration searches for the property in the added files,
		// in THE ORDER in which they are added. -> overriding files first !

		// The following both throw ConfigurationException:
		// We'll silence the fist because an overriding properties file is only optional
		// but we'll let the second one go through.

		/*        */	try
		/*        */	{

		                       config            . addConfiguration(new PropertiesConfiguration(fileName_overriding));

		/*        */	}
		/*        */	catch (ConfigurationException e)
		/*        */	{	String msg = "Could not load properties file : {" + fileName_overriding + "}. This MAY be perfectly all right.";
		/*        */		if (debugOn) slogger.debug(msg);
		/*        */	}

		                       config            . addConfiguration(new PropertiesConfiguration(fileName           ));

		return config;

		/*        */	}
		/*        */	catch (ConfigurationException e)
		/*        */	{
		/*        */		if (debugOn) slogger.debug(err_msg);
		/*        */		throw new OverridableConfigurationException(err_msg, e);
		/*        */	}
		/*        */	catch (Exception e)
		/*        */	{
		/*        */		if (debugOn) slogger.debug(err_msg);
		/*        */		throw new OverridableConfigurationException(err_msg, e);
		/*        */	}
	}


	/**
	 * Convenience.
	 * Same as loadConfiguration(...), only returning a java.util.Properties instead.
	 */
	public static Properties loadProperties (String fileName, String overridingMidfix)
	{
		// Convert from a Configuration to a java.util.Properties :
		return ConfigurationConverter.getProperties(loadConfiguration (fileName, overridingMidfix));
	}


	/**
	 * Return a resource with an overriding mechanism similar to that of loadConfiguration()
	 * (with the difference that the overriding is 'all or nothing' : overriding file replaces original file)                     <br />
	 * See documentation for further details.                                                                                     <br />
	 *                                                                                                                            <br />
	 * This function relies on function Thread.currentThread().getContextClassLoader().getResource()
	 * so localization is also possible. (see that function's doc).                                                               <br />
	 * However, the midfix will be inserted in the file name before call to that function, so localized resources should be named along
	 * the following pattern :                                                                                                    <br />
	 *   my_file.conf            // base file, will be returned if none of the below are present                                                                <br />
	 *   my_file_fr.conf         // localized base file, will be returned if if the current locale is fr and none of the below are present                      <br />
	 *   my_file_en.conf         // localized base file, will be returned if if the current locale is en and none of the below are present                      <br />
	 *   my_file.MIDFIX.conf     // this file will be returned instead of the above if passed MIDFIX as overridingMidfix (and none of the below are present)    <br />
	 *   my_file.MIDFIX_fr.conf  // this file will be returned instead of the above if the current locale is fr                                                 <br />
	 *   my_file.MIDFIX_en.conf  // this file will be returned instead of the above if the current locale is en                                                 <br />
	 *
	 *
	 *
	 *
	 * @param fileName
	 * @param overridingMidfix
	 * @return URL of resource or null if resource could not be found
	 */
	public static URL getResource (String fileName, String overridingMidfix)
	{
		// 1. Compute the overriding file's name : by inserting the midfix :
		String fileName_overriding = computeOverridingFilename(fileName, overridingMidfix);

		// 2. Load
		URL    url              = Thread.currentThread().getContextClassLoader().getResource(fileName_overriding);

		// If overriding resource is not found, try with original resource :
		if (url == null)
		{
			url                  = Thread.currentThread().getContextClassLoader().getResource(fileName);
		}

		// If still not found, just return null anyway.
		return url;
	}


	/**
	 * Given a file name, inserts the given midfix in it, just before the file extension.
	 *
	 * Example :                                                                          <br />
	 *   computeOverridingFilename("my_file"                 , "DEV" );                   <br />
	 *   computeOverridingFilename("my_file.txt"             , "PROD");                   <br />
	 *   computeOverridingFilename("my_file.new.txt"         , "INT" );                   <br />
	 *   computeOverridingFilename("/path/to/my_file.new.txt", "INT" ));                  <br />
	 * returns:                                                                           <br />
	 *   DEV.my_file                                                                      <br />
	 *   my_file.PROD.txt                                                                 <br />
	 *   my_file.new.INT.txt                                                              <br />
	 *   /path/to/my_file.new.INT.txt
	 *
	 * @param fileName
	 * @param overridingMidfix
	 * @return
	 */
	public static String computeOverridingFilename(String fileName, String overridingMidfix)
	{
		LinkedList<String> fileNameSplits = new LinkedList<String>(Arrays.asList(fileName.split("\\.")));
		String             pop            = fileNameSplits.removeLast();
		                   fileNameSplits . addLast(overridingMidfix);
		                   fileNameSplits . addLast(pop);
		String             fileName_overriding = org.apache.commons.lang.StringUtils.join(fileNameSplits, ".");
		return fileName_overriding;
	}











	// ==========================================================================
	// LOGGING
	// ==========================================================================

	private static Log     slogger= LogFactory.getLog("com.fhi.fjl.properties");


	/**
	 * Use if in need to use a different logger than the default one.
	 * Default logger is log4j logger of name "com.fhi.fjl.properties".
	 * @return
	 * @used_by IoC (e.g. spring) if in need of slogger other than the default one specified above
	 */
	public static void setSlogger(Log logger)
	{	slogger = logger;
	}


	/**
	 * Activate or deactivate logs of debug level for this class
	 * To deactivate debugging for this class, set to false. This is final i.e. in that case, there will be no logging of level debug.
	 * To activate   debugging for this class, set to true. The decision of logging of level debug will be left to higher levels.
	 */
	protected static boolean debugOn     = true
	                                              && slogger.isDebugEnabled();

}
