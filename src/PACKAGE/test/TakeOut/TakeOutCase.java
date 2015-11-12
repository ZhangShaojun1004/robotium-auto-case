package PACKAGE.test.TakeOut;

import PACKAGE.test.base.TestBase;


public class TakeOutCase extends TestBase {
	public TakeOutCase() throws Exception{
        super(Class.forName("com.sankuai.meituan.activity.MainActivity"));
	}



//
    public void testBeginCity(){
        if(solo.searchText("热门城市")) {
            solo.clickOnText("北京");
            assertTrue(solo.searchText("美食"));
        }
        assertTrue(solo.searchText("我的"));
    }

//切换选择城市的用例
     public void testChangeCity(){
    	 MyFunc.clickById("city_button");
    	 assertTrue("城区选择界面", solo.searchText("全城"));

    	 solo.clickOnText("切换");
    	 solo.clickOnText("杭州");
    	 assertTrue("城市已经选为杭州", solo.searchText("杭州"));

    	 MyFunc.clickById("city_button");
    	 solo.clickOnText("切换");
    	 solo.clickOnText("北京");
    	 assertTrue("城市已经选为北京", solo.searchText("北京"));
     }

//城市选择搜索框的用例
     public void testCitySearch(){
         MyFunc.clickById("city_button");
         assertTrue("城区选择界面", solo.searchText("全城"));

         solo.clickOnText("切换");
         assertTrue(solo.searchText("热门城市"));
         solo.scrollToTop();
         solo.sleep(2000);
         solo.scrollToTop();

         //测试城市搜索框功能和自动提示功能
         solo.enterText(0,"zhen");
         solo.sleep(3000);
         assertTrue(solo.searchText("郑州",1,false,true));
         solo.clickOnText("郑州");
         MyFunc.clickById("city_button");
         solo.clickOnText("切换");
         solo.clickOnText("北京");
         assertTrue("城市已经选为北京", solo.searchText("北京"));

     }

}
