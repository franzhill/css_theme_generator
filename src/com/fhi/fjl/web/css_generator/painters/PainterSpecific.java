package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paint a specific attribute of an element (i.e. modifies the specific css attribute for its css rule)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterSpecific extends Painter
{
	private String cssProperty;
	private String value;

	public PainterSpecific(String cssProperty, String value)
	{	this.cssProperty = cssProperty;
		this.value       = value      ;
	}




	public boolean update(IPainter painter)
	{
		if ( painter instanceof PainterSizeFactor)
		{	value = HtmlUtils.applySizeFactorDouble(value, ((PainterSizeFactor) painter).getSizeFactor());
		}
		return false;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{
		element.set(pseudoClass, cssProperty  , value  );
		return element;
	}



}
