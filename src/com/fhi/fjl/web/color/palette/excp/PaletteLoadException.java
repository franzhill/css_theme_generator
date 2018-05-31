package com.fhi.fjl.web.color.palette.excp;

import com.fhi.fjl.lang.exceptions.WithContextException;

@SuppressWarnings("serial")
public class PaletteLoadException extends WithContextException
{

	public PaletteLoadException(String err_msg)
	{	super(err_msg);
	}

	public PaletteLoadException(String err_msg, Throwable cause)
	{	super(err_msg, cause);
	}

	public PaletteLoadException(String err_msg, Throwable cause, Object context)
	{	this(err_msg, cause);
		this.context = context;
	}

}
