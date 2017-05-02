package tests_Chrome;


import static utilities.SeleniumDriver.*;
import static utilities.SW_Constants.*;
import static utilities.SystemUtil_SW.*;

import org.junit.*;

public class SW_Purchase_Ticket
{
	@BeforeClass
	//Open Browser
	public static void setUpBeforeRun() throws Exception {
		initializeDriverChrome(SW_URL);
	}
	
	@Test
	public void Purchase_Ticket() throws Exception {
		/*PAGE 1
		Enter 'DEPART' and 'ARRIVE' locations.
		Enter 'DEPART DATE' and 'RETURN DATE'
		Verify 'Dollars' Radio button is selected by default.
		Verify 'Round Trip' radio button is selected by default
		Verify 'ADULTS' field is selected as 1. 
		Click 'Search' button.*/
		setTextField(DEPART, DEPART_CITY);
		setTextField(ARRIVE, ARRIVAL_CITY);
		setTextField(DEPART_DATE, D_DATE);
		setTextField(RETURN_DATE, R_DATE);
		isRadioButtonSelected(DOLLARS_RADIO_BUTTON);
		isRadioButtonSelected(ROUND_TRIP_RADIO_BUTTON);
		verifyExists(ADULTS_COUNT, "1");
		findAndClick(SEARCH_BUTTON);
		
		/*PAGE2
		VERIFY 'IAD' and 'LAX' exist in 'From:' and 'To:' fields. 
		Sort by 'Travel Time'. 
		Select 'Departing' and 'Arriving' flights. 
		Click 'CONTINUE'*/
		verifyExists(FROM, "IAD");
		verifyExists(TO, "LAX");
		findAndClick(TRAVEL_TIME);
		findAndClick(SELECT_FLIGHT_OUTBOUND);
		findAndClick(SELECT_FLIGHT_INBOUND);
		findAndClick(CONTINUE_BUTTON);
		
		//PAGE3
		findAndClick(CONTINUE_BTN);
		
		/*PAGE4
		 Enter 'First Name' and 'Last Name'
		 Select 'Date of Birth', 'Birth Day', 'Birth Year' and Gender
	     Enter Email.
	     Verify 'Departing' and 'Arriving' cities.
	     Select 'Credit Card'.
	     Click 'Purchase'.
	     Verify ERROR messages match messages in XML. 
		 */
		setTextField(FIRST_NAME, FNAME);
		setTextField(LAST_NAME, LNAME);
		clickDropDown(DOB, DOB_DATE);
		clickDropDown(DAY, BIRTH_DAY);
		clickDropDown(YEAR, BIRTH_YEAR);	
		clickDropDown(GENDER, "Female");	
		setTextField(EMAIL, EMAIL_ID);
		verifyTextContains(DEPART_VERIFY, DEPART_CITY);
		verifyTextContains(ARRIVE_VERIFY, ARRIVAL_CITY );
		clickDropDown(CREDIT_CARD, CREDIT_CARD_TYPE);	
		findAndClick(SUBMIT_BTN);
		verifyTextContains(MSG_AREA1, getXmlMsg(1));
		verifyTextContains(MSG_AREA2, getXmlMsg(3));
	}

	@AfterClass
	public static void tearDownAfterRun() throws Exception {
		closeDriver();
	}
}

