package com.fhi.fjl.lang.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author fhill
 * @since 22 juil. 2011
 *
 */
public class Exceptions
{
	public static String getStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString() ;
	}



	/**
	 * In a chained exception, return the first cause exception of specified type.             <br />
	 *                                                                                         <br />
	 * If the passed exception is of the specified type, then this exception is returned
	 * (the causes are not explored).                                                          <br />
	 *                                                                                         <br />
	 * Example:                                                                                <br />
	 * <pre>{@code
	 * 	SQLException cause = (SQLException) Exceptions.getFirstCauseExceptionOfType(e, SQLException.class, true);
	 * }</pre>
	 *
	 *
	 * @param e
	 * @param causeType
	 * @param exact Should the cause looked for be of the exact specified type (i.e. can't be a subclass) or
	 *              not (it can be a subclass) ?   <br />
	 * @return null if no cause of specified type is found.
	 */
	public static Throwable getFirstCauseExceptionOfType(Exception e, Class<?> causeType, boolean exact)
	{
		Throwable cause = e;
		while (cause != null)
		{	boolean test = exact ? causeType.getCanonicalName().equals(cause.getClass().getCanonicalName())
			                     : causeType.isInstance(cause);

			if (test)
			{	return cause;
			}
			else
			{	cause = cause.getCause();
			}
		}
		return null;
	}




	private Exceptions() {} // prevent instanciation

}
