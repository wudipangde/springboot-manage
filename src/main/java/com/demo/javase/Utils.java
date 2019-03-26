package com.demo.javase;


import org.springframework.util.CollectionUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("restriction")
public class Utils {
	public static final Executor exec = Executors.newFixedThreadPool(10);
	public static final Executor audioExec=Executors.newFixedThreadPool(2);
	/**
	 * 删除html代码
	 * @param htmlStr
	 * @return
	 */
	public static String removeHtml(String htmlStr){
		String result = "";
		boolean flag = true;
		if (isEmpty(htmlStr)) {
			return null;
		}
		char[] a = htmlStr.toCharArray();
		int length = a.length;
		for (int ii = 0; ii < length; ii++) {
			if (a[ii] == '<') {
				flag = false;
				continue;
			}
			if (a[ii] == '>') {
				flag = true;
				continue;
			}
			if (flag == true) {
				result += a[ii];
			}
		}
	     String content = result.toString().replaceAll("&nbsp;","");
	     return content.replaceAll("\\s*","");  
	}
	/**
	 * 判断是否为空
	 */
	public static boolean isEmpty(Object v) {
		return null==v ||""==v ;
	}
	/**
     * 判断是否为空
     */
	public static boolean isEmpty(String v) {		
    	return null==v ||  v.trim().length()==0 || "null".equals(v.trim()) ;
    }
    /**
     * 判断是否为空
     */
	public static boolean isEmpty(@SuppressWarnings("rawtypes") List v) {
		return v==null || v.size()<=0;
    }
   
    /**
     * 判断是否为空
     */
	public static boolean isEmpty(@SuppressWarnings("rawtypes") Map v) {		
    	return v==null || v.size()<=0;
    }
	
    
    /**
     * 判断是否为空
     */
	public static boolean isEmpty(@SuppressWarnings("rawtypes") Set v) {		
    	return v==null || v.size()<=0;
    }
	
    /**
     * 判断是不是纯数字
     * @param str
     * @return
     */
    public static boolean isNum(String str){
    	return (str+"").trim().matches("\\d+");
    }
    /**
     * 判断是不是金额类型
     * @param str
     * @return
     */
    public static boolean isMoney(String str){
    	if(isEmpty(str)){return true;}
    	return isNum((str+"").replace(".", ""));
    }
    /**
     * 判断是不是手机
     * @param str
     * @return
     */
    public static boolean isMobile(String str){
    	if(isNum(str)){
    		if(str.trim().length()==11){return true;}
    	}
    	return false;
    }
	
	/**
	 * 把一个List拆分成多个子List
	 * @author zhangys
	 * @date 2016年7月30日 下午1:05:24
	 * @param target 目标List
	 * @param size 拆分的每个List的长度
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> target, int size) {
		List<List<T>> result = new ArrayList<List<T>>();
		if (CollectionUtils.isEmpty(target)) {
			return result;
		}
		if (target.size() <= size) {
			result.add(target);
			return result;
		}
		// 获取被拆分List的个数
		int arrSize = (target.size()%size==0) ? (target.size()/size) : (target.size()/size+1);
		for (int i = 0; i < arrSize; i++) {
			List<T> sub = new ArrayList<T>();
			for (int j = i*size; j <= size*(i+1)-1; j++) {
				if (j <= target.size()-1) {
					sub.add(target.get(j));
				}
			}
			result.add(sub);
		}
		return result;
	}

    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

    /**
	 * 获取UUID，并除去其中的"-".
	 * 
	 * @return
	 */
	public static String getUUID() {
		StringBuffer sbrUuid = new StringBuffer();
		String uuid = UUID.randomUUID().toString();// 获取随机唯一标识符
		// 去掉标识符中的"-"
		sbrUuid.append(uuid.substring(0, 8)).append(uuid.substring(9, 13))
				.append(uuid.substring(14, 18)).append(uuid.substring(19, 23))
				.append(uuid.substring(24));
		return sbrUuid.toString();
	}
//    string money去掉多余的零
    public static String rvZeroAndDot(String s){
        if (s.isEmpty()) {
            return null;
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


	/**
	 *  @descript 得到一个字符串的长度,显示的长度,一个汉字长度为2,英文字符长度为1
	 *  @author chensz
	 *  @date 2016年9月29日
	 *  @param str 需要得到长度的字符串
	 *  @return int 得到的字符串长度
	 */
	public static int getWordLength(String str){
		if (str == null) return 0;
		int count = 0;
		String regex = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);//提取出来的中文
		while (m.find()) {
			count++;
		}
		return count+str.length();
	}

	/**
	 * 将是否转换成数字
	 */
	public static int changeYesNoToInt(String str){
		if(Utils.isEmpty(str)){return 0;}
		if("是".equals(str)){return 1;}
		else{return 0;}
	}

	/**
	 * 正常计算除法
	 */
	public static String txfloat(int a,int b) {
		DecimalFormat df = new DecimalFormat("0.00");//设置保留位
		return df.format((float) a / b);
	}
}