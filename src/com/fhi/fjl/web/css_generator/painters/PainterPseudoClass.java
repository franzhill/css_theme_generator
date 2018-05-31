package com.fhi.fjl.web.css_generator.painters;

import org.apache.commons.lang3.StringUtils;

import com.fhi.fjl.web.css_generator.rules.CssRule;

/**
 * Paints a button (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public abstract class PainterPseudoClass extends Painter
{
	protected IPainter painterPseudoClass;


	/**
	 *
	 * @return ex: "active", "hover", "focus", "link"
	 */
	abstract String getPseudoClassName();


	public PainterPseudoClass(IPainter painterPseudoClass)
	{
		this.painterPseudoClass  = painterPseudoClass ;
	}


	public boolean update(IPainter painter)
	{	if (painterPseudoClass  != null)
		{	this.painterPseudoClass .update(painter);
		}
		return false;
	}


	public CssRule apply(CssRule r, String pseudoClass)
	{
		if (painterPseudoClass  != null) {painterPseudoClass .apply(r, getPseudoClassName() ); }

		//r.setPseudo("hover", "position", "relative");
		//r.setPseudo("hover", "top", "+1px");
		//r.setPseudo("hover", "left", "+1px");
		return r;
	}


	@Override
	public String toString()
	{
		return "Painter = [" + this.getClass().getSimpleName() + "\n" +
		       "            internal painter ="                +
		       StringUtils.join(painterPseudoClass.toString().split("\n"), "\n            ")                              + "\n" +
		       "          ]";
	}


	@Override
	public PainterPseudoClass clone()
	{	PainterPseudoClass ret = (PainterPseudoClass) super.clone();
		// Deep clone the list
		ret.painterPseudoClass = painterPseudoClass.clone();
		return ret;
	}
}
