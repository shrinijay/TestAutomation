package sprint3.assessment.TestCases;

import org.testng.annotations.Test;
import sprint3.assessment.base.BaseClass;
import sprint3.assessment.pages.Login;

public class Assessment extends BaseClass {

    @Test
    public void enterFlow() throws Exception {
        new Login(driver).enterUserName("bootcamp_2024@testleaf.com").enterPwd("Bootcamp@123").clickLogInButton().clickOnToggleMenu().clickViewAll().selectServiceConsole().clickOnArrowForTheLatestModifiedItem().clickOnPublicLink().clickOnCreateLink().clickOnArrowForTheLatestModifiedItem().selectDownloadOption().clickOnArrowForTheLatestModifiedItem().clickOnShare().enterDetailsInSharePopUp().removeBootcampTestleafUser().enterAnotherUserForSharing();

       // new Login(driver).enterUserName("bootcamp_2024@testleaf.com").enterPwd("Bootcamp@123").clickLogInButton().clickOnToggleMenu().clickViewAll().selectServiceConsole().clickOnArrowForTheLatestModifiedItem().selectDownloadOption();
    }
}
