package com.cui.utils.pinyin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获得文字的拼音
 * 目前只支持高位0xB1 -- 0xD7， 低位0xA1 -- 0xFE
 * @author cuipengyu
 */
public class WordPinyin {

	/**
	 * key代表每个区间，Object代表这个区间定义的多个拼音范围
	 */
	private static final Map<Integer, List<PinyinRange>> MapWords = new HashMap();

	static {
		
		add("a", 0xb0a1, 0xb0a2);
		add("ai", 0xb0a3, 0xb0af);
		add("an", 0xb0b0, 0xb0b8);
		add("ang", 0xb0b9, 0xb0bb);
		add("ao", 0xb0bc, 0xb0c4);
		add("ao", 0xb0bc, 0xb0c4);
		add("ba", 0xb0c5, 0xb0d6);
		add("bai", 0xb0d7, 0xb0de);
		add("ban", 0xb0df, 0xb0ed);
		add("bang", 0xb0ec, 0xb0f9);
		add("bao", 0xb0fa, 0xb1ac);
		add("bei", 0xb1ad, 0xb1bb);
		add("ben", 0xb1bc, 0xb1bf);
		add("beng", 0xb1c0, 0xb1c5);
		add("bi", 0xb1c6, 0xb1dd);
		add("bian", "0xb1de","0xb1e9"
				,"biao","0xb1ed"
				,"bie", "0xb1f1"
				,"bin", "0xb1f7"
				,"bing","0xb2a2"
				,"bo", "0xb2b5"
				,"bu", "0xb2c0"
				,"ca", "0xb2c1"
				,"cai", "0xb2cc"
				,"can", "0xb2d3"
				,"cang", "0xb2d9"
				,"cao", "0xb2dd"
				,"ce", "0xb2e2"
				,"ceng", "0xb2e4"
				,"cha", "0xb2ef","chai", "0xb2f2","chan", "0xb2fc"
				,"chang", "0xb3ab","chao", "0xb3b4","che", "0xb3ba"
				,"chen", "0xb3c4","cheng", "0xb3d3","chi", "0xb3e3"
				,"chong", "0xb3e8","chou", "0xb3f4","chu", "0xb4a6"
				,"chuai", "0xb4a7","chuan", "0xb4a3","chuang", "0xb4b4"
				,"chui", "0xb4b9","chun", "0xb4c0","chuo", "0xb4c2"
				,"ci", "0xb4ce","cong", "0xb4d4","cou", "0xb4d9"
				,"cuan", "0xb4dc","cui", "0xb4e4","cun", "0xb4e7"
				,"cuo", "0xb4ed","da", "0xb4f3","dai", "0xb5a2"
				,"dan", "0xb5b0","dang", "0xb5b5","dao", "0xb5c1"
				,"de", "0xb5c4","deng", "0xb5cb","di", "0xb5de"
				,"dian", "0xb5ee","diao", "0xb5f7","die", "0xb5fe"
				,"ding", "0xb6a9","diu", "0xb6aa","dong", "0xb6b4"
				,"dou", "0xb6bc","du", "0xb6ca","duan", "0xb6d0"
				,"dui", "0xb6d4","dun", "0xb6dd","duo", "0xb6e9"
				,"e", "0xb6f6","en", "0xb6f7","er", "0xb7a1"
				,"fa", "0xb7a9","fan", "0xb7ba","fang", "0xb7c5"
				,"fei", "0xb7d1","fen", "0xb7e0","feng", "0xb7ef"
				,"fo", "0xb7f0","fou", "0xb7f1","fu", "0xb8c0"
				,"ga", "0xb8c2","gai", "0xb8c8","gan", "0xb8d3"
				,"gang", "0xb8dc","gao", "0xb8e6","ge", "0xb8f7"
				,"gei", "0xb8f8","gen", "0xb8fa","geng", "0xb9a3"
				,"gong", "0xb9b2","gou", "0xb9bb","gu", "0xb9cd"
				,"gua", "0xb9d3","guai", "0xb9d7","guan", "0xb9e1"
				,"guang", "0xb9e4","gui", "0xb9f4","gun", "0xb9f7"
				,"guo", "0xb9fd","ha", "0xb9fe","hai", "0xbaa7"
				,"han", "0xbaba","hang", "0xbabd","hao", "0xbac6"
				,"he", "0xbad8","hei", "0xbada","hen", "0xbade"
				,"heng", "0xbae3","hong", "0xbaec","hou", "0xbaf3"
				,"hu", "0xbba7","hua", "0xbbb0","huai", "0xbbb5"
				,"huan", "0xbbc3","huang", "0xbbd1","hui", "0xbbe6"
				,"hun", "0xbbec","huo", "0xbbf6","ji", "0xbccd"
				
				,"jia", "0xbcde","jian", "0xbda8","jiang", "0xbdb5"
				,"jiao", "0xbdd1","jie", "0xbdec","jin", "0xbea3"
				,"jing", "0xbebb","jiong", "0xbebd","jiu", "0xbece"
				
				,"ju", "0xbee7","juan", "0xbeee","jue", "0xbef8"
				,"jun", "0xbfa5","ka", "0xbfa9","kai", "0xbfae"
				,"kan", "0xbfb4","kang", "0xbfbb","kao", "0xbfbf"
				
				
				
				
				
				
				
				);
		
	}

	public static String getPinyin(String word) {

		// 获得字符串对应的byte数组
		int[] bs = getBytes(word);
		if (bs == null) {
			return null;
		}
		
		// 目前只支持高位0xB1 -- 0xD7， 低位0xA1 -- 0xFE
		
		// 获得字符串在GBK表中的数字
		int val = getInt(word);

		List<PinyinRange> lst = MapWords.get(bs[0]);
		if (lst == null) {
			return null;
		}

		for (PinyinRange r : lst) {
			if (r.start <= val && r.end >= val) {
				return r.pinyin;
			}
		}

		return null;
	}

	/**
	 * 增加一个拼音的开始值和结尾值
	 */
	private static void add(String pinyin, int start, int end) {
		PinyinRange pr = new PinyinRange(pinyin, start, end);
		int[] arr = pr.getHiRange();
		for (Integer i : arr) {
			List<PinyinRange> lst = MapWords.get(i);
			if (lst == null) {
				lst = new ArrayList();
				MapWords.put(i, lst);
			}
			lst.add(pr);
		}
	}
	
	private static void add(String ...params){
		String pinyin=params[0];
		Integer start=Integer.valueOf(params[1]);
		Integer end=Integer.valueOf(params[2]);
		add(pinyin,start,end);
		
		for(int i=3;i<params.length;i=i+2){
			pinyin=params[i];
			start=end+1;
			end=Integer.valueOf(params[i+1]);
			add(pinyin,start,end);
		}
	}

	private static int[] getBytes(String word) {
		if (word == null || word.length() <= 0) {
			return null;
		}
		String w = word.substring(0, 1);

		try {
			byte[] bs = w.getBytes("GBK");
			int[] res=new int[bs.length];
			for(int i=0;i<bs.length;i++){
				res[i]=bs[i]&0x00FF;
			}
			return res;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private static int getInt(String word) {
		int[] bs = getBytes(word);
		if (bs == null || bs.length < 2) {
			return -1;
		}

		return 0xFFFF&((bs[0] << 8) | bs[1]);
	}

}

/**
 * 拼音的区间范围 定义每个拼音开始所在的位置和结果所在的位置
 */
class PinyinRange {
	String pinyin;
	int start;
	int end;

	PinyinRange(String pinyin, int start, int end) {
		this.pinyin = pinyin;
		this.start = start;
		this.end = end;
	}

	/**
	 * 获得拼音跨越的高位区 高位区可能是多个，所以返回值为数组
	 * 
	 * @return
	 */
	int[] getHiRange() {
		int hi1 = start>> 8;
		int hi2 = end >> 8;
		int[] arr = new int[hi2 - hi1 + 1];
		for (int i = 0; i <= hi2 - hi1; i++) {
			arr[i] = hi1 + i;
		}
		return arr;
	}

}
