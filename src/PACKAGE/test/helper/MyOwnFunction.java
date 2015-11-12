package PACKAGE.test.helper;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;


import junit.framework.Assert;

public class MyOwnFunction{
	private Solo solo;
	private String username;
	private String password;
	private String telephone;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public MyOwnFunction(Solo so){
        solo=so;
	}
	
//在LogCat中打log
    public void Log(String log){
	Log.d("testLogView",log);
	}

//根据控件String id找到View
    public void clickById (String id){
         View view=solo.getView(id);
         Assert.assertTrue(solo.waitForView(view));
         solo.clickOnView(view);
    }

    //根据控件id找到控件输入内容
    public void enterTextById (String id,String str){
        EditText Text=(EditText)solo.getView(id);
        Assert.assertTrue(solo.waitForView(Text));
        solo.enterText(Text,str);
    }
     

//品类筛选
     public void clickCategory(String category_1,String category_2){  
         clickById("category");
         Assert.assertTrue(solo.searchText(category_1));
         solo.clickOnText(category_1);
         solo.sleep(1000);
         Assert.assertTrue(solo.searchText(category_2));
         solo.clickOnText(category_2);
     }

     public void clickCategory(String category_1){
         clickById("category");
         solo.waitForText(category_1);
         solo.clickOnText(category_1);
     }

//商区筛选
     public void clickArea(String area_1,String area_2){
    	 clickById("area");
         Assert.assertTrue(solo.waitForText(area_1, 1, 5000, false, true));
         solo.clickOnText(area_1);
         Assert.assertTrue(solo.searchText(area_2));
         solo.clickOnText(area_2);
     }

//排序
     public void clickSort(String sortKey){
    	 clickById("sort");
         solo.waitForText(sortKey);
         solo.clickOnText(sortKey);
     }

     

 //在我的页面判断是否退出
     public void log_out(){
         if(isLogin()) {
             solo.scrollToTop();
             solo.sleep(1000);
             clickById("username");
             solo.waitForActivity("com.sankuai.meituan.user.UserAdminActivity", 4000);
             solo.clickOnText("退出账户");
             solo.waitForText("退出后您将不能查看美团券，确定退出吗？");
             solo.clickOnButton("退出");
         }
     }

 //判断是否登录了
     public Boolean isLogin(){
         //solo.scrollToTop();
         if(solo.searchText(".*请点击登录.*",true)){
             return false;
         }
         else{
             return true;
         }

     }

     public void login(){
    	 solo.waitForActivity("com.sankuai.meituan.user.MainActivity", 4000);
    	 solo.clickOnText(".*点击登录.*");
    	 solo.enterText(0, telephone);
    	 solo.enterText(1, password);
         solo.sleep(1000);
    	 clickById("login");
     }

     public void  getInToCategory(String category,String key){
     	solo.clickLongOnText(category);
     	solo.waitForText(key);
     	solo.sleep(2000);
     }
     
//打印当前页面所有的View
     public void printView(View view){
    	 ArrayList<View> container = solo.getViews(view);
    	 for(int i=0;i<container.size();i++){
    		 View tmp = container.get(i);
    		 if(tmp instanceof TextView)
    		      Log("TextView" + ((TextView)tmp).getText().toString() );
    		 else{
    			  Log(tmp.toString());
    		 }
    	 }
     }

// 比较价钱
     public void priceLow(){
         solo.sleep(2000);
         TextView textView_1 = (TextView) solo.getView("price",0);
         TextView textView_2 = (TextView) solo.getView("price",1);
         Double price_1 = Double.valueOf(textView_1.getText().toString());
         Double price_2 = Double.valueOf(textView_2.getText().toString());
         Assert.assertTrue(price_1<=price_2);
     }

//距离排序 这个地方可以扩展比较n个 但是这里只比较2个
   public void distanceDescSort(int i){
        solo.sleep(2000);
        ArrayList<Double> distanceArray = new ArrayList<Double>();


        for(int j=0 ; j <= i ; j++ ) {
            //solo.scrollDownList(6);
            TextView distanceContainer = (TextView) solo.getView("distance",j);
            String distance = distanceContainer.getText().toString().trim();
            Log(distance + distanceArray.size());
            if(distance.contains("m") && !distance.contains("km") &&!distance.contains("<")){
                distanceArray.add(Double.valueOf(distance.split("m")[0]));
            }
            if(distance.contains("km")){
                distanceArray.add((Double.valueOf(distance.split("km")[0])) * 1000);
            }
            if(distance.contains("m") && distance.contains("<")){
                distanceArray.add(Double.valueOf(distance.split("m")[0].split("<")[1]));
            }
        }

        for(int k = 0; k<distanceArray.size()-1;k++){
            Log(distanceArray.get(k).toString());
        }
        for(int k = 0; k<distanceArray.size()-1;k++){
            Assert.assertTrue("距离应该优先",distanceArray.get(k) <= distanceArray.get(k+1));
        }


    }

//评价最高排序判断
    public void ratingHighSort(int i){
        solo.sleep(2000);
        ArrayList<Double> rattingArray = new ArrayList<Double>();
        for(int j=0 ; j <= i ; j++ ) {
            TextView distanceContainer = (TextView) solo.getView("rating_text", j);
            String rating = distanceContainer.getText().toString().trim();
            rattingArray.add(Double.valueOf(rating.split("分")[0]));
            Log(rating);
        }
        for(int k = 0; k<rattingArray.size()-1;k++){
            Assert.assertTrue("评分应该由高到低",rattingArray.get(k)>=rattingArray.get(k+1));
        }

    }

//列表页ActionBar测试
    public void actionBarTest(){
        View mapButton = solo.getView("action_map");
        Assert.assertTrue("等待刷出地图图标", solo.waitForView(mapButton));

        View searchButton = solo.getView("action_search");
        Assert.assertTrue("等待刷出搜索图标",solo.waitForView(searchButton));

        //地图模式测试
        clickById("action_map");
        Assert.assertTrue(solo.searchText("查看周边团购", 1, false));
        solo.goBack();

        //搜索测试
        clickById("action_search");
        Assert.assertTrue("点击button进入搜索页面", solo.searchText("热门搜索", 1, false));
    }





// 随机数
    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

// 随机点  randomClick
    public void randomClick(ArrayList<View> views) {
        ArrayList<View> viewsFilter = new ArrayList<View>();
        for (int j = 0; j < views.size(); j++) {
            View tmp = views.get(j);
            if (tmp.isClickable() && tmp.isShown() && tmp instanceof ViewGroup) {
                viewsFilter.add(tmp);
            }
        }
        if (viewsFilter.isEmpty()) {
            Log("empty");
            solo.sendKey(KeyEvent.KEYCODE_BACK);
        } else {
            int total = viewsFilter.size();
            Log("total" + total);
            int randomNum = randInt(1, total);
            View clickView = viewsFilter.get(randomNum - 1);
            Log("total" + clickView.getClass().toString());
            solo.clickOnView(clickView);
        }

    }

//wait 网络等待
    public void waitNet(){
       solo.searchText("努力加载中...",1,false,true);
    }

//


    	 
     
    
}
