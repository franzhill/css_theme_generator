package com.fhi.fjl.web.css_generator.painters;

import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.core.BaseSupport;
import com.fhi.fjl.web.css_generator.rules.CssRule;



/**
 * Paint an element with a specified setting (i.e. modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public abstract class Painter extends BaseSupport implements IPainter
{
	// ==========================================================================
	// LOGGING
	// ==========================================================================

	// Redefine preset fields :
	// See base parent class
	{
		logger  = LogFactory.getLog("com.fhi.fjl.web.css_generator.painters");
		debugOn = true
		                       && logger.isDebugEnabled();
	}


	// ==========================================================================
	// BUSINESS
	// ==========================================================================

	public CssRule apply(CssRule rule)
	{
		return apply(rule, null);
	}


	@Override
	public Painter clone()
	{	/*        */	try
		/*        */	{

		return (Painter) super.clone();

		/*        */	}
		/*        */	catch (CloneNotSupportedException e1)
		/*        */	{	throw new RuntimeException("This should not happen. Check code.", e1);
		/*        */	}
	}


	public PainterStack add(IPainter p)
	{	return new PainterStack().add(this).add(p);
	}

	
	
	@Override
	public String toString()
	{
		return "Painter = [" + this.getClass().getSimpleName() + "]";
	}



	
}
