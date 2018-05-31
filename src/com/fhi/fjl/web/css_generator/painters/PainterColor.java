package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.css_generator.rules.CssRule;

/**
 * Paints the color of an element (modifies its css rule accordingly)
 * Does not affect the css rule directly, but "updates" other painters.
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterColor extends Painter
{
	private Palette  palette;
	public  Palette  getPalette()  {return palette;}


	public PainterColor(Palette pal)
	{	this.palette = pal;
	}


	public CssRule apply(CssRule e, String pseudoClass)
	{
		// This applier only modifies the other appliers, not the element directly.
		return e;
	}


	public boolean update(IPainter painter)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("Updating painter is:" + painter.getClass().getSimpleName());

		// This painter cannot be influenced ("updated") by other painters.

		/*        */	if (debug_on) logger.debug(">>");
		return false;
	}








}
