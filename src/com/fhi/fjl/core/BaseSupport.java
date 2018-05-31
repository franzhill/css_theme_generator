package com.fhi.fjl.core;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base support class offering cross-cutting concerns support. (logging ...)
 *
 *
 * @author fhi
 * @since  2012.11.21 - 28/07/2011
 */
public class BaseSupport
{
	// ==========================================================================
	// LOGGING
	// ==========================================================================

	protected        Log     logger = LogFactory.getLog("com.fhi.fjl");

	/**
	 * Static logger. Use in constructor methods when logger is possibly not set yet.
	 * Once one logger of one instance of this class has been set, this static logger
	 * becomes available (see setLogger).
	 * Since despite this, requests for logging can still be made before any sort
	 * initialisation of both loggers, the following attribute initialisation insures
	 * that the call will fail silently (and not throw a NPE)
	 */
	protected static Log     slogger= LogFactory.getLog("com.fhi.fjl");


	/**
	 * Set this object's logger.
	 *
	 * @used_by IoC (e.g. spring) if in need of logger other than the default one specified above
	 */
	public void setLogger(Log logger)
	{	this.logger = logger;
		slogger     = logger;

		// isDebugEnabled() is a check performed by the logger at the highest level : if debug is not enabled, this saves the overhead of computing the messages.
		debugOn     = debugOn && logger.isDebugEnabled();
	}


	/**
	 * Activate or deactivate logs of debug level for this class
	 * To deactivate debugging for this class, set to false. This is final i.e. in that case, there will be no logging of level debug.
	 * To activate   debugging for this class, set to true. The decision of logging of level debug will be left to higher levels.
	 */
	protected static boolean debugOn     = true
	                                              && slogger.isDebugEnabled();



}
