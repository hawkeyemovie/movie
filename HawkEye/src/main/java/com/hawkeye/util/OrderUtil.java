package com.hawkeye.util;

public class OrderUtil {

    public static String  modifyLayout2(String layout,String seat,Integer column){
        layout = xz(layout.toCharArray(),seat,column);
        return layout;
    }


    public static String  modifyLayout1(String layout,String seat,Integer column){
        layout = qx(layout.toCharArray(),seat,column);
        return layout;
    }

    private static String  qx(char[] s,String id,Integer column){
        int i = Integer.parseInt(id.substring(0,id.indexOf("排")));
        int a = Integer.parseInt(id.substring(id.indexOf("排")+1,id.indexOf("座")));
        int wz = (i-1)*column+(a-1);
        for (int j = 0; j < s.length; j++) {
            if (j==wz){
                s[j]='1';
            }
        }
        return String.valueOf(s);
    }



    private static String  xz(char[] s,String id,Integer column){
        int i = Integer.parseInt(id.substring(0,id.indexOf("排")));
        int a = Integer.parseInt(id.substring(id.indexOf("排")+1,id.indexOf("座")));
        int wz = (i-1)*column+(a-1);
        for (int j = 0; j < s.length; j++) {
            if (j==wz){
                s[j]='2';
            }
        }
        return String.valueOf(s);
    }

    public static String  extract(String seat){
        int i = Integer.parseInt(seat.substring(0,seat.indexOf("排")));
        int a = Integer.parseInt(seat.substring(seat.indexOf("排")+1,seat.indexOf("座")));
        return i+""+a;
    }

    public static Integer getRow(String seatName){
        return Integer.parseInt(seatName.substring(0,seatName.indexOf("排")));
    }

    public static Integer getColumn(String seatName){
        return Integer.parseInt(seatName.substring(seatName.indexOf("排")+1,seatName.indexOf("座")));
    }


    public static String join(String str){
        String a = "";
        for (int i = 0; i < str.length() ; i++) {
            a+=str.charAt(i)+"-";
        }
        StringBuffer buffer = new StringBuffer(a);

        buffer.deleteCharAt(buffer.length() - 1).toString();

        return buffer.toString();
    }

    public static String remove(String str){
        String a ="";
        String [] strings = str.split("-");
        for (int i = 0; i < strings.length; i++) {
            a+=strings[i];
        }
        StringBuffer buffer = new StringBuffer(a);

        return buffer.toString();
    }
}
