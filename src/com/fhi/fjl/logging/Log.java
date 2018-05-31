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


package com.fhi.fjl.logging;


/**
 * Contains additional (non essential) logging functionalities
 * Meant to be used WITH (and not INSTEAD OF) a 'standard' logging solution
 * (org.apache.commons.logging, java.util.logging ...)
 *
 * @author fhill
 * @since 8 juin 2011
 *
 */
public interface Log
{

	/**
	 * Use to render a visual 'start' mark in logs.
	 * Can be used at start of function for example, to visually single out logs for that particular function.
	 *
	 * Ex :
	 *   public void function myFunction()
	 *   {   this.logger.debug(com.fhi.fjl.logging.Log.OPEN);
	 *       ...
	 *       this.logger.debug(com.fhi.fjl.logging.Log.CLOSE);
	 *   }
	 */
	static public final String OPEN  = "<<";

	/**
	 * Use to render a visual 'close' mark in logs.
	 * Can be used at start of function for example, to visually single out logs for that particular function.
	 * Ex :
	 *   See OPEN
	 */
	static public final String CLOSE = ">>";


}
