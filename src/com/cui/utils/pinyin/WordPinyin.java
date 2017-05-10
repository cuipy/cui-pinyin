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
		add("bian", 0xb1de,0xb1e9
				,"biao",0xb1ed
				,"bie", 0xb1f1
				,"bin", 0xb1f7
				,"bing",0xb2a2
				,"bo", 0xb2b5
				,"bu", 0xb2c0
				,"ca", 0xb2c1
				,"cai", 0xb2cc
				,"can", 0xb2d3
				,"cang", 0xb2d9
				,"cao", 0xb2dd
				,"ce", 0xb2e2
				,"ceng", 0xb2e4
				,"cha", 0xb2ef,"chai", 0xb2f2,"chan", 0xb2fc
				,"chang", 0xb3ab,"chao", 0xb3b4,"che", 0xb3ba
				,"chen", 0xb3c4,"cheng", 0xb3d3,"chi", 0xb3e3
				,"chong", 0xb3e8,"chou", 0xb3f4,"chu", 0xb4a6
				,"chuai", 0xb4a7,"chuan", 0xb4ae,"chuang", 0xb4b4
				,"chui", 0xb4b9,"chun", 0xb4c0,"chuo", 0xb4c2
				,"ci", 0xb4ce,"cong", 0xb4d4,"cou", 0xb4d9
				,"cuan", 0xb4dc,"cui", 0xb4e4,"cun", 0xb4e7
				,"cuo", 0xb4ed,"da", 0xb4f3,"dai", 0xb5a2
				,"dan", 0xb5b0,"dang", 0xb5b5,"dao", 0xb5c1
				,"de", 0xb5c4,"deng", 0xb5cb,"di", 0xb5de
				,"dian", 0xb5ee,"diao", 0xb5f7,"die", 0xb5fe
				,"ding", 0xb6a9,"diu", 0xb6aa,"dong", 0xb6b4
				,"dou", 0xb6bc,"du", 0xb6ca,"duan", 0xb6d0
				,"dui", 0xb6d4,"dun", 0xb6dd,"duo", 0xb6e9
				,"e", 0xb6f6,"en", 0xb6f7,"er", 0xb7a1
				,"fa", 0xb7a9,"fan", 0xb7ba,"fang", 0xb7c5
				,"fei", 0xb7d1,"fen", 0xb7e0,"feng", 0xb7ef
				,"fo", 0xb7f0,"fou", 0xb7f1,"fu", 0xb8c0
				,"ga", 0xb8c2,"gai", 0xb8c8,"gan", 0xb8d3
				,"gang", 0xb8dc,"gao", 0xb8e6,"ge", 0xb8f7
				,"gei", 0xb8f8,"gen", 0xb8fa,"geng", 0xb9a3
				,"gong", 0xb9b2,"gou", 0xb9bb,"gu", 0xb9cd
				,"gua", 0xb9d3,"guai", 0xb9d7,"guan", 0xb9e1
				,"guang", 0xb9e4,"gui", 0xb9f4,"gun", 0xb9f7
				,"guo", 0xb9fd,"ha", 0xb9fe,"hai", 0xbaa7
				,"han", 0xbaba,"hang", 0xbabd,"hao", 0xbac6
				,"he", 0xbad8,"hei", 0xbada,"hen", 0xbade
				,"heng", 0xbae3,"hong", 0xbaec,"hou", 0xbaf3
				,"hu", 0xbba7,"hua", 0xbbb0,"huai", 0xbbb5
				,"huan", 0xbbc3,"huang", 0xbbd1,"hui", 0xbbe6
				,"hun", 0xbbec,"huo", 0xbbf6,"ji", 0xbccd
				,"jia", 0xbcde,"jian", 0xbda8,"jiang", 0xbdb5
				,"jiao", 0xbdd1,"jie", 0xbdec,"jin", 0xbea3
				,"jing", 0xbebb,"jiong", 0xbebd,"jiu", 0xbece
				,"ju", 0xbee7,"juan", 0xbeee,"jue", 0xbef8
				,"jun", 0xbfa5,"ka", 0xbfa9,"kai", 0xbfae
				,"kan", 0xbfb4,"kang", 0xbfbb,"kao", 0xbfbf
				,"ke", 0xbfce,"ken", 0xbfd2,"keng", 0xbfd4
				,"kong", 0xbfd8,"kou", 0xbfdc,"ku", 0xbfe3
				,"kua", 0xbfe8,"kuai", 0xbfec,"kuan", 0xbfee
				,"kuang", 0xbff6,"kui", 0xc0a3,"kun", 0xc0a7
				,"kuo", 0xc0ab,"la", 0xc0b2,"lai", 0xc0b5
				,"lan", 0xc0c4,"lang", 0xc0cb,"lao", 0xc0d4
				,"le", 0xc0d6,"lei", 0xc0e1,"leng", 0xc0e4
				,"li", 0xc1a8,"lia", 0xc1a9,"lian", 0xc1b7
				,"liang", 0xc1c2,"liao", 0xc1cf,"lie", 0xc1d4
				,"lin", 0xc1e0,"ling", 0xc1ee,"liu", 0xc1f9
				,"long", 0xc2a4,"lou", 0xc2aa,"lu", 0xc2be
				,"lv", 0xc2cc,"luan", 0xc2d2,"lue", 0xc2d4
				,"lun", 0xc2db,"luo", 0xc2e7,"ma", 0xc2f0
				,"mai", 0xc2f6,"man", 0xc3a1,"mang", 0xc3a7
				,"mao", 0xc3b3,"me", 0xc3b4,"mei", 0xc3c4
				,"men", 0xc3c7,"meng", 0xc3cf,"mi", 0xc3dd
				,"mian", 0xc3e6,"miao", 0xc3ee,"mie", 0xc3f0
				,"min", 0xc3f6,"ming", 0xc3fc,"miu", 0xc3fd
				,"mo", 0xc4b0,"mou", 0xc4b3,"mu", 0xc4c2
				,"na", 0xc4c9,"nai", 0xc4ce,"nan", 0xc4d1
				,"nang", 0xc4d2,"nao", 0xc4d7,"ne", 0xc4d8
				,"nei", 0xc4da,"nen", 0xc4db,"neng", 0xc4dc
				,"ni", 0xc4e7,"nian", 0xc4ee,"niang", 0xc4f0
				,"niao", 0xc4f2,"nie", 0xc4f9,"nin", 0xc4fa
				,"ning", 0xc5a2,"niu", 0xc5a6,"nong", 0xc5aa
				,"nu", 0xc5ad,"nv", 0xc5ae,"nuan", 0xc5af
				,"niu", 0xc5b1,"nuo", 0xc5b5,"ou", 0xc5bd
				,"pa", 0xc5c3,"pai", 0xc5c9,"pan", 0xc5d1
				,"pang", 0xc5d6,"pao", 0xc5dd,"pei", 0xc5e6
				,"pen", 0xc5e8,"peng", 0xc5f6,"pei", 0xc5f7
				,"pi", 0xc6a9,"pian", 0xc6ad,"piao", 0xc6b1
				,"pie", 0xc6b3,"pin", 0xc6b8,"ping", 0xc6c1
				,"po", 0xc6ca,"pu", 0xc6d9,"qi", 0xc6fe
				,"qia", 0xc7a2,"qian", 0xc7b8,"qiang", 0xc7c0
				,"qiao", 0xc7cf,"qie", 0xc7d4,"qin", 0xc7df
				,"qing", 0xc7ec,"qiong", 0xc7ee,"qiu", 0xc7f6
				,"qu", 0xc8a5,"quan", 0xc8b0,"que", 0xc8b8
				,"qun", 0xc8ba,"ran", 0xc8be,"rang", 0xc8c3
				,"rao", 0xc8c6,"re", 0xc8c8,"ren", 0xc8d2
				,"reng", 0xc8d4,"ri", 0xc8d5,"rong", 0xc8df
				,"rou", 0xc8e2,"ru", 0xc8ec,"ruan", 0xc8ee
				,"rui", 0xc8f1,"run", 0xc8f3,"ruo", 0xc8f5
				,"sa", 0xc8f8,"sai", 0xc8fc,"san", 0xc9a2
				
				,"sang", 0xc9a5,"sao", 0xc9a9,"se", 0xc9ac
				,"sen", 0xc9ad,"seng", 0xc9ae,"sha", 0xc9b7
				,"shai", 0xc9b9,"shan", 0xc9c9,"shang", 0xc9d1
				,"shao", 0xc9dc,"she", 0xc9e8,"shen", 0xc9f8
				,"sheng", 0xcaa5,"shi", 0xcad4,"shou", 0xcade
				,"shu", 0xcba1,"shua", 0xcba3,"shuai", 0xcba7
				,"shuan", 0xcba9,"shuang", 0xcbac,"shui", 0xcbb0
				

				
				
				
				
				
				
				
				
				
				
				);
		
	}

	public static String getPinyin(String word) {

		// 获得字符串对应的byte数组
		int[] bs = getBytes(word);
		if (bs == null) {
			return null;
		}
		
		// 目前只支持高位0xB1 -- 0xD7， 低位0xA1 -- 0xFE
		if(bs[0]<0xB1||bs[0]>0xD7||bs[1]<0xA1||bs[1]>0xFE){
			return null;
		}
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
	
	private static void add(Object ...params){
		String pinyin=(String)params[0];
		Integer start=(Integer)params[1];
		Integer end=(Integer)params[2];
		add(pinyin,start,end);
		
		for(int i=3;i<params.length;i=i+2){
			pinyin=(String)params[i];
			start=end+1;
			end=(Integer)params[i+1];
			if(end<start){
				System.out.println("错了");
			}
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
