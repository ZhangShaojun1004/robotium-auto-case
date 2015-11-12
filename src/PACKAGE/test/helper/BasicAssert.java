package PACKAGE.test.helper;

import android.view.View;
import com.robotium.solo.Solo;
import junit.framework.Assert;

/**
 * Created by guoshun on 15/3/12.
 */
public class BasicAssert {
     private Solo solo;
     public BasicAssert(Solo so){
         this.solo = so;
     }

    public void assertLocateBar(){
        View locate = solo.getView("locate");
        Assert.assertTrue("显示出定位条",solo.waitForView(locate));
    }

    public void assertUserName(String name){
        View mine = solo.getView("mine");
        solo.clickOnView(mine);
        solo.sleep(1000);
        Assert.assertTrue("没有登录成功", solo.searchText(name));
    }

}
