package com.example.mydb;
import java.util.ArrayList;

//这个静态公共类主要是存储收藏的菜品，用ArrayList来动态添加数据，最后通过result返回到界面中
public class Data {
    private  static ArrayList<String> mylist = new ArrayList<String>();
    private static String result = "";
    private static boolean flag = true; //判断是否添加相同的数据标志

    public static String getB(){
        //清空数据result,每次getB数据后要清空result，不然下次再getB时，就会把上次的数据重复输入
        result = "";
        for(int i = 0;i < mylist.size();i++){
            String str = (i+1)+"."+mylist.get(i)+"\n";
            result += str;
        }
        return result;
    }

    public static void setB(String c){
        //避免收藏相同的菜品
        if(mylist.contains(c))
            flag = false;
        if(flag == true)
            mylist.add(c.trim());
        //与result的原理相似，要重新恢复发到原来的值
        flag = true;
    }

    //清空收藏历史
    public static void clearAll(){
        result = "";
        mylist.clear();
    }
}
