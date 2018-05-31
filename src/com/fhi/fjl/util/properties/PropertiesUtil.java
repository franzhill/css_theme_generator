package com.fhi.fjl.util.properties;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.fhi.fjl.io.Filename;





/**
 *
 * Note on name of this class:
 * We choose to give the name PropertiesUtil rather than just Properties, along the pattern we have
 * adopted for other utility classes, because here the name would in effect clash with java.util.Properties,
 * which is generally not the case for the other utility classes.
 *
 * @author francois hill
 * @since 4 avr. 2012
 *
 */
public class PropertiesUtil
{
	public static Properties convertResourceBundleToProperties(ResourceBundle resource)
	{
		Properties          properties   = new Properties();
		Enumeration<String> keys         = resource.getKeys();
		while (keys.hasMoreElements())
		{	String key = keys.nextElement();
			properties.put(key, resource.getString(key));
		}
		return properties;
	}


	/**
	 * Converts a file path, e.g. /com/fhi/fjl/my_props.properties,
	 * to a package path, e.g. com.fhi.fjl.my_props, so as to be readily usable by
	 * such functions as java.util.ResourceBundle.getBundle(String baseName, Locale locale).
	 * The ".properties" extension will be stripped. (it needs to be that exact extension).
	 *
	 * @param filePath
	 * @param absolute if true, add a leading "/"
	 */
	public static String convertFilePathToPackagePath(String filePath)
	{	//#String ret = filePath.replace("/", ".");
		// Strip any leading / :
		filePath = Filename.stripLeadingSlash(filePath);
		filePath =  StringUtils.removeEnd(filePath, ".properties");
		return filePath;
	}




	private PropertiesUtil() {}  //prevent instanciation

}