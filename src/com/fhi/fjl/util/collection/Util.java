package com.fhi.fjl.util.collection;

//Imports for log4j
import java.util.Collection;

import org.apache.log4j.Logger;

public class Util
{
	static Logger logger_l4j = Logger.getLogger("com.fhi.fjl");

	/**
	 * This class is intended as static -> prevent it from being instantiated with private constructor
	 */
	private Util()
	{
	}



	public static String print(@SuppressWarnings("rawtypes") Collection c)
	{	return c.toString();
	}


}
