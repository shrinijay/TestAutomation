package sprint3.assessment.util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class fileUploadUsingRobotClass {

    public static void fileUpload() throws AWTException, InterruptedException {

        String usrDir = System.getProperty("user.dir");
        System.out.println("user dir "+usrDir);
        String filePath = usrDir+"/data/TxtToUpload.txt";
        System.out.println(usrDir+"filepath "+filePath);

        StringSelection selectFile = new StringSelection(usrDir+"\\data\\TxtToUpload.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectFile,null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


    }

}
