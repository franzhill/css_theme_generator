package com.fhi.fjl.web.css_generator.themes;



/**
 *
 * @author francois hill
 * @since 1 déc. 2012
 */
public class ThemeFactory
{
	public ITheme getTheme(Class<? extends Theme> clazz)
	{
		Theme ret;

		/*        */	String err_msg = "Should not happen. Please review code.";
		/*        */	try
		/*        */	{

		ret = clazz.newInstance();

		/*        */	}
		/*        */	catch (InstantiationException e)
		/*        */	{	throw new RuntimeException(err_msg, e);
		/*        */	}
		/*        */	catch (IllegalAccessException e)
		/*        */	{	throw new RuntimeException(err_msg, e);
		/*        */	}


		ret.setPainterStacksBase();
		ret.setPainterStackForRules();


		return ret;
	}
}
