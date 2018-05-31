package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.css_generator.rules.CssRule;

/**
 * Paints the size of an element (modifies its css rule accordingly)
 * Does not affect the css rule directly, but "updates" other painters.
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterSizeFactor extends Painter
{
	private double sizeFactor = 1.0;
	public  double getSizeFactor()   {  return sizeFactor; }

	public PainterSizeFactor(double sizeFactor)
	{	this.sizeFactor    = sizeFactor;
	}

	public CssRule apply(CssRule element, String pseudoClass)
	{
		return element;
	}

	public boolean update(IPainter applier)
	{
		// This painter cannot be influenced ("updated") by other painters.
		return false;
	}

	@Override
	public String toString()
	{
		return "PainterSizeFactor [sizeFactor=" + sizeFactor + "]";
	}

}
