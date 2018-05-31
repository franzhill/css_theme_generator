package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.color.palette.ColorSet;
import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;
import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.css_generator.skin.Skin;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paints the border of an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterBorder extends Painter
{
	private String             style             = null;
	private String             width             = null;
	private ETypeColorSet      colorSet          = null;
	private ETypeColorShade    shade             = null;
	private String             color             = null;

	private Palette            palette           = null;

	/**
	 * Builder pattern, adapted. Offers an alternate way of building a PainterBorder.   <br />
	 * @see PainterBackground.Builder
	 */
	public static class Builder
	{	PainterBorder pb;

		/**
		 * Build a new PainterBorder 'from scratch'.
		 * @see Builder
		 */
		public Builder()
		{	pb = new PainterBorder();
		}

		/**
		 * Build a new PainterBorder on the basis of the passed PainterBorder.
		 * @see Builder
		 */
		public Builder(PainterBorder p)
		{	pb = (PainterBorder) p.clone();
		}

		/**
		 * Set css 'style' property. Ex: "solid", "dotted"
		 */
		public Builder style            (String            style             )    {pb.style             = style                                      ; return this; }

		/**
		 * Set css 'width' property. Ex: "0px"   "1px 2px 0px 3px"
		 */
		public Builder width            (String            width             )    {pb.width             = width                                      ; return this; }

		/**
		 * Applies an empty border (i.e. no border).
		 * Same as <tt>width("0px")</tt>
		 */
		public Builder none            ()                                         {pb.width             = "0px"                                      ; return this; }

		/**
		 * Border color, specified directly (ex: "#FFFFFF").
		 * The other way to specify the color is to specify or both <tt>colorSet</tt> and <tt>shade</tt>.
		 */
		public Builder color            (String            color             )    {pb.color             = color                                     ; return this; }

		/**
		 * ColorSet from which to pick border color.
		 */
		public Builder colorSet         (ETypeColorSet     colorSet          )    {pb.colorSet          = colorSet                                  ; return this; }

		/**
		 * Shade of the colorSet to pick as border color.
		 */
		public Builder shade            (ETypeColorShade   shade             )    {pb.shade             = shade                                     ; return this; }

		/**
		 * Finalize building and return the built PainterBorder.
		 */
		public PainterBorder build()
		{	return pb;
		}
	}


	/**
	 * @used_by {@link PainterBorder.Builder}
	 */
	protected PainterBorder() {}




	/**
	 * Pass <tt>null</tt> for any non-applicable properties.                                                                  <br />
	 *                                                                                                                        <br />
	 * Where a <tt>null</tt> value is passed, the corresponding ccs property or properties of the underlying rule will
	 * be left unchanged by this painter. If it did not exist, it won't be created, if it already had a value, this value
	 * will be left untouched. This allows to "pile on" multiple Painters of the same type, with the later ones either
	 * overriding (redefining) settings from the previous ones, or defining settings that were not previously defined
	 * (i.e. left <tt>null</tt>).                                                                                             <br />
	 *                                                                                                                        <br />
 	 * <b>Notice</b>:the Builder pattern now offers a more convenient way to build a PainterBackground.
	 *         Use it rather than a constructor, when possible: {@link PainterBorder.Builder}                                 <br />
	 *                                                                                                                        <br />
	 * Note that for String parameters, passing an empty string is considered a value in itself different from <tt>null</tt>.
	 * The setting will therefore be written (with the empty string as value).
	 *
	 * @param width        Ex: "0px"   "1px 2px 0px 3px"
	 * @param style        Ex: "solid", "dotted"
	 * @param color        Ex: "#FFA083". If passed (i.e. non <tt>null</tt>, will supersede the color defined by <tt>colorSet</tt> and <tt>shade</tt>.
	 * @param colorSet {@link ColorSet} of the {@link Skin} {@link Palette} to be used to set the color of the border (the palette will be applied
	 *                     by a subsequent painter, e.g. {@link PainterColor}). Use with <tt>shade</tt>, instead of passing <tt>color</tt> directly (i.e.
	 *                     passing null for <tt>color</tt>), to customize the color along the skin-defined color palette.
	 * @param shade        {@link Shade} of the {@link ColorSet} to be used for the border color.

	 */
	public PainterBorder(String width, String style, String color, ETypeColorSet colorSet, ETypeColorShade shade)
	{
		this.style        = style;
		this.width        = width;
		this.colorSet     = colorSet;
		this.shade        = shade;
		this.color        = color;
	}


	/**
	 * Convenience constructor to create a painter applying an empty border (i.e. no border)       <br />
	 *                                                                                             <br />
	 * The idiomatic way to call that constructor is to pass <tt>false</tt>
	 * (however passing <tt>true</tt> will lead to the same result).
	 *
	 * @param b idiomatically this should be <tt>false</tt>.
	 */
	public PainterBorder(boolean b)
	{	this.width        = "0px";
	}


	public boolean update(IPainter updater)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("Updating painter is:" + updater.getClass().getSimpleName());

		if (updater instanceof PainterBorder)
		{	// Merge the two:
			PainterBorder u = (PainterBorder) updater;
			this.style           = (u.style           != null) ? u.style            : this.style           ;
			this.width           = (u.width           != null) ? u.width            : this.width           ;
			this.colorSet        = (u.colorSet        != null) ? u.colorSet         : this.colorSet        ;
			this.shade           = (u.shade           != null) ? u.shade            : this.shade           ;
			this.color           = (u.color           != null) ? u.color            : this.color           ;
			// Return true to indicate that the updater can be removed from the painter stack:
			return true;
		}

		if ( updater instanceof PainterColor)
		{
			palette = ((PainterColor) updater).getPalette();
		}

		if ( updater instanceof PainterSizeFactor)  // TODO record factor & perform caclulations in apply() instead
		{	double size_factor = ((PainterSizeFactor) updater).getSizeFactor();
			if (size_factor != 1)
			{	width = HtmlUtils.applySizeFactorDouble(width, size_factor);
			}
		}

		/*        */	if (debug_on) logger.debug("Painter after update: " + this);
		/*        */	if (debug_on) logger.debug(">>");
		return false;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{
		if (colorSet != null && shade != null && palette != null)
		{	color = palette.getColorSet(colorSet).getShade(shade).printHex();
		}

		element.set(pseudoClass, "border-width", width);
		element.set(pseudoClass, "border-style", style);
		element.set(pseudoClass, "border-color", color);
		return element;
	}


	@Override
	public String toString()
	{
		return "PainterBorder [width=" + width	+ ", colorSet=" + colorSet + ", shade=" + shade + ", color=" + color + "]";
	}

}
