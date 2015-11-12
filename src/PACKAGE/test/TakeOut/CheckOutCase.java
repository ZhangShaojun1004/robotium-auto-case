package PACKAGE.test.TakeOut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;


public class CheckOutCase extends ActivityInstrumentationTestCase2 {

    protected Solo solo;
    protected PACKAGE.test.helper.MyOwnFunction op = null;
    protected PACKAGE.test.helper.BasicAssert basicAssert = null;
    protected Context context = null;


    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());

       /* Uri uri = Uri.parse("imeituan://www.meituan.com/takeout/secondpage?sort_type=0&sort_name=综合排序&filter_type=50000&filter_name=全部商家&category_type=0&category_name=全部品类&sub_category_type=0&sub_category_name=全部");
        Intent intent = new Intent(Intent.ACTION_VIEW , uri);
        setActivityIntent(intent);
         getActivity();
        //startActivity(intent);*/
    }



    @Override
    public void tearDown() throws Exception {
        // Finish the Activity off (unless was never launched anyway)
        try {
            this.solo.finishOpenedActivities();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getActivity().finish();
        super.tearDown();
    }

    public CheckOutCase() throws Exception {
       super(Class.forName("com.sankuai.meituan.activity.MainActivity"));
                             //com.meituan.android.takeout.library.ui.TakeoutActivity


    }


    //测试外卖入口是否可用
    public void testhehe() {

        solo.sleep(2000);
        solo.drag(145, 145, 500, 700, 10);
        solo.sleep(2000);
        solo.drag(500, 1690, 500, 500,1);
        solo.sleep(2000);






    }
}
