package com.fhi.fjl.web.css_generator.painters;

import java.util.Arrays;
import java.util.List;

import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paints the padding and margin an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterPosition extends Painter
{
	private String             position        ;
	private String             trbl            ;


	/**
	 *
	 * @param position     Ex: "static" "relative" "absolute" "fixed"
 	 * @param trbl         optional (pass <tt>null</tt> otherwise). Top/right/bottom/left shift, if applicable.
 	 *                     Ex: "1px 2px 0px -3px"
	 *                     0nly applicable/required if the <tt>position</tt> setting requires it.
	 *                     Default is "0px 0px 0px 0px".
	 *                     Any incomplete value (e.g. "1px 1px 1px") is authorized and the corresponding
	 *                     setting(s) will not be set onto the css rule.
	 */
	public PainterPosition(String position, String trbl)
	{
		this.position       = position;
		this.trbl           = trbl    ;
	}


	public boolean update(IPainter painter)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("Updating painter is:" + painter.getClass().getSimpleName());

		if ( painter instanceof PainterSizeFactor)
		{	double size_factor = ((PainterSizeFactor) painter).getSizeFactor();
			if (size_factor != 1)
			{	trbl  = HtmlUtils.applySizeFactorDouble(trbl , ((PainterSizeFactor) painter).getSizeFactor());
			}
		}

		/*        */	if (debug_on) logger.debug(">>");
		return false;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{	List<String> shifts      = Arrays.asList(trbl.split(" "));
		int          shifts_size = shifts.size();

		                         element.set(pseudoClass, "position", position      );
		if (shifts_size >= 1) {  element.set(pseudoClass, "top "    , shifts.get(0));}
		if (shifts_size >= 2) {  element.set(pseudoClass, "right "  , shifts.get(1) );}
		if (shifts_size >= 3) {  element.set(pseudoClass, "bottom"  , shifts.get(2) );}
		if (shifts_size >= 4) {  element.set(pseudoClass, "left"    , shifts.get(3) );}
		return element;
	}


	@Override
	public String toString()
	{
		return "PainterPosition [position=" + position + ", trbl=" + trbl + "]";
	}





}
