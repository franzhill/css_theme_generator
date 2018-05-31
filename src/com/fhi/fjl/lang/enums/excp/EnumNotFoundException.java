package com.fhi.fjl.lang.enums.excp;


@SuppressWarnings("serial")
public class EnumNotFoundException extends Exception
{
	private final String errMessage = "A 'FJL' error occurred : the enum could not be found";

	public EnumNotFoundException(String msg)
	{	super(msg);
	}


	public EnumNotFoundException(String msg, Exception e)
	{	super(msg, e);
	}

	@Override
	public String getMessage()
	{	return errMessage + ". Further info: " +  super.getMessage();
}

}
