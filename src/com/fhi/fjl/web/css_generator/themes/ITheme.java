package com.fhi.fjl.web.css_generator.themes;

import java.util.Set;

import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.css_generator.painters.PainterStack;


/**
 *
 * @author francois hill
 * @since 1 déc. 2012
 *
 */
public interface ITheme
{

	/**
	 * Get the painter stack to be applied on the specified rule, as defined by this theme.
	 * Returns a clone.
	 *
	 * @param ruleName
	 * @return
	 */
	public PainterStack getPainterStackForRule(String ruleName);

	/**
	 * Get rules that this 1theme (re)defines.
	 *
	 * @return set of rules (rule names)
	 */
	public Set<String> getRulesAffected();


	public void setPalette(Palette palette);

	/**
	 * Return the name of this theme
	 * @return
	 */
	public    String  getName();
}
