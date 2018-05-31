package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paints the corners of an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterCorners extends Painter
{
	private boolean round;
	private String  radius;



	/**
	 * Pass null for any non-applying properties.
	 * In that case the element will not be changed for that property.
	 *
	 * @param round  Should the corners be round or not ?
	 * @param style  Ex: "solid", "dotted"
	 */
	public PainterCorners(boolean round, String radius)
	{	this.round  = round;
		this.radius = radius;
		if (! round)
		{	radius = "0px";
		}
	}


	/**
	 * Convenience constructor to create a painter applying no special corners (i.e. square corners).
	 * The idiomatic way to call that constructor would be to pass <tt>false</tt>, but
	 * passing true will have the same effect.
	 * @param b idiomatically this should be <tt>false</tt>.
	 */
	public PainterCorners(boolean b)
	{	this(false, null);
	}

	public boolean update(IPainter painter)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("Updating painter is:" + painter.getClass().getSimpleName());

		if ( painter instanceof PainterSizeFactor)
		{	double size_factor = ((PainterSizeFactor) painter).getSizeFactor();
			if (size_factor != 1)
			{	radius = HtmlUtils.applySizeFactorDouble(radius, size_factor);
			}
		}
		/*        */	if (debug_on) logger.debug(">>");
		return false;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{
		element.set(pseudoClass, "-moz-border-radius"   , radius);
		element.set(pseudoClass, "-webkit-border-radius", radius);
		element.set(pseudoClass, "border-radius"        , radius);
		return element;
	}


}
