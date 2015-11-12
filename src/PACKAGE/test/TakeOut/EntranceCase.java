package PACKAGE.test.TakeOut;

import PACKAGE.test.base.TestBase;

import android.widget.*;
import PACKAGE.test.base.TestBase;
import junit.framework.Assert;


public class EntranceCase extends TestBase {
    public EntranceCase() throws Exception {
        super(Class.forName("com.sankuai.meituan.activity.MainActivity"));
    }


    //测试外卖入口是否可用
    public void test_TakeOutSearch() {

     MyFunc.clickById("search_edit");
        solo.sleep(500);
        MyFunc.enterTextById("search_edit", "waimai");
        solo.sleep(500);
        MyFunc.clickById("search");
        Assert.assertTrue("没有找到waimai入口", solo.waitForView(solo.getView("extention_img")));
        MyFunc.clickById("up");
        solo.sleep(1000);

        solo.clearEditText(0);
        solo.sleep(500);
        MyFunc.enterTextById("search_edit", "外卖");
        solo.sleep(500);
        MyFunc.clickById("search");
        Assert.assertTrue("没有找到外卖入口", solo.waitForView(solo.getView("extention_img")));
        MyFunc.clickById("up");

        solo.clearEditText(0);
        solo.sleep(1000);
        MyFunc.enterTextById("search_edit", "美团外卖");
        solo.sleep(500);
        MyFunc.clickById("search");
        Assert.assertTrue("没有找到美团外卖入口", solo.waitForView(solo.getView("extention_img")));
        MyFunc.clickById("extention_img");


         solo.sleep(1000);
         solo.drag(145, 145, 1400, 500, 10);
         solo.sleep(1000);
         solo.drag(145, 145, 1400, 500, 10);
         solo.sleep(1000);
         solo.drag(145, 145, 1400, 500, 10);
         solo.sleep(1000);

    }
}