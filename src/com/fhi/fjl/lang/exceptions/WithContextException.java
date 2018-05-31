package com.fhi.fjl.lang.exceptions;

/**
 * Exception defining a context object to store info that can subsequently be
 * used to create highly informative exception messages.                             <br />
 *                                                                                   <br />
 * Example of use:                                                                   <br />
 * <pre>
 * MyMenuException extends WithContextException
 * {	...
 * 	//Override
 * 	public String getMessage()
 * 	{
 * 		String    menu_name = null;
 * 		ILoggable user      = null;
 *
 * 		if (this.context instanceof HashMap)
 * 		{	//HashMap   h         = (HashMap) o;
 * 			menu_name = (String)    ((HashMap) this.onObject).get("menu_name"); // may be null if key does not exist
 * 			user      = (ILoggable) ((HashMap) this.onObject).get("user")     ; // may be null if key does not exist
 * 		}
 *
 * 		String err_msg_final = "Failed to load menu: "                   +
 * 		                        (menu_name == null ? "null" : menu_name) +
 * 		                       " for user: "                              +
 * 		                        (user      == null ? "null" : user)      +
 * 		                       "."
 * 		                       " Further information : " + super.getMessage(); // will print the parent exception message
 *
 * 		return err_msg_final;
 * 	}
 * }
 * </pre>
 * Call:
 * <pre>
 * 	try
 * 	{	// do this or that
 * 	}
 * 	catch (Exception e)
 * 	{	// Context info :
 * 		HashMap<String, Object> context_info = new HashMap<String, Object>();
 * 		                        context_info.put("menu_name", menu_name);
 * 		                        context_info.put("user"     , user)     ;
 *
 * 		throw new MyMenuException("while doing this or that...", null, context_info);
 * 	}
 * </pre>
 * Output:
 * <pre>
 * 	Failed to load menu: side_bar_menu for user: Jeremy Osborne. Further information : while doing this or that...
 * </pre>
 *
 *
 * @author fhill
 * @since 2012.11
 *
 */
@SuppressWarnings("serial")
public class WithContextException extends Exception
{
	/**
	 * Context object
	 *
	 * May be a map of several objects.
	 * Can be used ad libitum to create customized getMessage() functions (in extending classes). See class doc.
	 */
	protected Object context;

	public WithContextException(String err_msg)
	{	super(err_msg);
	}

	/**
	 *
	 * @param err_msg
	 * @param cause       Accepts null if no cause (just like parent, Exception)
	 */
	public WithContextException(String err_msg, Throwable cause)
	{	super(err_msg, cause);
	}

	/**
	 *
	 * @param err_msg
	 * @param cause      Accepts null if no cause (just like parent, Exception)
	 * @param subject    Context object (possibly a map of objects) processed when exception arose
	 */
	public WithContextException(String err_msg, Throwable cause, Object context)
	{	this(err_msg, cause);
		this.context = context;
	}


	/**
	 * Return the context. Useful to create custom getMessage() functions (in extending classes)
	 * @return
	 */
	public Object getContext()
	{	return this.context;
	}
}
