package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paints the padding and margin an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterMarginPadding extends Painter
{

	private String             padding         ;
	private String             margin          ;

	/**
	 *
	 * @param padding      Ex: "0px"   "1px 2px 0px 3px"
	 * @param margin       Ex: "0px"   "1px 2px 0px 3px"
	 */
	public PainterMarginPadding(String margin, String padding)
	{
		this.padding       = padding;
		this.margin        = margin;
	}


	public boolean update(IPainter painter)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("Updating painter is:" + painter.getClass().getSimpleName());

		if ( painter instanceof PainterSizeFactor)
		{	double size_factor = ((PainterSizeFactor) painter).getSizeFactor();
			if (size_factor != 1)
			{	padding = HtmlUtils.applySizeFactorDouble(padding, ((PainterSizeFactor) painter).getSizeFactor());
				margin  = HtmlUtils.applySizeFactorDouble(margin , ((PainterSizeFactor) painter).getSizeFactor());
			}
		}

		/*        */	if (debug_on) logger.debug(">>");
		return false;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{
		element.set(pseudoClass, "margin ", margin );
		element.set(pseudoClass, "padding", padding);
		return element;
	}


	@Override
	public String toString()
	{
		return "PainterPaddingMargin [margin=" + margin + ", padding=" + padding + "]";
	}


}
