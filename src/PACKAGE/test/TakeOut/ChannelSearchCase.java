package PACKAGE.test.TakeOut;

import PACKAGE.test.base.TestBase;
import android.widget.TextView;


public class ChannelSearchCase extends TestBase {
    public ChannelSearchCase()throws Exception
    {
        super(Class.forName("com.sankuai.meituan.activity.MainActivity"));
    }
    //测试频道内搜索
    public void testSearch()
    {

        solo.waitForText("外卖");
        solo.clickOnText("外卖");
        solo.sleep(1000);
        solo.clickOnScreen(990, 141);//点击搜索框
        solo.sleep(500);

        assertTrue("没有进入频道内搜索页面", solo.waitForView(solo.getView("img_poiList_search")));
        MyFunc.enterTextById("txt_poiList_search_keyword", "饭");//频道内输入“饭”
        solo.sleep(500);
        //MyFunc.clickById("com.sankuai.meituan:id/actionbar_txt");

        MyFunc.clickById("img_poiList_search");//"点击搜索"
        assertTrue("频道内搜索没有找到“饭”", solo.waitForText("饭", 2, 20000));
        solo.sleep(1000);

        solo.scrollDown();//滑动页面
        solo.sleep(500);
        solo.scrollDown();
        solo.sleep(500);
        solo.scrollToTop();
        solo.sleep(500);

        solo.clickOnScreen(885, 152);//清除“饭”
        MyFunc.enterTextById("txt_poiList_search_keyword", "菜");//频道内输入“菜”
        solo.sleep(500);
        MyFunc.clickById("img_poiList_search");//"点击搜索"
        assertTrue("频道内搜索没有找到“菜”", solo.waitForText("菜", 2, 20000));

        solo.sleep(500);
        MyFunc.clickById("up");
        solo.sleep(500);
    }

    //测试清除搜索记录
    public void testSearchHistoryClear()
    {
        solo.waitForText("外卖");
        solo.clickOnText("外卖");
        solo.sleep(1000);
        solo.clickOnScreen(990,141);//点击搜索框
        solo.sleep(500);
        assertTrue("没有清楚按钮", solo.waitForView(solo.getView("txt_clear")));

        solo.clickOnText(".*清除历史记录.*");
        solo.sleep(500);

        MyFunc.clickById("up");
        solo.sleep(500);

    }


    //附件热门餐厅搜索
    public void testHotWord()
    {
        solo.sleep(500);
        solo.waitForText("外卖");
        solo.clickOnText("外卖");
        solo.sleep(1000);
        solo.clickOnScreen(990,141);//点击搜索框
        solo.sleep(500);

        assertTrue("怎么回事?没有附近的热门餐厅推荐", solo.waitForView(solo.getView("hot_word", 0)));

        //solo.getView("img_poiList_search");
        TextView View=(TextView)solo.getView("hot_word");//获取热门餐厅名字
        MyFunc.clickById("hot_word");//
        solo.sleep(500);

        // assertEquals("丽华快餐",View.getText());
        assertTrue("没有进入" + View.getText(), solo.waitForText((String)View.getText(), 1, 2000));//判断是否进去了该餐厅

        solo.drag(145, 145, 1400, 500, 10);
        solo.sleep(500);
        solo.drag(145, 145, 1400, 500, 10);
        solo.sleep(500);

    }


}
