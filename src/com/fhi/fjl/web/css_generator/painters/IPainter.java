package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.css_generator.rules.CssRule;




/**
 * Applies modifications to ("paints") a page element
 * (in effect, changes the css rule {@lin CssRule}) that will apply to the page element)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public interface IPainter extends Cloneable
{
	/**
	 * Apply this painter on a page element (i.e. on its css rule)
	 *
	 * @param rule
	 * @param pseudoClass optional (pass null if non applicable). Ex: "active", "hover", "focus", "link"...
	 *                    Pseudo class of the rule to which the settings should apply
	 *                    Example if the rule is ".myClass", then passing the pseudo class "hover" will
	 *                    apply the painter not to ".myClass" but to ".myClass:hover".
	 * @return
	 */
	public CssRule apply(CssRule rule, String pseudoClass);


	/**
	 * Alias for apply(rule, null)
	 * Implementing classes should simply redirect to that call
	 * (it is not possible to do so here, this being an interface and not an abstract class)
	 * @see {@link #apply(CssRule, String)}
	 * @param rule
	 * @return
	 */
	public CssRule apply(CssRule rule);


	/**
	 * Update this painter to take in modifications due to the passed painter.
	 * Used when painters are stacked in a {@link PainterStack}: a painter added on
	 * top of the stack can update all the painters beneath it, i.e. have a side effect
	 * e.g. applying color.
	 *
	 * @param  p
	 * @return indicate whether the updater painter can be removed from the painterStack
	 *         once the update is done. This can be useful for cases where the updating
	 *         painter is just a complement to a specific painter in the stack, and once
	 *         this painter has been updated there is no more justification for the top
	 *         stack painter anymore.
	 *         Note for extending classes: unless you know exactly what you're doing,
	 *         you should return <tt>false</tt>
	 */
	public boolean update(IPainter p);

	public PainterStack add(IPainter p);

	public IPainter clone();
}