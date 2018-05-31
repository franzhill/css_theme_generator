package com.fhi.fjl.util.configuration.excp;

@SuppressWarnings("serial")
public class OverridableConfigurationException extends RuntimeException
{
	public OverridableConfigurationException(String msg)
	{	super(msg);
	}


	public OverridableConfigurationException(String msg, Exception e)
	{	super(msg, e);
	}
}
