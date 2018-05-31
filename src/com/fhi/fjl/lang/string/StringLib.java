// ==========================================================================
/**
 * Project:
 * Client :
 *
 * Note : This file may be commented so as to serve as a tutorial and/or memo.
 *
 * @author fhill (francois.hill-at-sfr.com)
 * @since 15 juin 2011
 */
// ==========================================================================

package com.fhi.fjl.lang.string;

import org.apache.commons.logging.Log;



/**
 *
 * @author fhill
 * @since 15 juin 2011
 *
 */
public class StringLib
{
	/**
	 * If null, attempts to log through this logger will just silently fail.
	 */
	protected static Log logger;


	/**
	 * This class is intended as static -> prevent it from being instantiated with private constructor
	 */
	private StringLib()
	{
	}


	/**
	 * Call prior to using StringLib if initialisations need to be done.
	 * Initialisations include :
	 * -setting a logger (used by functions of StringLib)
	 *
	 * If not called, StringLib will function with default settings.
	 *
	 * @param logger
	 */
	public static void init(Log logger)
	{	setLogger(logger);
	}


	/**
	 * Port of PHP function str_repeat
	 * Courtesy of http://www.finbr.com/q/str_repeat%28%29_%28PHP%29_in_java
	 * @param s
	 * @param n
	 * @return
	 */
	public static String repeat(String s, int n)
	{
		if (s == null)
		{	return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
		{	sb.append(s);
		}
		return sb.toString();
	}

























	public static Log getLogger()
	{	return logger;
	}

	public static void setLogger(Log logger)
	{
		if (logger == null)
		{
		}
		else
		{	StringLib.logger = logger;
		}
	}



}
