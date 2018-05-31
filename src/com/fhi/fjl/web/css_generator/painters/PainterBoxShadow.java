package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;
import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paints the shadow of an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterBoxShadow extends Painter
{
	protected String             oneLiner          = null;
	protected String             position          = null;
	protected String             color             = null;
	protected Boolean            inset             = null;
	protected ETypeColorSet      colorSet          = null;
	protected ETypeColorShade    shade             = null;
	protected Palette            palette           = null;


	/**
	 * Builder pattern, adapted. Offers an alternate way of building a PainterBoxShadow.   <br />
	 * @see PainterBackground.Builder
	 */
	public static class Builder
	{	PainterBoxShadow pb;

		/**
		 * Build a new PainterBorder 'from scratch'.
		 * @see Builder
		 */
		public Builder()
		{	pb = new PainterBoxShadow();
		}

		/**
		 * Build a new PainterBorder on the basis of the passed PainterBorder.
		 * @see Builder
		 */
		public Builder(PainterBoxShadow p)
		{	pb = (PainterBoxShadow) p.clone();
		}

		/**
		 *  Ex: "0px 1px 0px 0px" (h shadow, v shadow, blur, spread)
		 */
		public Builder position         (String            position          )    {pb.position          = position                                   ; return this; }

		/**
		 * Applies an empty/removes shadow.
		 * Same as <tt>position("0px 0px 0px 0px")</tt>
		 */
		public Builder none            ()                                         {pb.position          = "0px 0px 0px 0px"                         ; return this; }

		/**
		 * Shadow color, specified directly (ex: "#FFFFFF").
		 * The other way to specify the color is to specify or both <tt>colorSet</tt> and <tt>shade</tt>.
		 */
		public Builder color            (String            color             )    {pb.color             = color                                     ; return this; }

		/**
		 * ColorSet from which to pick shadow color.
		 */
		public Builder colorSet         (ETypeColorSet     colorSet          )    {pb.colorSet          = colorSet                                  ; return this; }

		/**
		 * Shade of the colorSet to pick as shadow color.
		 */
		public Builder shade            (ETypeColorShade   shade             )    {pb.shade             = shade                                     ; return this; }

		/**
		 * Make the shadow 'inset' (in terms of css, postfixes "inset" to the shadow property line.
		 */
		public Builder inset            ()                                        {pb.inset             = true                                      ; return this; }

		/**
		 * Apply a ready-made 'shine' effect. This is made by placing the shadow 'inset' (inside the box).
		 */
		public Builder shine            ()
		{	pb.position = "0px 1px 3px 0px";
			pb.inset    = true;
			return this;
		}

		/**
		 * Finalize building and return the built PainterBoxShadow.
		 */
		public PainterBoxShadow build()
		{	return pb;
		}
	}

	/**
	 * @used_by {@link PainterBoxShadow.Builder}
	 */
	protected PainterBoxShadow() {}


	/**
	 * Pass null for any non-applying properties.
	 * In that case the element will not be changed for that property.
	 *
	 * @param typeColorSet ColorSet of the Palette (possibly provided later by PainterColor) to use.
	 * @param oneLiner     css shadow properties in one line.
	 *                     Ex: "0px 1px 0px 0px #888888 inset",  "0px 1px 0px 0px #888888"
	 *                     (h shadow, v shadow, blur, spread, inset)
	 */
	public PainterBoxShadow(String oneLiner)
	{
		this.oneLiner = oneLiner;
	}

	/**
	 * @param position  Ex: "0px 1px 0px 0px" (h shadow, v shadow, blur, spread)
	 * @param color     Ex: "#FFFFFF"
	 * @param inset     Pass true for an inset shadow (i.e. a shadow inside the box and not outside)
	 */
	public PainterBoxShadow(String position, String color, Boolean inset)
	{
		this.position = position ;
		this.color    = color    ;
		this.inset    = inset    ;
	}

	/**
	 * @param position  Ex: "0px 1px 0px 0px" (h shadow, v shadow, blur, spread)
	 * @param colorSet  ColorSet of a subsequent color painter applied to the painter stack, to use for the shadow color.
	 * @param shade     shade of the above ColorSet to use for the shadow color.
	 * @param inset     pass true for an inset shadow (i.e. a shadow inside the box and not outside)
	 */
	public PainterBoxShadow(String position, ETypeColorSet colorSet, ETypeColorShade shade, Boolean inset)
	{
		this.position = position ;
		this.colorSet = colorSet ;
		this.shade    = shade    ;
		this.inset    = inset    ;
	}


	/**
	 * Convenience constructor to create a layer applying no shadow.
	 * The idiomatic way to call that constructor would be to pass <tt>false</tt>, but
	 * passing true will have the same effect.
	 * @param b idiomatically this should be <tt>false</tt>.
	 */
	public PainterBoxShadow(boolean b)
	{
		this.oneLiner      = "0px";
	}




	public boolean update(IPainter painter)
	{
		if ( painter instanceof PainterBoxShadow)
		{	// Merge the two:
			PainterBoxShadow updater = (PainterBoxShadow) painter;

			this.oneLiner          = (updater.oneLiner  != null) ? updater.oneLiner  : this.oneLiner  ;
			this.position          = (updater.position  != null) ? updater.position  : this.position  ;
			this.color             = (updater.color     != null) ? updater.color     : this.color     ;
			this.inset             = (updater.inset     != null) ? updater.inset     : this.inset     ;
			this.colorSet          = (updater.colorSet  != null) ? updater.colorSet  : this.colorSet  ;
			this.shade             = (updater.shade     != null) ? updater.shade     : this.shade     ;
			this.palette           = (updater.palette   != null) ? updater.palette   : this.palette   ;
			return true;
		}

		if ( painter instanceof PainterSizeFactor)
		{	double size_factor = ((PainterSizeFactor) painter).getSizeFactor();
			if (size_factor != 1)
			{	oneLiner = HtmlUtils.applySizeFactorDouble(oneLiner, size_factor);
			}
		}

		if (painter instanceof PainterColor)
		{
			this.palette = ((PainterColor) painter).getPalette();
		}

		return false;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{
		if (color == null && palette != null && colorSet != null && shade != null)
		{	color = palette.getColorSet(colorSet).getShade(shade).printHex();
		}

		String one_liner = (oneLiner != null) ? oneLiner
		                                      : position + " " + color + ((inset != null && inset) ? " inset" : "");

		element.set(pseudoClass, "-moz-box-shadow"      , one_liner);
		element.set(pseudoClass, "-webkit-box-shadow"   , one_liner);
		element.set(pseudoClass, "box-shadow"           , one_liner);
		return element;
	}


	@Override
	public String toString()
	{
		return "PainterBoxShadow = [oneLiner=" + oneLiner
				+ ", position=" + position + ", color=" + color + ", inset="
				+ inset + ", colorSet=" + colorSet + ", shade=" + shade + "]";
	}


}
