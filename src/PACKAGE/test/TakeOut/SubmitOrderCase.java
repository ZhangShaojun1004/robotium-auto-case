package PACKAGE.test.TakeOut;

import PACKAGE.test.base.TestBase;
import android.widget.TextView;

/**
 * Created by zsj on 15/8/24.
 */
public class SubmitOrderCase extends TestBase {
    public SubmitOrderCase() throws Exception{
        super(Class.forName("com.sankuai.meituan.activity.MainActivity"));
    }

    public void  testOrder()
    {
        solo.waitForView(solo.getView("mine_pic"));
        MyFunc.clickById("mine_pic");//进入我的页面
        solo.sleep(500);
        if(!MyFunc.isLogin())//判断是否已登录
        {
            MyFunc.login();
        }
        solo.sleep(500);
        MyFunc.clickById("group");//切换到团购首页

        solo.waitForText("外卖");
        solo.clickOnText("外卖");//进入外卖频道

        /*MyFunc.clickById("actionbar_txt");//切换地址到昌都市
        solo.enterText(0, "昌都");
        solo.clickOnText(".*昌都市.*", 2);

        TextView View=(TextView)solo.getView("actionbar_tx");//切换地址成功则title展示地址名字
        assertFalse("没有切换到 "+View+" 地址",solo.waitForText((String)View.getText()));

        */
    }

}
