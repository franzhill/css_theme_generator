// ==========================================================================
/**
 * Project: FHI APP TEMPLATE
 * Client :
 *
 * Note : This file may be commented so as to serve as a tutorial and/or memo.
 *
 *
 * @author    fhi (francois.hill-at-sfr.com)
 * @since     2011.05
 * @copyright SFR-2011
 */
// ==========================================================================

package com.fhi.fjl.logging.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fhi.fjl.logging.ILogger;


/**
 * Logger based on log4j logger (wraps it).
 *
 * NOTE :
 *   The problem with using LoggerLog4j.java wrapping Log4j's Log is that it adds a level
 *   of redirection and messes up  Log4j's Log's messages (class and method producing the log message)
 *   This problem is solved using log(String callerFQCN, Priority level, Object message, Throwable t)
 *   "intended to be invoked by wrapper classes".
 *
 * @author francois.hill-at-sfr.com
 * @since 8 févr. 2011
 *
 */
public class LoggerLog4jWrap implements ILogger
{
	public final String OPEN="<<";
	public final String CLOSE=">>";


	/**
	 * The encapsulated log4j logger
	 */
	protected Logger logger;

	/**
	 * The encapsulated log4j logger's name
	 */
	protected String loggerName;


	// --------------------------------------------------------------------------
	// Construction
	// --------------------------------------------------------------------------

	/**
	 * Constructor
	 * @param log4j_logger_name The name of the log4j logger to use (aka the "category").
	 *                          See log4j config file.
	 */
	public LoggerLog4jWrap(String log4j_logger_name)
	{	this.construct(log4j_logger_name);
	}


	public LoggerLog4jWrap()
	{
	}

	public void construct(String log4j_logger_name)
	{	this.loggerName = log4j_logger_name;
		//this.logger     = LogFactory.getLog(this.loggerName);
		this.logger = Logger.getLogger(log4j_logger_name);
	}


	// --------------------------------------------------------------------------
	// "Wire" the encapsulated logger's functions :
	// --------------------------------------------------------------------------

	public void fatal(Object message)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.FATAL,
		                message,
		                null);
	}
	public void fatal(Object message, Throwable t)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.FATAL,
		                message,
		                t);
	}


	public void error(Object message)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.ERROR,
		                message,
		                null);
	}
	public void error(Object message, Throwable t)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.ERROR,
		                message,
		                t);
	}


	public void warn(Object message)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.WARN,
		                message,
		                null);
	}
	public void warn(Object message, Throwable t)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.WARN,
		                message,
		                t);
	}


	public void info(Object message)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.INFO,
		                message,
		                null);
	}
	public void info(Object message, Throwable t)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.INFO,
		                message,
		                t);
	}


	public void debug(Object message)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.DEBUG,
		                message,
		                null);
	}
	public void debug(Object message, Throwable t)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.DEBUG,
		                message,
		                t);
	}


	public void trace(Object message)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.TRACE,
		                message,
		                null);
	}
	public void trace(Object message, Throwable t)
	{	this.logger.log(LoggerLog4jWrap.class.getName(),
		                Level.TRACE,
		                message,
		                t);
	}


	public void debugOpen()
	{	this.debug(this.OPEN);
	}

	public void debugClose()
	{	this.debug(this.CLOSE);
	}


	// --------------------------------------------------------------------------
	// Getters/Setters
	// --------------------------------------------------------------------------

	public String getLoggerName()
	{	return loggerName;
	}


	public void setLoggerName(String logger_name)
	{	//#this.loggerName = logger_name;
		this.construct(logger_name);	// TEMP : because at this point in time spring initialisation is done through property and not through constructor
	}


}
