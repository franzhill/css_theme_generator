package com.fhi.fjl.web.css_generator.painters;

import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.web.color.palette.Palette;
import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;
import com.fhi.fjl.web.css_generator.painters.types.GradientIntensity;
import com.fhi.fjl.web.css_generator.rules.CssRule;


/**
 * Paints the background of an element (modifies its css rule accordingly)
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterBackground extends Painter
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

	private Boolean            fill              = null;
	private ETypeColorSet      colorSet          = null;
	private ETypeColorShade    shade             = null;
	private Boolean            gradient          = null;
	private GradientIntensity  gradientIntensity = null;
	private Boolean            gradientReverse   = null;
	private String             backgroundImage   = null;

	// INTERNAL

	private String             color             = null;
	private String             colorFrom         = null;
	private String             colorTo           = null;
	private Palette            palette           = null;

	/**
	 * Builder pattern, adapted. Offers an alternate way of building a PainterBackground.   <br />
	 * Ex:                                                                                  <br />
	 * <pre>
	 * new PainterBackground.Builder().fill().shade(ETypeColorShade.BASE).gradient(1).gradientReverse().backgroundImage(bckg_image_url).build()
	 * </pre>
	 * instead of
	 * <pre>
	 * new PainterBackground   (true, null, null, ETypeColorShade.BASE, true, new GradientIntensity(1), true, bckg_image_url)
	 * </pre>
	 * The main advantages are the general advantages of the builder pattern.
	 * The builder proves more verbose than the constructor when many parameters are to be set,
	 * however, when only few parameters are to be set, it is less verbose.
	 * In all cases it proves vastly more readable, scalable and versatile.
	 */
	public static class Builder
	{	PainterBackground pb;

		/**
		 * Build a new PainterBackground 'from scratch'.
		 * @see Builder
		 */
		public Builder()
		{	pb = new PainterBackground();
		}

		/**
		 * Build a new PainterBackground on the basis of the passed PainterBackground.
		 * @see Builder
		 */
		public Builder(PainterBackground p)
		{	pb = (PainterBackground) p.clone();
		}

		/**
		 * Same as fill(true)
		 */
		public Builder fill             ()                                        {pb.fill              = true                                      ; return this; }

		/**
		 * Fill background or not. If <tt>false</tt> no background will be applied.
		 */
		public Builder fill             (Boolean           fill              )    {pb.fill              = fill                                      ; return this; }

		/**
		 * Background color, specified directly (ex: "#FFFFFF").
		 * The other way to specify background color is to specify or both <tt>colorSet</tt> and <tt>shade</tt>.
		 */
		public Builder color            (String            color             )    {pb.color             = color                                     ; return this; }

		/**
		 * ColorSet from which to pick background color.
		 */
		public Builder colorSet         (ETypeColorSet     colorSet          )    {pb.colorSet          = colorSet                                  ; return this; }

		/**
		 * Shade of the colorSet to pick as background color.
		 */
		public Builder shade            (ETypeColorShade   shade             )    {pb.shade             = shade                                     ; return this; }

		/**
		 * Same as gradient(<tt>true</tt>).
		 */
		public Builder gradient         ()                                        {pb.gradient          = true                                      ; return this; }

		/**
		 * Apply a gradient to the background, or not. (By default, no gradient is applied).
		 */
		public Builder gradient         (Boolean           gradient          )    {pb.gradient          = gradient                                  ; return this; }

		/**
		 * Apply a gradient of given intensity. See {@link GradientIntensity} for acceptable values.
		 * Same as calling <tt>gradient(true)</tt> and <tt>gradientIntensity(gradientIntensity)</tt>
		 */
		public Builder gradient         (int               gradientIntensity )    {pb.gradient          = true                                      ;
		                                                                           pb.gradientIntensity = new GradientIntensity(gradientIntensity)  ; return this; }

		/**
		 * Set intensity for gradient.
		 */
		public Builder gradientIntensity(int               gradientIntensity )    {pb.gradientIntensity = new GradientIntensity(gradientIntensity)  ; return this; }

		/**
		 * Same as <tt>gradientReverse(true)</tt>
		 * @return
		 */
		public Builder gradientReverse  ()                                        {pb.gradientReverse   = true                                      ; return this; }

		/**
		 * Reverse direction of currently set gradient, or not.
		 */
		public Builder gradientReverse  (Boolean           gradientReverse   )    {pb.gradientReverse   = gradientReverse                           ; return this; }

		/**
		 *
		 * @param backgroundImage
		 * @return
		 */
		public Builder backgroundImage  (String            backgroundImage   )    {pb.backgroundImage   = backgroundImage                           ; return this; }

		/**
		 * Finalize building and return the built PainterBackground.
		 */
		public PainterBackground build()
		{	return pb;
		}
	}


	/**
	 * @used_by {@link PainterBackground.Builder}
	 */
	protected PainterBackground() {}



	/**
	 * This is the full constructor that allows to pass all settings.                                        <br />
	 * <b>Notice</b>: since this constructor accomodates all settings, some of these may not be compatible          <br />
	 *         with others, or may be redundant: use the one to suit the desired effect and
	 *         pass <tt>null</tt> to the others. In the case of clashing settings, one will get priority.    <br />
	 * <b>Notice</b>:the Builder pattern now offers a more convenient way to build a PainterBackground.
	 *         Use it rather than a constructor, when possible: {@link PainterBackground.Builder}                                 <br />
	 *
	 * @param fill              fill background or not. If false no background will be applied.
	 * @param color             fill background with this color (ex: "#FFFFFF").
	 *                          Specify either this or both <tt>colorSet</tt> and <tt>shade</tt> (in that case pass <tt>null</tt>)
	 * @param colorSet          colorSet from which to pick the color for the background. If specified, will be used instead of <tt>color</tt>,
	 *                          and <tt>shade</tt> should be specified too.
	 * @param shade             shade of the colorSet, defining the color to use for the background.
	 * @param gradient          fill background with a gradient of the passed color (<tt>fill</tt> should be set to <tt>true</tt>)
	 *
	 * @param gradientIntensity intensity of the background gradient. Ex: "new GradientIntensity(3)"
	 * @param gradientReverse   reverse the direction of the gradient. Useful for rollover (hover) effects.
	 * @param backgroundImage   define a background image. Ex: "url(http:// ...)" "url(images/pic23.png)" "none"
	 */
	public PainterBackground(Boolean           fill             ,
	                         String            color            ,
	                         ETypeColorSet     colorSet         ,
	                         ETypeColorShade   shade            ,
	                         Boolean           gradient         ,
	                         GradientIntensity gradientIntensity,
	                         Boolean           gradientReverse  ,
	                         String            backgroundImage
	                        )
	{
		this.fill              = (fill              != null) ? fill              : null;
		this.color             = (color             != null) ? color             : null;
		this.colorSet          = (colorSet          != null) ? colorSet          : null;
		this.shade             = (shade             != null) ? shade             : null;
		this.gradient          = (gradient          != null) ? gradient          : null;
		this.gradientIntensity = (gradientIntensity != null) ? gradientIntensity : null;
		this.gradientReverse   = (gradientReverse   != null) ? gradientReverse   : null;
		this.backgroundImage   = (backgroundImage   != null) ? backgroundImage   : null;
	}


	/**
	 * Convenience constructor to create a painter applying no background.
	 * The idiomatic way to call that constructor would be to pass <tt>false</tt>, but
	 * passing true will have the same effect.
	 * @param b idiomatically this should be <tt>false</tt>.
	 */
	public PainterBackground(boolean b)
	{	this(false, null, null, null, null, null, null, null);
	}


	/**
	 * Convenience constructor.
	 * For a background with no gradient.
	 * @param fill
	 * @param colorSet
	 * @param shade
	 */
	public PainterBackground(ETypeColorSet colorSet, ETypeColorShade shade)
	{	this(true, null, colorSet, shade, false, null, null, null);
	}


	public PainterBackground(ETypeColorSet colorSet, ETypeColorShade shade, String backgroundImage)
	{	this(true, null, colorSet, shade, false, null, null, backgroundImage);
	}

	/**
	 * Convenience constructor.
	 * For a background with a directly defined color.
	 *
	 * @param color        Ex: "#FFA083".
	 */
	public PainterBackground(String color)
	{	this(true, color, null, null, false, null, null, null);
	}


	public PainterBackground(String color, String backgroundImage)
	{	this(true, color, null, null, false, null, null, backgroundImage);
	}


	/**
	 * Return a copy (clone) of this painter, but with the gradient reverse property inverted.
	 * Convenience function.
	 * The result is the same as
	 * <tt>PainterBackground.Builder(this).reverseGradient().build()</tt>
	 * @return
	 */
	public PainterBackground reverseGradient()
	{
		PainterBackground ret = (PainterBackground) this.clone();
		if (this.gradientReverse != null)
		{	ret.gradientReverse = ! this.gradientReverse;
		}
		return ret;
	}


	public boolean update(IPainter updater)
	{
		if (updater instanceof PainterBackground)
		{	// Merge the two:
			PainterBackground u = (PainterBackground) updater;
			if (u.fill              != null) { this.fill              = u.fill   ;}
			if (u.color             != null) { this.color             = u.color  ;
			                                   this.colorSet          = null     ;
			                                   this.shade             = null     ;
			                                 }
			if (u.colorSet          != null) { this.colorSet          = u.colorSet            ;}
			if (u.shade             != null) { this.shade             = u.shade               ;}
			if (u.gradient          != null) { this.gradient          = u.gradient            ;}
			if (u.gradientIntensity != null) { this.gradientIntensity = u.gradientIntensity   ;}
			if (u.gradientReverse   != null) { this.gradientReverse   = u.gradientReverse     ;}
			if (u.backgroundImage   != null) { this.backgroundImage   = u.backgroundImage     ;}

			// Return true to indicate that the updater can be removed from the painter stack:
			return true;
		}

		if (fill != null && fill)
		{
			if (updater instanceof PainterColor)
			{	palette = ((PainterColor) updater).getPalette();
			}
		}
		return false;
	}


	public CssRule apply(CssRule r, String pseudoClass)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("pseudoClass=" + pseudoClass);
		/*        */	if (debug_on) logger.debug("rule=" + r.print());
		/*        */	if (debug_on) logger.debug("removing background-color, background-image and background..." );

		r.remove(pseudoClass, "background-color");
		r.remove(pseudoClass, "background-image");
		r.remove(pseudoClass, "background"      );

		/*        */	if (debug_on) logger.debug("rule=" + r.print());

		if (fill != null && !fill)
		{	r.set(pseudoClass, "background"      , "none");
		}

		if (fill != null && fill)
		{
			if (backgroundImage != null)
			{	//this.backgroundImage   = (StringUtils.isEmpty(backgroundImage)) ? "none" : backgroundImage;
				r.set(pseudoClass, "background-image", backgroundImage);
			}

			if (colorSet != null && shade != null && palette != null)
			{	color = palette.getColorSet(colorSet).getShade(shade).printHex();
			}
			r.set(pseudoClass, "background-color", color);
			r.set(pseudoClass, "background"      , color);


			if (gradient != null && gradient)
			{	int gradient_int = (gradientIntensity == null) ? 0 : gradientIntensity.getValue();
				    gradient_int = (gradientReverse)           ? gradient_int * -1 : gradient_int;

				int shade_from = 0;
				int shade_to   = 0;

				if (Math.abs(gradient_int) == 1)
				{	shade_to = Math.abs(gradient_int);
				}
				else if (Math.abs(gradient_int) == 2)
				{	shade_from=-1;
					shade_to  = 1;
				}
				else if (Math.abs(gradient_int) == 3)
				{	shade_from=-2;
					shade_to  = 1;
				}
				else
				{	shade_to  = -2;
					shade_from=  2;
				}
				if (gradient_int < 0)
				{	int tmp    = shade_from;
					shade_from = shade_to;
					shade_to   = tmp;
				}

				colorFrom = palette.getColorSet(colorSet).getShade(ETypeColorShade.lookup(shade_from)).printHex();
				colorTo   = palette.getColorSet(colorSet).getShade(ETypeColorShade.lookup(shade_to  )).printHex();

				if (colorFrom != null && colorTo != null)
				{	r.set(pseudoClass, true , "background-image", "-webkit-gradient(       linear, left top, left bottom, from(            " + colorFrom + "), to(           " + colorTo + "))"     );    //<%--  Safari 4+, Chrome 1-9  --%>
					r.set(pseudoClass, false, "background-image", "-webkit-linear-gradient(top,                                            " + colorFrom + ",                " + colorTo + " )"     );    //<%--  Safari 5.1+, Mobile Safari, Chrome 10+ --%>
					r.set(pseudoClass, true , "background"      , "-webkit-gradient(       linear, left top, left bottom, color-stop(0.05, " + colorFrom + "), color-stop(1, " + colorTo + "))"     );    //<%--  Chrome, "Fancy apple-style curve"--%>
					r.set(pseudoClass, false, "background"      , "-moz-linear-gradient(   center top,                                     " + colorFrom + ",                " + colorTo + " )"     );    //<%--  Firefox 3.6+  -  Careful ! no space between -moz-linear-gradient and ( --%>
					r.set(pseudoClass, true , "background-image", "-ms-linear-gradient(    top,                                            " + colorFrom + ",                " + colorTo + " )"     );    //<%--  IE 10+ --%>
					r.set(pseudoClass, false, "background-image", "-o-linear-gradient(     top,                                            " + colorFrom + ",                " + colorTo + " )"     );    //<%--  Opera 11.10+ --%>
				}
			}
			else
			{	//r.set(pseudoClass, true , "background-image", "none" );    //<%--  Safari 4+, Chrome 1-9  --%>
				////r.set(pseudoClass, false, "background-image", "none" );    //<%--  Safari 5.1+, Mobile Safari, Chrome 10+ --%>
				//r.set(pseudoClass, true , "background"      , "none" );    //<%--  Chrome, "Fancy apple-style curve"--%>
				////r.set(pseudoClass, false, "background"      , "none" );    //<%--  Firefox 3.6+  -  Careful ! no space between -moz-linear-gradient and ( --%>
				//r.set(pseudoClass, true , "background-image", "none" );    //<%--  IE 10+ --%>
				////r.set(pseudoClass, false, "background-image", "none" );
			}
		}

		/*        */	if (debug_on) logger.debug(">>");
		return r;
	}


	@Override
	public String toString()
	{
		return "PainterBackground = [fill=" + fill + ", gradient=" + gradient + ", color=" + color +", colorSet=" + colorSet + ", shade=" + shade + "]";
	}



}
