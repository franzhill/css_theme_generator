package com.fhi.fjl.web.css_generator.painters;

import org.apache.commons.lang3.StringUtils;

import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;
import com.fhi.fjl.web.css_generator.rules.CssRule;
import com.fhi.fjl.web.html.HtmlUtils;

/**
 * Paints the text of an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterText extends Painter
{

	private String             fontSize     = null;
	private String             fontWeight   = null;
	private String             fontStyle    = null;
	private String             fontFamily   = null;
	private String             color        = null;
	private ETypeColorSet      colorSet     = null;
	private ETypeColorShade    shade        = null;
	private String             shadow       = null;
	private Boolean            activateShadow = null;
	private String             textAlign    = null;

	private Palette            palette      = null;
	private Boolean            emboss       = null;
	private Boolean            glow         = null;


	/**
	 * Builder pattern, adapted. Offers an alternate way of building a PainterText.   <br />
	 * @see PainterBackground.Builder
	 */
	public static class Builder
	{	PainterText pb;

		/**
		 * Build a new PainterBorder 'from scratch'.
		 * @see Builder
		 */
		public Builder()
		{	pb = new PainterText();
		}

		/**
		 * Build a new PainterBorder on the basis of the passed PainterBorder.
		 * @see Builder
		 */
		public Builder(PainterText p)
		{	pb = (PainterText) p.clone();
		}

		/**
		 *  Ex: "13px"
		 */
		public Builder fontSize         (String            fontSize          )    {pb.fontSize          = fontSize                                   ; return this; }

		/**
		 */
		public Builder fontWeight       (String            fontWeight        )    {pb.fontWeight        = fontWeight                                 ; return this; }
		public Builder fontStyle        (String            fontStyle         )    {pb.fontStyle         = fontStyle                                  ; return this; }
		public Builder fontFamily       (String            fontFamily        )    {pb.fontFamily        = fontFamily                                 ; return this; }

		/**
		 * Text color, specified directly (ex: "#FFFFFF").
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
		 * Apply a shadow to the text. Ex: "1px 1px 0px #FFFFFF"
		 */
		public Builder shadow           (String shadow                       )    {pb.shadow            = shadow                                    ; return this; }

		/**
		 * Deactivate shadow by passing <tt>false</tt>. Passing <tt>true</tt> will have no effect.
		 * @param activateShadow  pass <tt>false</tt> to deactivate any previously set shadow
		 */
		public Builder shadow           (Boolean activateShadow              )    {pb.activateShadow    =  activateShadow                           ; return this; }

		/**
		 * Apply an arbitrary shadow to the text.
		 */
		public Builder shadow           ()                                        {pb.shadow            = "1px 1px 0px #FFFFFF"                     ; return this; }

		/**
		 *
		 */
		public Builder textAlign        (String textAlign                    )    {pb.textAlign         = textAlign                                 ; return this; }

		/**
		 * Apply an "emboss" effect.
		 * Meant to work with text in dark color on dark background.
		 * Only works if no shadow is applied. Will deactivate any shadow setting.
		 * @return
		 */
		public Builder emboss()
		{
			pb.shadow = null;
			pb.emboss = true;
			return this;
		}

		/**
		 * Apply an "glow" effect.
		 * Meant to work with text in dark color on dark background.
		 * Only works if no shadow is applied. Will deactivate any shadow setting.
		 * @return
		 */
		public Builder glow()
		{
			pb.shadow = null;
			pb.glow   = true;
			return this;
		}


		/**
		 * Finalize building and return the built PainterBoxShadow.
		 */
		public PainterText build()
		{	return pb;
		}
	}

	/**
	 * @used_by {@link PainterText.Builder}
	 */
	protected PainterText() {}





	/**
	 * Pass null for any non-applying properties.
	 * In that case the element will not be changed for that property.
	 *
	 * <b>Notice</b>:the Builder pattern now offers a more convenient way to build a PainterText.
	 *         Use it rather than a constructor, when possible: {@link PainterText.Builder}                                 <br />
	 *
	 * @param fontSize          ex: "13px"
	 * @param fontWeight
	 * @param fontStyle         ex: "solid", "dotted"
	 * @param fontFamily        ex: "arial", "helvetica"
	 * @param color             fill background with this color (ex: "#FFFFFF").
	 *                          Specify either this or both <tt>colorSet</tt> and <tt>shade</tt> (in that case pass <tt>null</tt>)
	 * @param colorSet          colorSet from which to pick the color. If specified, will be used instead of <tt>color</tt>, and
	 *                          <tt>shade</tt> should be specified too.
	 * @param shade             shade of the ColorSet of the Palette to use
	 * @param shadow            ex: "1px 1px 0px #FFFFFF"
	 * @param textAlign         ex: "left" "center"
	 */
	public PainterText(String fontSize, String fontWeight, String fontStyle, String fontFamily, String color, ETypeColorSet colorSet, ETypeColorShade shade, String shadow, String textAlign)
	{
		this.fontSize     = fontSize    ;
		this.fontWeight   = fontWeight  ;
		this.fontStyle    = fontStyle   ;
		this.fontFamily   = fontFamily  ;
		this.color        = color       ;
		this.colorSet     = colorSet;
		this.shade        = shade       ;
		this.shadow       = shadow      ;
		this.textAlign    = textAlign  ;
	}


	public boolean update(IPainter updater)
	{
		if (updater instanceof PainterText)
		{	// Merge the two:
			PainterText u = (PainterText) updater;

			if (u.fontSize   != null) { this.fontSize    = u.fontSize   ;}
			if (u.fontWeight != null) { this.fontWeight  = u.fontWeight ;}
			if (u.fontStyle  != null) { this.fontStyle   = u.fontStyle  ;}
			if (u.fontFamily != null) { this.fontFamily  = u.fontFamily ;}
			if (u.color      != null) { this.color       = u.color      ;
			                            this.colorSet    = null         ;
			                            this.shade       = null         ;
			                          }
			if (u.colorSet   != null) { this.colorSet   = u.colorSet    ;}
			if (u.shade      != null) { this.shade      = u.shade       ;}
			if (u.shadow     != null) { this.shadow     = u.shadow      ;}
			if (u.activateShadow
			                 != null) { this.activateShadow     = u.activateShadow      ;}
			if (u.textAlign  != null) { this.textAlign  = u.textAlign   ;}
			if (u.emboss     != null) { this.emboss     = u.emboss      ;}
			if (u.glow       != null) { this.glow       = u.glow        ;}

			// Return true to indicate that the updater can be removed from the painter stack:
			return true;
		}

		if (updater instanceof PainterColor)
		{	palette = ((PainterColor) updater).getPalette();
		}

		if ( updater instanceof PainterSizeFactor)       // TODO record factor & perform calculations in apply() instead
		{	double size_factor = ((PainterSizeFactor) updater).getSizeFactor();
			if (size_factor != 1)
			{	fontSize = HtmlUtils.applySizeFactorDouble(fontSize, ((PainterSizeFactor) updater).getSizeFactor());
				shadow   = HtmlUtils.applySizeFactorDouble(shadow  , ((PainterSizeFactor) updater).getSizeFactor());
			}
		}
		return false;
	}


	/**
	 * Return a copy (clone) of this painter, with an "emboss" effect.
	 * Meant to work with text in dark color on dark background.
	 * @return
	 */
	public PainterText emboss()
	{
		PainterText ret = (PainterText) this.clone();
		            ret.shadow = null;
		            ret.emboss = true;
		return ret;
	}


	/**
	 * Return a copy (clone) of this painter, with a "glow" effect.
	 * Meant to work with text in light color.
	 * @return
	 */
	public PainterText glow()
	{
		PainterText ret = (PainterText) this.clone();
		            ret.shadow = null;
		            ret.glow   = true;
		return ret;
	}


	public CssRule apply(CssRule element, String pseudoClass)
	{
		if (colorSet != null && shade != null && palette != null)
		{	color = palette.getColorSet(colorSet).getShade(shade).printHex();
		}

		if (StringUtils.isEmpty(shadow) && palette != null && emboss != null && emboss)
		{	shadow = "1px 1px 1px " +  palette.getColorSet(colorSet).getShade(ETypeColorShade.LIGHTER_1).printHex();
		}

		if (StringUtils.isEmpty(shadow) && palette != null && glow != null && glow)
		{	shadow = "1px 1px 2px " +  color;
		}

		if (activateShadow != null && !activateShadow)
		{	shadow = null;
		}

		element.set(pseudoClass, "font-size"  , fontSize  );
		element.set(pseudoClass, "font-weight", fontWeight);
		element.set(pseudoClass, "font-style" , fontStyle );
		element.set(pseudoClass, "font-family", fontFamily);
		element.set(pseudoClass, "color"      , color     );
		element.set(pseudoClass, "text-shadow", shadow    );
		element.set(pseudoClass, "text-align" , textAlign );
		return element;
	}


	@Override
	public String toString()
	{
		return "PainterText = [fontSize=" + fontSize
				+ ", fontWeight=" + fontWeight + ", fontStyle=" + fontStyle
				+ ", fontFamily=" + fontFamily + ", color=" + color
				+ ", colorSet=" + colorSet + ", shade=" + shade
				+ ", shadow=" + shadow
				+ ", textAlign=" + textAlign + "]";
	}



}
