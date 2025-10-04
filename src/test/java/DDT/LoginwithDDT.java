package DDT;

import Utils.UtilExcel;
import org.testng.annotations.Test;

public class LoginwithDDT {
    @Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
    public void testLoginData(String username,String password){
        System.out.println("UserName - "+ username);
        System.out.println("Password - "+ password);
        // Rest Assured code
    }
}

