package com.tyc.lottery.award.prizes.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 彩票计算
 * @author anyi
 *
 */
public class AwardUtils {

	
	
	
	 public static final int D3_直选 = 1;
	 public static final int D3_组选3 = 2;
	 public static final int D3_组选6 = 3;
	 
	 /**
	  * 返回2个数组位置及号码相同的元素个数
	  * @param a
	  * @param b
	  * @return
	  */
	 public static int sameNumbers(String[] a,String[] b){
		int sum = 0;
		 if(a.length<=b.length){
			 for(int i=0;i<a.length;i++)
				 if(a[i]!=null&&b[i]!=null&&!a[i].isEmpty()&&!b[i].isEmpty()&&a[i].equals(b[i]))sum++;
		 } else{
			 for(int i=0;i<b.length;i++)
				 if(a[i]!=null&&b[i]!=null&&!a[i].isEmpty()&&!b[i].isEmpty()&&a[i].equals(b[i]))sum++;
		 }
		 
		 return sum;
	 }
	 
	 /**
	  * 根据 arr 的类型 判断 符合那种投注类型
	  * @param arr
	  * @return
	  * @see AwardUtils.D3_直选
	  * @see AwardUtils.D3_组选3
	  * @see AwardUtils.D3_组选6
	  */
	 public static int D3BettingFormat(String[] arr){
		 Set<String> set = new HashSet<String>();
		 for(String s : arr)
			 set.add(s);
		 switch(set.size()){
		 case 1:
			 return D3_直选;
		 case 2:
			 return D3_组选3;
		 case 3:
			 return D3_组选6;
		 }
		return 0;
	 }
	 /**
		 * 去除重复(3d) 例：1:2:3 与 3:2:1 重复
		 * @param set
		 */
		public static void D3RemoveDuplicate(Set<String> set){
			List<String> list = new ArrayList<String>(set);
			for (int i=0;i<list.size();i++) {
				String s = list.get(i);
				String[] arr1 = s.split(":");
				List<String> list1 = new ArrayList<String>();
				list1.add(arr1[0]);
				list1.add(arr1[1]);
				list1.add(arr1[2]);
				Collections.sort(list1);
				for(int j=i+1;j<list.size();j++){				
					String[] arr2 = list.get(j).split(":");
					List<String> list2 = new ArrayList<String>();
					list2.add(arr2[0]);
					list2.add(arr2[1]);
					list2.add(arr2[2]);
					Collections.sort(list2);
					if(list1.equals(list2)){
						set.remove(s);
						break;
					}	
				}
			}
		}
	/**
	 * 双色球 N选6的组合数
	 * @param required 必须包含的数
	 * @param arr
	 * @param list
	 */
	public static void assemble6(String[]required,String[]arr,List<String> list){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++)
					for (int m = l + 1; m < arr.length; m++)
						for (int n = m + 1; n < arr.length; n++)
							for (int k = n + 1; k < arr.length; k++){
								Set<String> set = new HashSet<String>();
								set.add(arr[i]);
								set.add(arr[j]);
								set.add(arr[l]);
								set.add(arr[m]);
								set.add(arr[n]);
								set.add(arr[k]);
								for(String r : required){
									set.add(r);
								}
								if(set.size()==6)
									list.add(arr[i]+","+arr[j]+","+arr[l]+","+arr[m]+","+arr[n]+","+arr[k]);	
							}
	}
	
	/**
	 * 双色球 N选6的组合数
	 * @param arr
	 * @param list
	 */
	public static void assemble6(String[]arr,List<String> list){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++)
					for (int m = l + 1; m < arr.length; m++)
						for (int n = m + 1; n < arr.length; n++)
							for (int k = n + 1; k < arr.length; k++)
								list.add(arr[i]+","+arr[j]+","+arr[l]+","+arr[m]+","+arr[n]+","+arr[k]);	
	}
	

	
	public static void main(String[] args) {
//		Set<String> set = new HashSet<String>();
//		assemble3(new String[]{"胜","平","负","胜","平","负","胜","平","负"}, set);
//	
//		for (String string : set) {
//			System.out.println(string);
//		}
//		System.out.println(set.size());
		
		
//		Set<String> set1 = new HashSet<String>();
//		assemble2(new String[]{"胜","平","负","胜","平","负"}, set1);
//	
//		for (String string : set1) {
//			System.out.println(string);
//		}
//		System.out.println(set1.size());
		
		System.out.println(sameNumbers("1:2:3".split(":"), "1:f:3".split(":")));
		
		
	}
	
	/**
	 * 七乐彩 N选7的组合数
	 * @param arr
	 * @param list
	 */
	public static void assemble7(String[]arr,List<String> list){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++)
					for (int m = l + 1; m < arr.length; m++)
						for (int n = m + 1; n < arr.length; n++)
							for (int k = n + 1; k < arr.length; k++)
								for (int p = k + 1; p < arr.length; p++)
									list.add(arr[i]+","+arr[j]+","+arr[l]+","+arr[m]+","+arr[n]+","+arr[k]+","+arr[p]);		
	}
	
	/**
	 * 七乐彩 N选7的组合数
	 * @param required 必须包含的数
	 * @param arr
	 * @param list
	 */
	public static void assemble7(String[]required,String[]arr,List<String> list){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++)
					for (int m = l + 1; m < arr.length; m++)
						for (int n = m + 1; n < arr.length; n++)
							for (int k = n + 1; k < arr.length; k++){
								for (int p = k + 1; p < arr.length; p++){
									Set<String> set = new HashSet<String>();
									set.add(arr[i]);
									set.add(arr[j]);
									set.add(arr[l]);
									set.add(arr[m]);
									set.add(arr[n]);
									set.add(arr[k]);
									set.add(arr[p]);
									for(String r : required){
										set.add(r);
									}
									if(set.size()==7)
										list.add(arr[i]+","+arr[j]+","+arr[l]+","+arr[m]+","+arr[n]+","+arr[k]+","+arr[p]);	
								}
							}
	}
	
	/**
	 * D3 N选3的组合数
	 * @param required 必须包含的数
	 * @param arr
	 * @param list
	 */
	public static void assemble3(String[]required,String[]arr,Set<String> list){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++){
									Set<String> set = new HashSet<String>();
									set.add(arr[i]);
									set.add(arr[j]);
									set.add(arr[l]);
									int len = set.size();
									for(String r : required)
										set.add(r);
									
									if(set.size()==len)
										list.add(arr[i]+":"+arr[j]+":"+arr[l]);	
								
							}
	}
	
	/**
	 * D3 N选3的组合数
	 * @param arr
	 * @param list
	 */
	public static void assemble3(String[]arr,Set<String> set){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++)
									set.add(arr[i]+":"+arr[j]+":"+arr[l]);		
	}
	
	public static void assemble2(String[]arr,Set<String> set){
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
									set.add(arr[i]+":"+arr[j]);		
	}
	
	
	/**
	 * 根据字符串创建双色球奖等
	 * @param s
	 * @return
	 */
	public static Award createSSQprize(String s,String lottery_number){
		Award award = null;
		String[] names = {"一等奖","二等奖","三等奖","四等奖","五等奖","六等奖"};
		String[] prize = {"6:1","6:0","5:1","5:0#4:1","4:0#3:1","2:1#1:1#0:1"};
		for(int i =0;i<prize.length;i++){
			if(prize[i].contains("#")){
				String[] arr = prize[i].split("#");
				for(String ss : arr){
					if(ss.equals(s)){
						award = new Award();
						award.setLottery_name("SSQ");
						award.setAward_name(names[i]);
						award.setLottery_number(lottery_number);
						award.setAward_code(String.valueOf(i+1));
						return award;
					}
				}
			}
			if(prize[i].equals(s)){
				award = new Award();
				award.setLottery_name("SSQ");
				award.setAward_name(names[i]);
				award.setLottery_number(lottery_number);
				award.setAward_code(String.valueOf(i+1));
			}
		}
		return award;
	}
	
	
	/**
	 * 双色球红球中奖个数
	 * @param red
	 * @param openRed
	 * @return
	 */
	public static int compare_SSQ_RED(String[] red,String[]openRed){
		int l = red.length;
		int len = openRed.length;
			Set<String> set = new HashSet<String>();
			for(String s : openRed){
				set.add(s);
			}
			for(String s : red){
				set.add(s);
			}
			return Math.abs(l -set.size()+len);		
	}
	/**
	 * 双色球蓝球中奖个数
	 * @param blue
	 * @param openBlue
	 * @return
	 */
	public static int compare_SSQ_BLUE(String[] blue,String[]openBlue){
		int l = blue.length;
		int len = openBlue.length;
			Set<String> set = new HashSet<String>();
			for(String s : openBlue){
				set.add(s);
			}
			for(String s : blue){
				set.add(s);
			}
			return Math.abs(l -set.size()+len);		
	}
	
	/**
	 * 七乐彩红球中奖个数
	 * @param red  七个号码(1注) 兑奖号码
	 * @param open 七个号码(1注) 开奖号码
	 * @return 相同号码个数
	 */
	public static int compare_QLC_RED(String[] red,String[]open){
		int l = red.length;
		int len = open.length;
			Set<String> set = new HashSet<String>();
			for(String s : open){
				set.add(s);
			}
			for(String s : red){
				set.add(s);
			}
			return Math.abs(l -set.size()+len);		
	}
	
	/**
	 * 七乐彩特殊号中奖个数
	 * @param red
	 * @param special
	 * @return
	 */
	public static int compare_QLC_SPECIAL(String[] red,String special){
		for (String string : red) {
			if(string.equals(special))
				return 1;
		}
		return 0;
	}
	
	/**
	 * 根据字符串创建七乐彩奖等
	 * @param s
	 * @return
	 */
	public static Award createQLCprize(String s,String lottery_number){
		Award award = null;
		String[] names = {"一等奖","二等奖","三等奖","四等奖","五等奖","六等奖","七等奖"};
		String[] prize = {"7+0","6+1","6+0","5+1","5+0","4+1","4+0"};
		for(int i =0;i<prize.length;i++){
			if(prize[i].contains("#")){
				String[] arr = prize[i].split("#");
				for(String ss : arr){
					if(ss.equals(s)){
						award = new Award();
						award.setLottery_name("QLC");
						award.setAward_name(names[i]);
						award.setAward_code(String.valueOf(i+1));
						award.setLottery_number(lottery_number);
						return award;
					}
				}
			}
			if(prize[i].equals(s)){
				award = new Award();
				award.setLottery_name("QLC");
				award.setAward_name(names[i]);
				award.setAward_code(String.valueOf(i+1));
				award.setLottery_number(lottery_number);
			}
		}
		return award;
	}
	
	/**
	 * 新3d彩票 1D投注
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3new_1D(String[]code,String[] open){
		if(sameNumbers(code, open)!=1)return null;
		
		Award award = new Award();
		award.setLottery_name("D3");
		award.setAward_name("1D中奖");
		award.setAward_code("4");
		award.setLottery_number(open[0]+":"+open[1]+":"+open[2]);
		return award;
	}
	
	/**
	 * 新3d彩票 2D投注
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3new_2D(String[]code,String[] open){
		if(sameNumbers(code, open)!=2)return null;
		
		Award award = new Award();
		award.setLottery_name("D3");
		award.setAward_name("2D中奖");
		award.setAward_code("5");
		award.setLottery_number(open[0]+":"+open[1]+":"+open[2]);
		return award;
	}
	
	/**
	 * 3d彩票 直选投注中奖判断
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3_直选(String[] code,String[] open){
		if(code.length != 3 || code.length != open.length)
			return null;
		
		for(int i=0;i<3;i++)
			if(!open[i].equals(code[i]))
				return null;
		
		Award award = new Award();
		award.setLottery_name("D3");
		award.setAward_name("直选中奖");
		award.setAward_code("1");
		award.setLottery_number(open[0]+":"+open[1]+":"+open[2]);
		return award;
	}
	
	/**
	 * 3d彩票 组选3投注中奖判断
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3_组选3(String[] code,String[] open){
		if(code.length != 3 || code.length != open.length)
			return null;
		Set<String> set = new HashSet<String>();
		for(String s : open)
			set.add(s);
		if(set.size()>2)
			return null;
		
		set = permutation3(open[0]+":"+open[1]+":"+open[2]);
		for (String string : set) {
			String s = code[0]+":"+code[1]+":"+code[2];
			if(s.equals(string)){
				Award award = new Award();
				award.setLottery_name("D3");
				award.setAward_name("组选3中奖");
				award.setAward_code("2");
				award.setLottery_number(s);
//				String[] arr=sort(s.split(":"));				
//				award.setLottery_number(arr[0]+":"+arr[1]+":"+arr[2]);
				return award;
			}				
		}
		
		return null;
	}
	
	/**
	 * 3d彩票 组选6投注中奖判断
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3_组选6(String[] code,String[] open){
		if(code.length != 3 || code.length != open.length)
			return null;
		Set<String> set = new HashSet<String>();
		for(String s : open)
			set.add(s);
		if(set.size()>3)
			return null;
		
		set = permutation3(open[0]+":"+open[1]+":"+open[2]);
		for (String string : set) {
			String s = code[0]+":"+code[1]+":"+code[2];
			if(s.equals(string)){
				Award award = new Award();
				award.setLottery_name("D3");
				award.setAward_name("组选6中奖");
				award.setAward_code("3");
				award.setLottery_number(s);
//				String[] arr=sort(s.split(":"));				
//				award.setLottery_number(arr[0]+":"+arr[1]+":"+arr[2]);
				return award;
			}				
		}
		
		return null;
	}
	
	/**
	 * 得到3个数的排列数 (N选3)
	 * @param arr 长度为N的数组
	 * @return N选3 的排列组合 集合
	 */
	public static Set<String> permutation3(String[] arr){
		Set<String> set = new HashSet<String>();
		
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				for (int l = j + 1; l < arr.length; l++){
					String[] s = {arr[i],arr[j],arr[l]};
					permute(s,0,s.length-1,set);
				}

		return set;
	}
	
	/**
	 * 得到3个数的排列数
	 * @param num 用:号隔开的字符串  例：(1:2:3)
	 * @return 组合数的集合 例：(1:2:3 , 1:3:2 , 2:1:3 , 2:3:1 , 3:1:2 , 3:2:1)
	 */
	public static Set<String> permutation3(String str){
		String[]num = str.split(":");
		String[][] arr = new String[][]{
				{num[0],num[1],num[2]},{num[0],num[2],num[1]},
				{num[1],num[0],num[2]},{num[1],num[2],num[0]},
				{num[2],num[0],num[1]},{num[2],num[1],num[0]}};

		Set<String> set = new HashSet<String>();
		for(int i=0;i<arr.length;i++){
			String s = arr[i][0]+":"+arr[i][1]+":"+arr[i][2];
			set.add(s);
		}
		return set;
	}
	
	/**
	 * 得到3个数的排列数(将 对应 arr的组合数中的 ch 替换成 0-9)
	 * @param arr 3个数的组合 例：([1] [*] [2])
	 * @param f 替换的字符
	 * @return [*:*:*],... 的数字组合的集合
	 */
	public static Set<String> permutation3(String[] arr,final String ch){
		if(arr.length!=3)
			return null;
		String code = arr[0]+":"+arr[1]+":"+arr[2];
		Set<String> set = new HashSet<String>();
		
		int f = 0;
		for(int i=0;i<arr.length;i++)
			if(arr[i].equalsIgnoreCase(ch))
				f++;

		switch(f){
		case 0:
			set.add(code);
			break;
		case 1:
			for(int i=0;i<10;i++)
				set.add(code.replaceFirst(ch, String.valueOf(i)));
			break;
		case 2:
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++){
					String s = code.replaceFirst(ch, String.valueOf(i));
					String s1 = s.replaceFirst(ch, String.valueOf(j));
					set.add(s1);
				}	
			break;
		case 3:
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)
					for(int k=0;k<10;k++){
						String s = code.replaceFirst(ch, String.valueOf(i));
						String s1 = s.replaceFirst(ch, String.valueOf(j));
						String s2 = s1.replaceFirst(ch, String.valueOf(k));
						set.add(s2);
					}
			break;
		}
		
		return set;
	}

	/**
	 * 得到3个数的排列数
	 * @param num  和值
	 * @return 3个数相加等于 num 的三位数集合
	 */
	public static Set<String> permutation3(int num){
		Set<String> set = new HashSet<String>();
		for(int i=0;i<1000;i++){
			int a = i/100;
			int b = i%100/10;
			int c = i%10;
			if(a+b+c==num)
				set.add(a+":"+b+":"+c);
		}
		return set;
	}
	
	/**
	 * 得到3个数的排列数
	 * @param num  跨度 (1~9)
	 * @return 3个数 最大号码 – 最小号码 = 跨度
	 */
	public static Set<String> permutation3_跨度(int num){
		Set<String> set = new HashSet<String>();
		for(int i=0;i<1000;i++){
			int a = i/100;
			int b = i%100/10;
			int c = i%10;
			int max = Math.max(Math.max(a, b),c);
			int min = Math.min(Math.min(a, b),c);
			if(max-min==num)
				set.add(a+":"+b+":"+c);
		}
		return set;
	}
	
	/**
	 * 得到3个数的排列数
	 * @return 全组三的270注
	 */
	public static Set<String> permutation3_全组三(){
		Set<String> set = new HashSet<String>();
		for(int i=0;i<1000;i++){
			int a = i/100;
			int b = i%100/10;
			int c = i%10;
			Set<Integer> test = new HashSet<Integer>();
			test.add(a);
			test.add(b);
			test.add(c);
			if(test.size()==2)
				set.add(a+":"+b+":"+c);
		}
		return set;
	}
	
	/**
	 * 得到3个数的排列数
	 * @param arr 包含组合数定义的二位数组 例：
	 * 
	 * <br> 百位2个号码（0，1）
	 * <br> 十位3个号码（2，3，4）
	 * <br> 个位2个号码（5，6）
	 * @return 满足条件的集合
	 */
	public static Set<String> permutation3(String[][] arr){
		if(arr.length!=3)
			return null;
		int count = arr[0].length * arr[1].length * arr[2].length;
		int[][] nums = new int[arr.length][];
		for(int i=0;i<arr.length;i++){
			nums[i] = new int[arr[i].length];
			for(int j=0;j<arr[i].length;j++){
				nums[i][j] = Integer.parseInt(arr[i][j]);
			}
		}
		Set<String> set = new HashSet<String>();
		for(int i=0;i<1000;i++){
			int a = i/100;
			int b = i%100/10;
			int c = i%10;
			
			String s = "";
			for(int x=0;x<nums.length;x++)
				for(int y=0;y<nums[x].length;y++){
					switch(x){
					case 0:
						if(a==nums[x][y])
							s+=a+":";
						break;
					case 1:
						if(b==nums[x][y])
							s+=b+":";
						break;
					case 2:
						if(c==nums[x][y])
							s+=c;
						break;
					}
				}
			if(s.length()==5){
				count--;
				set.add(s);
				if(count==0)
					return set;
			}
		}
		return null;
	}
	
	/**
	 * 得到3个数的排列数
	 * @param d 胆码         选择d（0<d<=2）个数字作为胆码
	 * @param t 拖码         选择t（t+d>=3且t>=2）个不重复，且不包括在胆码中的数字作为拖码
	 * @return
	 */
	public static Set<String> permutation3_直选(String[]d,String[]t){
		if(d==null || t==null || d.length>2 || d.length<1 || d.length + t.length <3 || t.length <2 || hasDuplicate(t) || hasIntersection(d, t))
			return null;
		Set<String> set = new HashSet<String>();
		
		if(d.length==1){
			Set<String> set1 = new HashSet<String>();			
			assemble3(new String[]{d[0],d[0],d[0]}, set1);			
			List<String> tt = new ArrayList<String>();
			tt.add(d[0]);
			tt.add(d[0]);
			for(int i =0;i<t.length;i++){
				tt.add(t[i]);
				tt.add(t[i]);
			}
			t = new String[t.length*2+2];
			tt.toArray(t);
			assemble3(new String[]{d[0]},t, set1);
			for (String string : set1) 
				set.addAll(permutation3(string.split(":")));
			return set;
		}
		
		if(d.length==2 && d[0].equals(d[1])){
			Set<String> set1 = new HashSet<String>();			
			assemble3(new String[]{d[0],d[0],d[0]}, set1);			
			List<String> list1 = new ArrayList<String>();
			list1.add(d[0]);
			list1.add(d[1]);
			for(String s : t)
				list1.add(s);
			String[] t1 = new String[t.length+2];
			assemble3(d,list1.toArray(t1), set1);
			List<String> list2 = new ArrayList<String>();
			list1.remove(0);
			list2.addAll(list1);
			String[] t2 = new String[list2.size()];			
			Set<String> set2 = new HashSet<String>();		
			assemble3(list2.toArray(t2), set2);	
			set1.removeAll(set2);		
			for (String string : set1) 
				set.addAll(permutation3(string.split(":")));	
			return set;
		}
		
		if(d.length==2 && !d[0].equals(d[1])){
			Set<String> set1 = new HashSet<String>();	
			assemble3(new String[]{d[0],d[0],d[1],d[1]}, set1);	
			List<String> list1 = new ArrayList<String>();
			list1.add(d[0]);
			list1.add(d[1]);
			for(String s : t)
				list1.add(s);
			String[] t1 = new String[t.length+2];
			assemble3(d, list1.toArray(t1), set1);
			for (String string : set1) 
				set.addAll(permutation3(string.split(":")));	
			return set;
		}
		
		return set;
	}
	
	
	
	/**
	 * 得到3个数的排列数
	 * @param d 胆码         选择d（0<d<=2）个数字作为胆码
	 * @param t 拖码         选择t（t+d>=3且t>=2）个不重复，且不包括在胆码中的数字作为拖码
	 * @return
	 */
	public static Set<String> permutation3_组选(String[]d,String[]t){
		if(d==null || t==null || d.length>2 || d.length<1 || d.length + t.length <3 || t.length <2 || hasDuplicate(t) || hasIntersection(d, t))
			return null;
		Set<String> set = new HashSet<String>();
		
		if(d.length==1){
			Set<String> set1 = new HashSet<String>();			
			assemble3(new String[]{d[0],d[0],d[0]}, set1);			
			List<String> tt = new ArrayList<String>();
			tt.add(d[0]);
			tt.add(d[0]);
			for(int i =0;i<t.length;i++){
				tt.add(t[i]);
				tt.add(t[i]);
			}
			t = new String[t.length*2+2];
			tt.toArray(t);
			assemble3(new String[]{d[0]},t, set1);
			set.addAll(set1);
			return set;
		}
		
		if(d.length==2 && d[0].equals(d[1])){
			Set<String> set1 = new HashSet<String>();			
			assemble3(new String[]{d[0],d[0],d[0]}, set1);			
			List<String> list1 = new ArrayList<String>();
			list1.add(d[0]);
			list1.add(d[1]);
			for(String s : t)
				list1.add(s);
			String[] t1 = new String[t.length+2];
			assemble3(d,list1.toArray(t1), set1);
			List<String> list2 = new ArrayList<String>();
			list1.remove(0);
			list2.addAll(list1);
			String[] t2 = new String[list2.size()];			
			Set<String> set2 = new HashSet<String>();		
			assemble3(list2.toArray(t2), set2);	
			set1.removeAll(set2);		
			set.addAll(set1);
			return set;
		}
		
		if(d.length==2 && !d[0].equals(d[1])){
			Set<String> set1 = new HashSet<String>();	
			assemble3(new String[]{d[0],d[0],d[1],d[1]}, set1);	
			List<String> list1 = new ArrayList<String>();
			list1.add(d[0]);
			list1.add(d[1]);
			for(String s : t)
				list1.add(s);
			String[] t1 = new String[t.length+2];
			assemble3(d, list1.toArray(t1), set1);
			set.addAll(set1);
			return set;
		}
		
		return set;
	}

	
	/**
	 * 排列
	 * @param a
	 * @param start
	 * @param end
	 * @param set
	 */
	 public static void permute(String a[], int start, int end,
			final Set<String> set) {
		if (start == end) {
			String s = "";
			for (int i = 0; i <= end; ++i) {
				if (i < end)
					s += a[i] + ':';
				else
					s += a[i];
			}
			set.add(s);
		} else {
			for (int i = start; i <= end; ++i) {
				String t = a[i];
				a[i] = a[start];
				a[start] = t;
				permute(a, start + 1, end, set);
				t = a[i];
				a[i] = a[start];
				a[start] = t;
			}
		}
	}
	 

	
	
	/**
	 * 是否有交集
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	 public static boolean hasIntersection(String[]arr1,String[]arr2){
		 List<String> list1 = new ArrayList<String>();
		 List<String> list2 = new ArrayList<String>();		 
		 for(String s : arr1)
			 list1.add(s);
		 for(String s : arr2)
			 list2.add(s);
		 
		 return !Collections.disjoint(list1, list2);		 
	 }
	 
	/**
	 * 获得交集
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	public static <T extends Comparable<T>>T[] intersection(T[] arr1, T[] arr2) {
		List<T> list1 = new ArrayList<T>();
		List<T> list2 = new ArrayList<T>();
		Set<T> set = new HashSet<T>();
		List<T> list = new ArrayList<T>();
		for(T s1 : arr1)
			list1.add(s1);
		for(T s2 : arr2)
			list2.add(s2);
		list2.retainAll(list1);
		set.addAll(list2);
		list.addAll(set);
		for(T s : set){
			int x = hasDuplicate(arr1, s);
			int y = hasDuplicate(arr2, s);
			if(x>0 && y>0)
				for(int i=0;i<Math.min(x,y);i++)
					list.add(s);
		}
		Collections.sort(list);
		String[] arr = new String[list.size()];
		return (T[]) list.toArray(arr);
	}
		 
		 
		 /**
		  * 号码排序
		  * @param arr
		  * @return
		  */
		 public static <T extends Comparable<T>>T[] sort(T[] arr){
			 List<T> list = new ArrayList<T>();
			 for(T s : arr)
				 list.add(s);
			 Collections.sort(list);
			 return list.toArray(arr);
		 }

		 /**
		  * 是否有重复值
		  * @param arr
		  * @return
		  */
		 public static<T> boolean hasDuplicate(T[] arr){
			 for(int i=0;i<arr.length;i++)
				 for(int j=i+1;j<arr.length;j++)
					 if(arr[i].equals(arr[j]))
						 return true;
			 return false;
		 }
		 
		 /**
		  * val在arr中重复的个数
		  * @param arr
		  * @param val 
		  * @return
		  */
		 public static <T>int hasDuplicate(T[] arr,T val){
			int i = 0; 
			for(T s : arr)
				if(val.equals(s))
					i++;
			return i-1;
		 }
			
		 
		
	/**
	 * 获得一个k3和值号码	 
	 * @param num
	 * @return
	 */
	public static String[] k3NumBySum(int num){
		int[]arr = {1,2,3,4,5,6};
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr.length; j++)
				for (int l = 0; l < arr.length; l++)
					if(arr[i]+arr[j]+arr[l]==num)
						return new String[]{String.valueOf(arr[i]),String.valueOf(arr[j]),String.valueOf(arr[l])};	
		return null;
	}

		 
		/**
		 * 快3中奖判断
		 * @param code
		 * @param open
		 * @param k3 购买类型
		 * @return
		 */
	public static Award prizewinning_K3(String[] code, String[] open, int k3) {
		if(code.length!=3 || open.length!=3)
			return null;
		
		Award award = null;		
		
		int sum = Integer.parseInt(open[0]) + Integer.parseInt(open[1]) + Integer.parseInt(open[2]);
		int sum1 = Integer.parseInt(code[0]) + Integer.parseInt(code[1]) + Integer.parseInt(code[2]);
		int same = sameNumCount(open);
		int same1 = sameNumCount(code);
		
		switch (k3) {
		case K3_和值:
			switch(sum){
			case 4:
			case 17:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("1");
						award.setAward_name("和值4或17");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			case 5:
			case 16:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("2");
						award.setAward_name("和值5或16");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			case 6:
			case 15:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("3");
						award.setAward_name("和值6或15");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			case 7:
			case 14:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("4");
						award.setAward_name("和值7或14");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			case 8:
			case 13:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("5");
						award.setAward_name("和值8或13");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			case 9:
			case 12:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("6");
						award.setAward_name("和值9或12");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			case 10:
			case 11:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("7");
						award.setAward_name("和值10或11");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			}				
		break;
		case K3_三同号通选:
			if(same==3 && same1==3 && code[0].equals("0")){
				award = new Award();
				award.setAward_code("8");
				award.setAward_name("三同号通选");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;	
			}	
			break;
		case K3_三同号自选单式:
			if(same==3 && same1==3 && code[0].equals(open[0])){
				award = new Award();
				award.setAward_code("9");
				award.setAward_name("三同号单选");
				award.setLottery_name("K3");
				award.setLottery_number(code[0]+","+code[1]+","+code[2]);
				return award;	
			}				
			break;
		case K3_二同号自选复式:
			List<String> list = new ArrayList<String>();
			for(String s : code)
				list.add(s);
			Collections.sort(list);
			list.toArray(code);
			if(same>=2 && same1==2 && code[0].equals("0") && intersection(code, open).length==2){
				award = new Award();
				award.setAward_code("10");
				award.setAward_name("二同号复选");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;
			}
			break;
		case K3_二同号自选单式:
			if(same==2 && same1==2 && arrayEquals(code, open)){
				award = new Award();
				award.setAward_code("11");
				award.setAward_name("二同号单选");
				award.setLottery_name("K3");
				award.setLottery_number(code[0]+","+code[1]+","+code[2]);
				return award;	
			}
			break;
		case K3_三不同号自选:
			if(same==1 && same1==1 && arrayEquals(code, open)){
				award = new Award();
				award.setAward_code("12");
				award.setAward_name("三不同号");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;	
			}
			break;
		case K3_二不同号自选:
			List<String> listx = new ArrayList<String>();
			for(String s : code)
				listx.add(s);
			Collections.sort(listx);
			listx.toArray(code);
			if(same1==1 && code[0].equals("0") && intersection(code, open).length==2){
				award = new Award();
				award.setAward_code("13");
				award.setAward_name("二不同号");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;
			}
			break;
		case K3_三连号通选:
			if(same1==3 && code[0].equals("0") && isContinuation(open)){
				award = new Award();
				award.setAward_code("14");
				award.setAward_name("三连号通选");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;	
			}
			break;
			
			
		}
		
		
		return award;
	}
	
	/**
	 * 数组是否相同
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static boolean arrayEquals(String[] arr1,String[] arr2){
		if(arr1.length!=arr2.length)
			return false;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		for(String s : arr1)
			list1.add(s);
		for(String s : arr2)
			list2.add(s);
		Collections.sort(list1);
		Collections.sort(list2);
		return list1.equals(list2);
	}
	
	/**
	 * 数组中相同数的个数(只能判断3个数的数组)
	 * @param num
	 * @return 返回1则没有都不相同
	 */
	@Deprecated
	public static int sameNumCount(String[] num){
		Set<String> set = new HashSet<String>();
		for(String s : num)
			set.add(s);	
		return num.length - set.size() + 1;
	}
	
	/**
	 * 是否是号码连续的数组
	 * @param num
	 * @return
	 */
	public static boolean isContinuation(String[] num){
		List<String>list = new ArrayList<String>();
		for(String s : num)
			list.add(s);
		Collections.sort(list);
		for(int i=0;i<list.size();i++)
			if(i!=list.size()-1 && !String.valueOf(Integer.parseInt(list.get(i))+1).equals(list.get(i+1)))
				return false;
		return true;		
	}

		 public static final int K3_和值 = 1;		 
		 public static final int K3_三同号自选单式 = 2;
		 public static final int K3_三同号通选 = 3;
		 public static final int K3_三不同号自选 = 6;
		 public static final int K3_二同号自选单式 = 4;
		 public static final int K3_二同号自选复式 = 5;
		 public static final int K3_二不同号自选 = 7;
		 public static final int K3_三连号通选 = 8;

}
