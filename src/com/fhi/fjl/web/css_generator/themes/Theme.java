package com.fhi.fjl.web.css_generator.themes;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.core.BaseSupport;
import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.css_generator.painters.IPainter;
import com.fhi.fjl.web.css_generator.painters.PainterStack;
import com.fhi.fjl.web.css_generator.themes.test.ECssRule;





/**
 *
 * @author francois hill
 * @since 1 déc. 2012
 *
 */
public abstract class Theme extends BaseSupport implements ITheme
{
	// ==========================================================================
	// LOGGING
	// ==========================================================================

	// Redefine preset fields :
	// See base parent class
	{
		logger  = LogFactory.getLog("com.fhi.fjl.web.css_generator.painters");
		slogger = logger;
		debugOn = true
		                       && logger.isDebugEnabled();
	}


	// ==========================================================================
	// BUSINESS
	// ==========================================================================


	/**
	 * Map of base painterstacks used by the theme to paint the rules
	 * Ex:
	 *   painterStacks['base']     : the base painter stack
	 *   painterStacks['buttons']  : the base painter stack for buttons
	 *   painterStacks['borders']  : the base painter stack for borders
	 * etc.
	 *
	 * Administration of that map and the name of its keys is left entirely up the the (extending) themes.
	 */
	protected Map<String, IPainter> painterStacksBase = new HashMap<String, IPainter>();

	protected Palette palette = Palette.empty();

	protected Map<String, PainterStack> painterStacksByRule       = new LinkedHashMap<String, PainterStack>();
	protected Map<String, PainterStack> getPainterStacksByRule()  {return painterStacksByRule;}




	/**
	 * Use the factory to create themes.
	 */
	protected Theme()
	{	// FORBIDDEN - Use the factory to create themes.
	}


	abstract public   String  getName();


	public final void setPalette(Palette palette)
	{	this.palette = palette;
	}


	public final PainterStack getPainterStackForRule(String ruleName)
	{	return painterStacksByRule.get(ruleName).clone();
	}


	public final Set<String> getRulesAffected()
	{	return painterStacksByRule.keySet();
	}


	/**
	 * Add a painter stack for the specified css rule.
	 * If the theme already contained a painterstack for the passed rule, then it will be overwritten.
	 *
	 * @param e
	 * @param m modifier to add. In effect, a clone of the modifier is added, so a reference to a "changing" modifier can be passed.
	 */
	protected void set(String ruleName, PainterStack m)
	{	painterStacksByRule.put(ruleName, m/*.clone()*/);
	}


	/**
	 * Add a painter to the the painter stack of the specified element.
	 * Useful in cases where a theme extends another.
	 *
	 */
	protected final void add(String ruleName, IPainter painter)
	{
		// If element has not been given a painterstack (i.e. it's not in the map)
		if (! painterStacksByRule.containsKey(ruleName))
		{	// Then insert it:
			PainterStack ls = new PainterStack();
			             ls. add(painter);
			set(ruleName, ls);
		}
		else
		{	set(ruleName, painterStacksByRule.get(ruleName).add(painter));
		}
	}


	/**
	 * Get a base painter stack used for this theme, based on its name.                       <br />
	 * Returns <tt>painterStacksBase.get('name')</tt> or <tt>new PainterStack()</tt> if
	 * no entry for <tt>name</tt>.
	 *
	 * Ex:
	 *   getPsb('base'   )  : the base painter stack
	 *   getPsb('buttons')  : the base painter stack for buttons
	 *   getPsb('borders')  : the base painter stack for borders
	 * etc.
	 *
	 * @see {@link #painterStacksBase}
	 */
	protected final IPainter    getPsb(String name)
	{
		IPainter ret = painterStacksBase.get(name);
		if (ret == null) {return new PainterStack();};
		return ret;
	}


	abstract protected void    setPainterStacksBase();

	/**
	 * Set the base painter stack, for each rule, used for this theme.                                                <br />
	 *                                                                                                                <br />
	 * Note for extending classes:                                                                                    <br />
	 * - in this function, you should assign a painter stack to each {@link ECssRule} affected by this theme.         <br />
	 *
	 */
	abstract protected void    setPainterStackForRules();


}