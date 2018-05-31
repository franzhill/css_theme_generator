package com.fhi.fjl.lang.types;


public class Types
{

	/**
	 * Convert a String representing a boolean to a boolean.
	 * Accepted strings are (case-insensitive):
	 *  "true"
	 *  "false"
	 *
	 * @param strBool the string to convert to boolean
	 * @return the boolean value represented by the passed string
	 * @throws IllegalArgumentException if passed string is none of the accepted values
	 */
	public static boolean toBoolean(String strBool)
	{
			if ("true".equalsIgnoreCase(strBool))
			{	return true;
			}
			else if ("false".equalsIgnoreCase(strBool))
			{	return false;
			}
			else
			{	String err_msg = "while trying to convert String {" + strBool +"} to boolean, when only accepted values are :"
				               + "true, false";
				throw new IllegalArgumentException(err_msg);
			}
	}


	/**
	 * Convert a String representing a boolean to a boolean.
	 * Accepted strings are (case-insensitive):
	 *  "1"   (for true)
	 *  "0"   (for false)
	 *
	 * @param strBool the string to convert to boolean
	 * @return the boolean value represented by the passed string
	 * @throws IllegalArgumentException if passed string is none of the accepted values
	 */
	public static boolean toBooleanNum(String strBool)
	{
			if ("1".equalsIgnoreCase(strBool))
			{	return true;
			}
			else if ("0".equalsIgnoreCase(strBool))
			{	return false;
			}
			else
			{	String err_msg = "while trying to convert String {" + strBool +"} to boolean, when only accepted values are :"
				               + "true, false";
				throw new IllegalArgumentException(err_msg);
			}

	}





	private Types() {} // prevent instanciation
}
