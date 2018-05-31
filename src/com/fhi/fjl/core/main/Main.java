// ==========================================================================
/**
 * Project:
 * Client :
 *
 * Note : This file may be commented so as to serve as a tutorial and/or memo.
 *
 * @author fhill (francois.hill-at-sfr.com)
 * @since 1 juin 2011
 */
// ==========================================================================

package com.fhi.fjl.core.main;


// Imports for JCL :
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//# //Imports for log4j
//# import org.apache.log4j.Logger;



/**
 * A frame for any java application
 * Simply have main class of application inherit this class, and implement mainSub().
 * This frame provides :
 * - logger
 *
 * @author fhill
 * @since 1 juin 2011
 *
 */
public class Main
{
	/**
	 * Logger obtained using apache (prev. jakarta) commons logging api (JCL)
	 * (one level of abstraction higher than log4j) :
	 * Can use a wide number of concrete implementation, including log4j
	 *
	 * org.apache.commons.logging.Log is already an interface, so this is compatible with
	 * using a dependency injection framework (spring)
	 *
	 *  In case of use of dependency injection, see if the fact that this is static is a problem.
	 */
	static protected Log logger = LogFactory.getLog("LOGGER_FJL");


	/**
	 * Logger obtained using log4j directly (one level lower than JCL) :
	 */
	//# static Logger logger_l4j = Logger.getLogger("LOGGER_L4J");


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*        */	logger.debug(com.fhi.fjl.logging.Log.OPEN);
		mainSub(args);
		/*        */	logger.debug(com.fhi.fjl.logging.Log.CLOSE);
	}


	/**
	 * To be redefined by extending classes.
	 * DOES NOT WORK
	 */
	protected static void mainSub(String[] args)
	{
	}



	public static Log getLogger()
	{	return logger;
	}

	public static void setLogger(Log logger)
	{	Main.logger = logger;
	}


}
