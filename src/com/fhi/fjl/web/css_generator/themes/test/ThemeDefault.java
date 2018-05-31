package com.fhi.fjl.web.css_generator.themes.test;

import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;
import com.fhi.fjl.web.css_generator.painters.PainterBackground;
import com.fhi.fjl.web.css_generator.painters.PainterStack;
import com.fhi.fjl.web.css_generator.themes.Theme;


public class ThemeDefault extends Theme
{
	// ==========================================================================
	// LOGGING
	// ==========================================================================

	// Redefine preset fields :
	// See base parent class
	{
		logger  = LogFactory.getLog("com.fhi.fwl.core.skin.themes");
		slogger = logger;
		debugOn = true
		                       && logger.isDebugEnabled();
	}


	// ==========================================================================
	// BUSINESS
	// ==========================================================================

	/**
	 * Use the factory to create themes.
	 */
	public ThemeDefault()
	{	// FORBIDDEN - Use the factory to create themes.
	}

	@Override
	public String getName()
	{	return "default";
	}

	@Override
	protected void setPainterStacksBase()
	{
		painterStacksBase.put("base" , new PainterStack());
		painterStacksBase.put("empty", new PainterStack());
	}


	@Override
	protected void setPainterStackForRules()
	{	/*        */	boolean debug_on = true && debugOn;
		/*        */	if (debug_on) logger.debug("<<");



		// For certain rules: overrule that setting:
		set(ECssRule.BCKG_PR_D2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.PRIMARY    , ETypeColorShade.DARKER_2 )));
		set(ECssRule.BCKG_PR_D1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.PRIMARY    , ETypeColorShade.DARKER_1 )));
		set(ECssRule.BCKG_PR            .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.PRIMARY    , ETypeColorShade.BASE     )));
		set(ECssRule.BCKG_PR_L1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.PRIMARY    , ETypeColorShade.LIGHTER_1)));
		set(ECssRule.BCKG_PR_L2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.PRIMARY    , ETypeColorShade.LIGHTER_2)));
		set(ECssRule.BCKG_SA_D2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_A, ETypeColorShade.DARKER_2 )));
		set(ECssRule.BCKG_SA_D1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_A, ETypeColorShade.DARKER_1 )));
		set(ECssRule.BCKG_SA            .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_A, ETypeColorShade.BASE     )));
		set(ECssRule.BCKG_SA_L1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_A, ETypeColorShade.LIGHTER_1)));
		set(ECssRule.BCKG_SA_L2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_A, ETypeColorShade.LIGHTER_2)));
		set(ECssRule.BCKG_SB_D2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_B, ETypeColorShade.DARKER_2 )));
		set(ECssRule.BCKG_SB_D1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_B, ETypeColorShade.DARKER_1 )));
		set(ECssRule.BCKG_SB            .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_B, ETypeColorShade.BASE     )));
		set(ECssRule.BCKG_SB_L1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_B, ETypeColorShade.LIGHTER_1)));
		set(ECssRule.BCKG_SB_L2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.SECONDARY_B, ETypeColorShade.LIGHTER_2)));
		set(ECssRule.BCKG_CO_D2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.COMPLEMENT , ETypeColorShade.DARKER_2 )));
		set(ECssRule.BCKG_CO_D1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.COMPLEMENT , ETypeColorShade.DARKER_1 )));
		set(ECssRule.BCKG_CO            .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.COMPLEMENT , ETypeColorShade.BASE     )));
		set(ECssRule.BCKG_CO_L1         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.COMPLEMENT , ETypeColorShade.LIGHTER_1)));
		set(ECssRule.BCKG_CO_L2         .getValue(), new PainterStack().add( new PainterBackground(ETypeColorSet.COMPLEMENT , ETypeColorShade.LIGHTER_2)));

		set(ECssRule.LEADING_TITLE      .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("title"  )).add(getPsb("title_leading"    )));//.add(getPsb("bckg_color_pr"  )).add(getPsb("border_color_pr"))   );
		set(ECssRule.LEADING_CONTENT    .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("content")).add(getPsb("content_leading"  )));//.add(getPsb("bckg_color_pr"  )).add(getPsb("border_color_pr"))   );

		set(ECssRule.SECTION_1_TITLE    .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("title"  )).add(getPsb("title_1"    )) ); //.add(getPsb("bckg_color_pr"  )).add(getPsb("border_color_pr"))   );
		set(ECssRule.SECTION_1_CONTENT  .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("content")).add(getPsb("content_1"  )) ); //.add(getPsb("border_color_pr"))                                  );
		set(ECssRule.SECTION_2_TITLE    .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("title"  )).add(getPsb("title_2"    )) ); //.add(getPsb("bckg_color_sa"  )).add(getPsb("border_color_sa"))   );
		set(ECssRule.SECTION_2_CONTENT  .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("content")).add(getPsb("content_2"  )) ); //.add(getPsb("border_color_sa"))                                  );
		set(ECssRule.SECTION_3_TITLE    .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("title"  )).add(getPsb("title_3"    )) ); //.add(getPsb("bckg_color_sb"  )).add(getPsb("border_color_sb"))   );
		set(ECssRule.SECTION_3_CONTENT  .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("content")).add(getPsb("content_3"  )) ); //.add(getPsb("border_color_sb"))                                  );
		set(ECssRule.BUTTON_1           .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("button" )).add(getPsb("button_1"   )) ); //.add(getPsb("bckg_color_pr"  )).add(getPsb("border_color_pr"))   );
		set(ECssRule.BUTTON_2           .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("button" )).add(getPsb("button_2"   )) ); //.add(getPsb("bckg_color_sa"  )).add(getPsb("border_color_sa"))   );
		set(ECssRule.BUTTON_3           .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("button" )).add(getPsb("button_3"   )) ); //.add(getPsb("bckg_color_sb"  )).add(getPsb("border_color_sb"))   );

		set(ECssRule.BOX_1              .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("box"    )).add(getPsb("box_1"      ))   );
		set(ECssRule.BOX_1_TITLE        .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("title"  )).add(getPsb("box_title"  )).add(getPsb("box_1_title"  ))   );
		set(ECssRule.BOX_1_CONTENT      .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("content")).add(getPsb("box_content")).add(getPsb("box_1_content"))   );

		set(ECssRule.MENU_BAR           .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("menu_bar"             )));
		set(ECssRule.MENU_BAR_ITEM      .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("menu_bar_item"        )));
		set(ECssRule.MENU_BAR_ITEM_SUB  .getValue(), new PainterStack().add(getPsb("base")).add(getPsb("menu_bar_item_sub"    )));
		set(ECssRule.MENU_BAR_ITEM_HOVER.getValue(), new PainterStack().add(getPsb("menu_bar_item_hover"   )));
		set(ECssRule.MENU_BAR_ITEM_ACTIVE.getValue(),new PainterStack().add(getPsb("menu_bar_item_active"  )));

		/*        */	if (debug_on) logger.debug(">>");
		/*        */	if (debug_on) logger.debug(">>");
	}



}
