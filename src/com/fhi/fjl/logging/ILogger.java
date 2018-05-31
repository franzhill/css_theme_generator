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
 * Logging interface.
 *
 * This logging interface allows us to define extra functions, compared with the
 * 'standard' logging interfaces provided by apache.commons.logging or java.util.logging.
 *
 * @author fhill
 * @since 8 févr. 2011
 *
 */
public interface ILogger
{
	public void fatal(Object message);
	public void fatal(Object message, Throwable t);

	public void error(Object message);
	public void error(Object message, Throwable t);

	public void warn(Object message);
	public void warn(Object message, Throwable t);

	public void info(Object message);
	public void info(Object message, Throwable t);

	public void debug(Object message);
	public void debug(Object message, Throwable t);

	/**
	 * Produce an 'opening' debug message.
	 * Can be used to make logs visually more readable : use at start of function, and debugClose at end.
	 */
	public void debugOpen();

	/**
	 * Produce an 'opening' debug message.
	 * Can be used to make logs visually more readable : use at start of function, and debugClose at end.
	 */
	public void debugClose();

	public void trace(Object message);
	public void trace(Object message, Throwable t);
}
