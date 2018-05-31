package com.fhi.fjl.web.css_generator.themes.test;

import java.util.Map;

import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;
import com.fhi.fjl.web.css_generator.painters.IPainter;
import com.fhi.fjl.web.css_generator.painters.PainterBackground;
import com.fhi.fjl.web.css_generator.painters.PainterBorder;
import com.fhi.fjl.web.css_generator.painters.PainterBoxShadow;
import com.fhi.fjl.web.css_generator.painters.PainterCorners;
import com.fhi.fjl.web.css_generator.painters.PainterMarginPadding;
import com.fhi.fjl.web.css_generator.painters.PainterPosition;
import com.fhi.fjl.web.css_generator.painters.PainterPseudoClassActive;
import com.fhi.fjl.web.css_generator.painters.PainterPseudoClassHover;
import com.fhi.fjl.web.css_generator.painters.PainterText;


public class ThemeSquare extends ThemeDefault
{
	// ==========================================================================
	// LOGGING
	// ==========================================================================

	//#// Redefine preset fields :
	//#// See base parent class
	//#{
	//#	logger  = LogFactory.getLog("com.fhi.fwl.core.skin.themes");
	//#	slogger = logger;
	//#	debugOn = true
	//#	                       && logger.isDebugEnabled();
	//#}


	// ==========================================================================
	// BUSINESS
	// ==========================================================================

	@Override
	public String getName()
	{	return "square";
	}

	//#/**
	//# * Use the factory to create themes.
	//# */
	//#public ThemeSquare()
	//#{	// FORBIDDEN - Use the factory to create themes.
	//#}


	@Override
	protected void setPainterStacksBase()
	{	/*        */	boolean debug_on = true && debugOn;
		/*        */	if (debug_on) logger.debug("<<");

		super.setPainterStacksBase();

		// Alias
		Map<String, IPainter> ps                 = painterStacksBase;
		//String              bckg_image_url     = "url(	http://ajax.googleapis.com/ajax/libs/dojo/1.5.0/dijit/themes/claro/images/commonHighlight.png)";
		String                bckg_image_url     = "none";
		PainterBorder         p_border_1px_solid = new PainterBorder.Builder().width("1px 1px 1px 1px").style("solid").build();

		//# // Method 1: use constructor:
		//# ps.put("p_bckg"           ,  new PainterBackground   (true, null, null, ETypeColorShade.BASE, true, new GradientIntensity(1), true, bckg_image_url)   );
		//# // Method 2: use builder:
		ps.put("p_bckg"           ,  new PainterBackground.Builder().fill().shade(ETypeColorShade.BASE).gradient(1).gradientReverse().backgroundImage(bckg_image_url).build());
		//ps.put("p_bckg_color_pr"  ,   new PainterBackground   (null, null, ETypeColorSet.PRIMARY  , null, null, null, null, null)                          );
		ps.put("p_bckg_color_pr"  ,  new PainterBackground.Builder().colorSet(ETypeColorSet.PRIMARY    ).build());
		ps.put("p_bckg_color_sa"  ,  new PainterBackground.Builder().colorSet(ETypeColorSet.SECONDARY_A).build());
		ps.put("p_bckg_color_sb"  ,  new PainterBackground.Builder().colorSet(ETypeColorSet.SECONDARY_B).build());
		ps.put("p_bckg_color_co"  ,  new PainterBackground.Builder().colorSet(ETypeColorSet.COMPLEMENT ).build());

		//# // Method 1: use constructor:
		//# ps.put("p_border_color"   ,  new PainterBorder       (null, null, null, null                     , ETypeColorShade.DARKER_1)                          );
		//# Method 2: use builder
		ps.put("p_border_color"   ,  new PainterBorder.Builder().shade   (ETypeColorShade.DARKER_1 ).build());
		ps.put("p_border_color_pr",  new PainterBorder.Builder().colorSet(ETypeColorSet.PRIMARY    ).build());
		ps.put("p_border_color_sa",  new PainterBorder.Builder().colorSet(ETypeColorSet.SECONDARY_A).build());
		ps.put("p_border_color_sb",  new PainterBorder.Builder().colorSet(ETypeColorSet.SECONDARY_B).build());
		ps.put("p_border_color_co",  new PainterBorder.Builder().colorSet(ETypeColorSet.COMPLEMENT ).build());

		//# // Method 1: use constructor:
		//# ps.put("p_box_shadow"      ,  new PainterBoxShine     (null                       , ETypeColorShade.LIGHTER_2)                                         );
		//# Method 2: use builder
		ps.put("p_box_shadow"      ,  new PainterBoxShadow.Builder().shine().shade(ETypeColorShade.LIGHTER_2).build());
		ps.put("p_box_shadow_pr"   ,  new PainterBoxShadow.Builder().colorSet(ETypeColorSet.PRIMARY    ).build());
		ps.put("p_box_shadow_sa"   ,  new PainterBoxShadow.Builder().colorSet(ETypeColorSet.SECONDARY_A).build());
		ps.put("p_box_shadow_sb"   ,  new PainterBoxShadow.Builder().colorSet(ETypeColorSet.SECONDARY_B).build());
		ps.put("p_box_shadow_co"   ,  new PainterBoxShadow.Builder().colorSet(ETypeColorSet.COMPLEMENT ).build());

		//# // Method 1: use constructor:
		//# ps.put("p_text_on_dark_bckg", new PainterText         (null, null, null, null, "#FFFFFF", null, null, null, null));
		//# Method 2: use builder
		ps.put("p_text_on_dark_bckg", new PainterText.Builder().color("#FFFFFF").build());


		ps.put("base"             ,      new PainterCorners      (false)
		                            .add(new PainterMarginPadding("0px", "2px 4px 2px 4px"))
		                            .add(new PainterText  .Builder().fontWeight("normal").fontStyle("normal").fontFamily("trebuchet, sans-serif").build())
		                            .add(new PainterBorder.Builder().none().build())
		       );

		ps.put("bckg_color_pr"    ,      ps.get("p_bckg").add(ps.get("p_bckg_color_pr"))
		      );
		ps.put("bckg_color_sa"    ,      ps.get("p_bckg").add(ps.get("p_bckg_color_sa"))
		      );
		ps.put("bckg_color_sb"    ,      ps.get("p_bckg").add(ps.get("p_bckg_color_sb"))
		      );
		ps.put("bckg_color_co"    ,      ps.get("p_bckg").add(ps.get("p_bckg_color_co"))
		      );

		ps.put("title"            ,      ps.get("p_text_on_dark_bckg")
		                            .add(new PainterText.Builder().fontWeight("bold").fontStyle("normal").build())
		                            .add(p_border_1px_solid)
		                            .add(ps.get("p_bckg"        ))
		                            .add(ps.get("p_border_color"))
		                            .add(ps.get("p_box_shadow"   ))
		      );
		ps.put("title_leading"    ,      new PainterText.Builder().fontSize("12px").textAlign("center").build()
		                            .add(new PainterMarginPadding("0px", "0px"))
		                            .add(ps.get("p_bckg_color_pr"  ))
		                            .add(ps.get("p_border_color_pr"))
		                            .add(ps.get("p_box_shadow_pr"   ))
		      );
		ps.put("title_1"          ,      new PainterText.Builder().fontSize("14px").build()
		                            .add(ps.get("p_bckg_color_pr"  ))
		                            .add(ps.get("p_border_color_pr"))
		                            .add(ps.get("p_box_shadow_pr"   ))
		      );
		ps.put("title_2"          ,      new PainterText.Builder().fontSize("14px").build()
		                            .add(ps.get("p_bckg_color_sa"  ))
		                            .add(ps.get("p_border_color_sa"))
		                            .add(ps.get("p_box_shadow_sa"   ))
		      );
		ps.put("title_3"          ,      new PainterText.Builder().fontSize("12px").build()
		                            .add(ps.get("p_bckg_color_sb"  ))
		                            .add(ps.get("p_border_color_sb"))
		                            .add(ps.get("p_box_shadow_sb"   ))
		      );

		ps.put("content"          ,      p_border_1px_solid
		                            .add(ps.get("p_border_color"))
		      );
		ps.put("content_1"        ,      ps.get("p_border_color_pr")
		      );
		ps.put("content_2"        ,      ps.get("p_border_color_sa")
		      );
		ps.put("content_3"        ,      ps.get("p_border_color_sb")
		      );

		ps.put("button_active"    ,      new PainterPosition     ("relative", "2px 0px 0px 2px")
		      );
		ps.put("button"           ,      p_border_1px_solid
		                            .add(ps.get("p_border_color"))
		                            .add(ps.get("p_bckg"        ))
		                            .add(ps.get("p_box_shadow"  ))
		                            .add(ps.get("p_text_on_dark_bckg"))
		                            .add(new PainterText.Builder().fontSize("12px").fontWeight("bold").fontStyle("normal").build())
		                            .add(new PainterMarginPadding("0px", "5px 18px 5px 18px"))
		                            .add(new PainterPseudoClassActive(getPsb("button_active")))
		       );
		ps.put("button_hover"     ,      new PainterPosition     ("relative", "1px 0px 0px 1px")
		       );
		ps.put("button_1"         ,      ps.get("p_bckg_color_pr"  )
		                            .add(ps.get("p_border_color_pr"))
		                            .add(ps.get("p_box_shadow_pr"   ))
		                            .add(new PainterPseudoClassHover( //    new PainterBackground(ETypeColorSet.PRIMARY    , ETypeColorShade.BASE)
		                                                              ((PainterBackground) ps.get("p_bckg")).reverseGradient().add(ps.get("p_bckg_color_pr"))
		                                                             //.add(new PainterText         (null, null, null, null, ETypeColorSet.PRIMARY     , ETypeColorShade.BASE, "", null))
		                                                             .add(new PainterBorder       (null, null, null, ETypeColorSet.PRIMARY, ETypeColorShade.DARKER_2))
		                                                             .add(getPsb("button_hover"))
		                                                             )
		                                 ));
		ps.put("button_2"         ,      ps.get("p_bckg_color_sa")
		                            .add(ps.get("p_border_color_sa"))
		                            .add(ps.get("p_box_shadow_sa"))
		                            .add(new PainterPseudoClassHover( //    new PainterBackground( ETypeColorSet.SECONDARY_A, ETypeColorShade.BASE)
		                                                              ((PainterBackground) ps.get("p_bckg")).reverseGradient().add(ps.get("p_bckg_color_sa"))
		                                                             .add(new PainterBorder       (null, null, null, ETypeColorSet.SECONDARY_A, ETypeColorShade.DARKER_2))
		                                                             .add(getPsb("button_hover"))
		                                                             )
		                                 ));
		ps.put("button_3"         ,      ps.get("p_bckg_color_sb")
		                            .add(ps.get("p_border_color_sb"))
		                            .add(ps.get("p_box_shadow_sb"))
		                            .add(new PainterPseudoClassHover(//     new PainterBackground(ETypeColorSet.SECONDARY_B, ETypeColorShade.BASE)
		                                                              ((PainterBackground) ps.get("p_bckg")).reverseGradient().add(ps.get("p_bckg_color_sb"))
		                                                             .add(new PainterBorder       (null, null, null, ETypeColorSet.SECONDARY_B, ETypeColorShade.DARKER_2))
		                                                             .add(getPsb("button_hover"))
		                                                             )
		                                 ));

		ps.put("box_1"            ,      p_border_1px_solid
		                            .add(ps.get("p_border_color"))
		                            .add(ps.get("p_border_color_pr"))
		                            .add(ps.get("p_bckg_color_pr"))
		                            .add(new PainterBackground.Builder().fill().shade(ETypeColorShade.LIGHTER_2).build())
		                            .add(new PainterMarginPadding("0px", "0px"))
		       );
		ps.put("box_1_title"      ,      p_border_1px_solid
		                            .add(ps.get("p_border_color_pr"))
		                            .add(new PainterBorder.Builder().shade(ETypeColorShade.BASE).build())
		                            .add(ps.get("p_bckg"))
		                            .add(ps.get("p_bckg_color_pr"))
		                            .add(new PainterText.Builder().fontSize("12px").textAlign("center").build())
		                            .add(new PainterMarginPadding("0px", "0px"))
		                            .add(ps.get("p_box_shadow")).add(ps.get("p_box_shadow_pr"))
		       );

		ps.put("menu_bar"         ,      p_border_1px_solid
		                            .add(ps.get("p_border_color"))
		                            .add(ps.get("p_border_color_pr"))
		                            .add(ps.get("p_bckg"))
		                            .add(ps.get("p_bckg_color_pr"))
		                            .add(ps.get("p_box_shadow")).add(ps.get("p_box_shadow_pr"))
		      );
		ps.put("menu_bar_item"    ,      ps.get("p_text_on_dark_bckg")
		                            .add(new PainterText.Builder().fontSize("13px").fontWeight("bold").fontStyle("normal").color("#FFFFFF").build())
		                            .add(new PainterMarginPadding("0px", "4px"))
		                            .add(ps.get("p_bckg"))
		                            .add(ps.get("p_bckg_color_pr"))
		                            //.add(new PainterBackground   (ETypeColorSet.PRIMARY     , ETypeColorShade.BASE , bckg_image_url))
		      );
		ps.put("menu_bar_item_sub",      ps.get("menu_bar_item")
		                            .add(new PainterBackground.Builder().shade(ETypeColorShade.LIGHTER_1).backgroundImage(bckg_image_url).gradient(false).build())
		      );
		ps.put("menu_bar_item_hover",    new PainterText      .Builder().colorSet(ETypeColorSet.PRIMARY).shade(ETypeColorShade.BASE).build()
		                            .add(new PainterBackground.Builder().fill().colorSet(ETypeColorSet.PRIMARY).shade(ETypeColorShade.LIGHTER_2).build())
		      );
		// If we do not provide "menu_bar_item_active", the values from "menu_bar_item_hover" will apply (this is how the dojo menu works)
		//ps.put("menu_bar_item_active",  new PainterText      .Builder().colorSet(ETypeColorSet.PRIMARY).shade(ETypeColorShade.BASE).build()
		//                            .add(new PainterBackground.Builder().fill().colorSet(ETypeColorSet.PRIMARY).shade(ETypeColorShade.DARKER_1).build())
		//      );

		/*        */	if (debug_on) logger.debug(">>");
	}






}
