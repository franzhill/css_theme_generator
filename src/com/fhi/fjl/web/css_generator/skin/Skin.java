package com.fhi.fjl.web.css_generator.skin;

import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.core.BaseSupport;
import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.css_generator.painters.PainterColor;
import com.fhi.fjl.web.css_generator.painters.PainterSizeFactor;
import com.fhi.fjl.web.css_generator.painters.PainterStack;
import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.css_generator.themes.ITheme;

/**
 *
 * @author francois hill
 * @since 1 déc. 2012
 *
 */
public class Skin extends BaseSupport
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

	private ITheme  theme     ;
	private Palette palette   ;
	private double  sizeFactor;

	public ITheme   getTheme()                         {	return theme;                }
	public Palette getPalette()                       {	return palette;              }
	public double  getSizeFactor()                    {	return sizeFactor;           }


	public Skin(ITheme theme, Palette palette, double sizeFactor)
	{
		super();
		this.theme      = theme;
		this.palette    = palette;
		this.sizeFactor = sizeFactor;

		theme.setPalette(palette);
	}


	/**
	 * Print out all the css rules of this skin.
	 * The result is a directly usable css stylesheet.
	 *
	 * @return
	 */
	public String print()
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		StringBuffer sb = new StringBuffer();

		PainterColor      p_color = new PainterColor(palette);
		PainterSizeFactor p_size  = new PainterSizeFactor(sizeFactor);

		for (String rule_name: theme.getRulesAffected())
		{
			CssRule      rule = new CssRule(rule_name);
			PainterStack ps   = theme.getPainterStackForRule(rule_name)
			                         .add(p_color)
			                         .add(p_size);

			/*        */	if (debug_on) logger.debug("\n - Rule name     = " + rule_name);
			/*        */	if (debug_on) logger.debug(  "   Painter stack = " + ps);

			             rule = ps
			                      .apply(rule);
			sb.append(rule.print()+ "\n");
		}
		/*        */	if (debug_on) logger.debug("<<");
		return sb.toString();
	}


}
