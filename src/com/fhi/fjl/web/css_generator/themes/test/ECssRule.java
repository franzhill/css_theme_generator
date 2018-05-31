package com.fhi.fjl.web.css_generator.themes.test;


/**
 * Enumeration of all css rules
 *
 * @author u103860
 * @since 15 nov. 2012
 *
 */
public enum ECssRule
{
	GENERIC             ( ".fwl__generic"                                           ),
	BCKG_1              ( ".fwl__bckg_1"                                            ),
	BCKG_2              ( ".fwl__bckg_2"                                            ),
	BCKG_3              ( ".fwl__bckg_3"                                            ),


	BCKG_PR_D2          ( ".fwl__bckg_pr_d2"                                        ),
	BCKG_PR_D1          ( ".fwl__bckg_pr_d1"                                        ),
	BCKG_PR             ( ".fwl__bckg_pr"                                           ),
	BCKG_PR_L1          ( ".fwl__bckg_pr_l1"                                        ),
	BCKG_PR_L2          ( ".fwl__bckg_pr_l2"                                        ),
	BCKG_SA_D2          ( ".fwl__bckg_sa_d2"                                        ),
	BCKG_SA_D1          ( ".fwl__bckg_sa_d1"                                        ),
	BCKG_SA             ( ".fwl__bckg_sa"                                           ),
	BCKG_SA_L1          ( ".fwl__bckg_sa_l1"                                        ),
	BCKG_SA_L2          ( ".fwl__bckg_sa_l2"                                        ),
	BCKG_SB_D2          ( ".fwl__bckg_sb_d2"                                        ),
	BCKG_SB_D1          ( ".fwl__bckg_sb_d1"                                        ),
	BCKG_SB             ( ".fwl__bckg_sb"                                           ),
	BCKG_SB_L1          ( ".fwl__bckg_sb_l1"                                        ),
	BCKG_SB_L2          ( ".fwl__bckg_sb_l2"                                        ),
	BCKG_CO_D2          ( ".fwl__bckg_co_d2"                                        ),
	BCKG_CO_D1          ( ".fwl__bckg_co_d1"                                        ),
	BCKG_CO             ( ".fwl__bckg_co"                                           ),
	BCKG_CO_L1          ( ".fwl__bckg_co_l1"                                        ),
	BCKG_CO_L2          ( ".fwl__bckg_co_l2"                                        ),


	LEADING_            ( ".fwl__leading                       , .fwl__l"           ),
	LEADING_CONTAINER   ( ".fwl__leading > .fwl__container     , .fwl__l_ct"        ),
	LEADING_TITLE       ( ".fwl__leading > .fwl__title         , .fwl__l_t"         ),
	LEADING_CONTENT     ( ".fwl__leading > .fwl__content       , .fwl__l_c"         ),


	SECTION_1           ( ".fwl__section_1                       , .fwl__s1"        ),
	SECTION_1_CONTAINER ( ".fwl__section_1 > .fwl__container     , .fwl__s1_ct"     ),
	SECTION_1_TITLE     ( ".fwl__section_1 > .fwl__title         , .fwl__s1_t"      ),
	SECTION_1_CONTENT   ( ".fwl__section_1 > .fwl__content       , .fwl__s1_c"      ),
	SECTION_2           ( ".fwl__section_2                       , .fwl__s2"        ),
	SECTION_2_CONTAINER ( ".fwl__section_2 > .fwl__container     , .fwl__s2_ct"     ),
	SECTION_2_TITLE     ( ".fwl__section_2 > .fwl__title         , .fwl__s2_t"      ),
	SECTION_2_CONTENT   ( ".fwl__section_2 > .fwl__content       , .fwl__s2_c"      ),
	SECTION_3           ( ".fwl__section_3                       , .fwl__s3"        ),
	SECTION_3_CONTAINER ( ".fwl__section_3 > .fwl__container     , .fwl__s3_ct"     ),
	SECTION_3_TITLE     ( ".fwl__section_3 > .fwl__title         , .fwl__s3_t"      ),
	SECTION_3_CONTENT   ( ".fwl__section_3 > .fwl__content       , .fwl__s3_c"      ),

	BUTTON_1            ( ".fwl__button_1                        , .fwl__b1"        ),
	BUTTON_2            ( ".fwl__button_2                        , .fwl__b2"        ),
	BUTTON_3            ( ".fwl__button_3                        , .fwl__b3"        ),

	BOX                 ( ".fwl__box"                                               ),
	BOX_CONTAINER       ( ".fwl__box_container"                                     ),
	BOX_TITLE           ( ".fwl__box_title"                                         ),
	BOX_CONTENT         ( ".fwl__box_content"                                       ),

	BOX_1               ( ".fwl__box_1                           , .fwl__bo1"       ),
	BOX_1_CONTAINER     ( ".fwl__box_1 > .fwl__container         , .fwl__bo1_ct"    ),
	BOX_1_TITLE         ( ".fwl__box_1 > .fwl__title             , .fwl__bo1_t"     ),
	BOX_1_CONTENT       ( ".fwl__box_1 > .fwl__content           , .fwl__bo1_c"     ),

	// Here we will be redefining dojo/dijit elemnts' styling:

	MENU_BAR            ( ".fwl .dijitMenuBar"                                       ),
	MENU_BAR_ITEM       ( ".fwl .dijitMenuBar           .dijitMenuItem                      , .fwl .dijitMenuBar           .dijitMenuItem                       a"  ),
	MENU_BAR_ITEM_SUB   ( ".fwl .dijitMenuPopup > table .dijitMenuItem                      , .fwl .dijitMenuPopup > table .dijitMenuItem                       a"  ),

	MENU_BAR_ITEM_HOVER ( ".fwl .dijitMenuBar           .dijitMenuItem.dijitMenuItemHover   , .fwl .dijitMenuBar           .dijitMenuItem.dijitMenuItemHover    a, " +
	                      ".fwl .dijitMenuBar           .dijitMenuItem.dijitMenuItemSelected, .fwl .dijitMenuBar           .dijitMenuItem.dijitMenuItemSelected a, " +
	                      ".fwl .dijitMenuPopup > table .dijitMenuItem.dijitMenuItemHover   , .fwl .dijitMenuPopup > table .dijitMenuItem.dijitMenuItemHover    a, " +
	                      ".fwl .dijitMenuPopup > table .dijitMenuItem.dijitMenuItemSelected, .fwl .dijitMenuPopup > table .dijitMenuItem.dijitMenuItemSelected a  " ),
	MENU_BAR_ITEM_ACTIVE( ".fwl .dijitMenuBar           .dijitMenuItem.dijitMenuItemActive  , .fwl .dijitMenuBar           .dijitMenuItem.dijitMenuItemActive   a, " +
	                      ".fwl .dijitMenuPopup > table .dijitMenuItem.dijitMenuItemActive  , .fwl .dijitMenuPopup > table .dijitMenuItem.dijitMenuItemActive   a  " );

	// The part of the selector : ".fwl .dijitMenuPopup > table " is to allow to make a distinction between a popup submenu and a popup select field.
	// The popup submenu will look like this:
	//    <div class="dijitPopup dijitMenuPopup"   ... >
	//       <table class="dijit dijitMenu dijitReset dijitMenuTable dijitMenuPassive"  ...>
	// whereas the popup select field will look like:
	//    <div class="dijitPopup dijitMenuPopup" ...>
	//       <div style="overflow: hidden; top: 0px; width: 163px; visibility: visible; " class="dijit dijitMenu dijitReset dijitSelectMenu dijitMenuPassive" ...>
	//          <table class="dijitReset dijitMenuTable" role="listbox"
	//


	/**
	 * Value for that type, in database
	 */
	private final String value;
	public String getValue() {	return value; }

	// This constructor is used by Java to build the type
	ECssRule(String value)
	{	this.value      = value;
	}
}