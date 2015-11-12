package PACKAGE.test.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import PACKAGE.test.helper.BasicAssert;
import PACKAGE.test.helper.MyOwnFunction;



public class TestBase extends ActivityInstrumentationTestCase2{

    protected Solo solo;
    protected MyOwnFunction MyFunc = null;
    protected BasicAssert basicAssert = null;
    protected Context context = null;


    public TestBase(Class activityClass)
    {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        MyFunc = new MyOwnFunction(solo);
        context = getInstrumentation().getContext();
        MyFunc.setUsername("张绍君的测试账户");
        MyFunc.setPassword("wm1234");
        MyFunc.setTelephone("13800444644");
        basicAssert = new BasicAssert(solo);
    }



    @Override
    public void tearDown() throws Exception {
        // Finish the Activity off (unless was never launched anyway)
        try {
            this.solo.finishOpenedActivities();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        getActivity().finish();
        super.tearDown();
    }

}
