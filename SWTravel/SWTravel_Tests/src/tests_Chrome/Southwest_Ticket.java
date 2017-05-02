package tests_Chrome;


import static utilities.SeleniumDriver.*;
import static utilities.SW_Constants.*;
import static utilities.SystemUtil_SW.*;

import org.junit.*;

public class Southwest_Ticket
{
	@BeforeClass
	//Open Browser
	public static void setUpBeforeRun() throws Exception {
		initializeDriverChrome(SW_URL);
	}
	
	@Before
	public void setupBeforeEachTest() throws Exception {	
		refreshPage();
	}

	@Test
	/* Enter all fields. ARRIVE field is blank. 
	 * Click SEARCH button.
	 * Verify error message.  
	 */
	public void BLANK_FIELD() throws Exception {
		setTextField(DEPART, DEPART_CITY);
		setTextField(ARRIVE, ARRIVAL_CITY_BLANK);
		setTextField(DEPART_DATE, D_DATE);
		setTextField(RETURN_DATE, R_DATE);
		findAndClick(SEARCH_BUTTON);
		verifyTextContains(MSG_AREA, getXmlMsg(14));
	}
	
	@Test
	/* Verify text header for DEPART field equals DEPART.
	 * Verify ARRIVE text field is displayed and not grayed out. 
	 */
	public void VERIFY_FIELDS_EXIST() throws Exception {
		verifyTextEquals(DEPART_FIELD, DEPART_FIELD_NAME);
		isTextFieldDisplayed (ARRIVE);
	}
	
	@Ignore
	@Test
	/* Ignores test - used @Ignore Annotation
	 */
	public void IGNORE_BLANK_FIELD_TEST() throws Exception {
		setTextField(DEPART, DEPART_CITY);
		setTextField(ARRIVE, ARRIVAL_CITY_BLANK);
		setTextField(DEPART_DATE, D_DATE);
		setTextField(RETURN_DATE, R_DATE);
		findAndClick(SEARCH_BUTTON);
		verifyTextContains(MSG_AREA, getXmlMsg(14));
	}
	
	
	@AfterClass
	public static void tearDownAfterRun() throws Exception {
		closeDriver();
	}
}

