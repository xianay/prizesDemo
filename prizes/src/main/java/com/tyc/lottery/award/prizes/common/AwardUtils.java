package com.tyc.lottery.award.prizes.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ��Ʊ����
 * @author anyi
 *
 */
public class AwardUtils {

	
	
	
	 public static final int D3_ֱѡ = 1;
	 public static final int D3_��ѡ3 = 2;
	 public static final int D3_��ѡ6 = 3;
	 
	 /**
	  * ����2������λ�ü�������ͬ��Ԫ�ظ���
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
	  * ���� arr ������ �ж� ��������Ͷע����
	  * @param arr
	  * @return
	  * @see AwardUtils.D3_ֱѡ
	  * @see AwardUtils.D3_��ѡ3
	  * @see AwardUtils.D3_��ѡ6
	  */
	 public static int D3BettingFormat(String[] arr){
		 Set<String> set = new HashSet<String>();
		 for(String s : arr)
			 set.add(s);
		 switch(set.size()){
		 case 1:
			 return D3_ֱѡ;
		 case 2:
			 return D3_��ѡ3;
		 case 3:
			 return D3_��ѡ6;
		 }
		return 0;
	 }
	 /**
		 * ȥ���ظ�(3d) ����1:2:3 �� 3:2:1 �ظ�
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
	 * ˫ɫ�� Nѡ6�������
	 * @param required �����������
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
	 * ˫ɫ�� Nѡ6�������
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
//		assemble3(new String[]{"ʤ","ƽ","��","ʤ","ƽ","��","ʤ","ƽ","��"}, set);
//	
//		for (String string : set) {
//			System.out.println(string);
//		}
//		System.out.println(set.size());
		
		
//		Set<String> set1 = new HashSet<String>();
//		assemble2(new String[]{"ʤ","ƽ","��","ʤ","ƽ","��"}, set1);
//	
//		for (String string : set1) {
//			System.out.println(string);
//		}
//		System.out.println(set1.size());
		
		System.out.println(sameNumbers("1:2:3".split(":"), "1:f:3".split(":")));
		
		
	}
	
	/**
	 * ���ֲ� Nѡ7�������
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
	 * ���ֲ� Nѡ7�������
	 * @param required �����������
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
	 * D3 Nѡ3�������
	 * @param required �����������
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
	 * D3 Nѡ3�������
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
	 * �����ַ�������˫ɫ�򽱵�
	 * @param s
	 * @return
	 */
	public static Award createSSQprize(String s,String lottery_number){
		Award award = null;
		String[] names = {"һ�Ƚ�","���Ƚ�","���Ƚ�","�ĵȽ�","��Ƚ�","���Ƚ�"};
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
	 * ˫ɫ������н�����
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
	 * ˫ɫ�������н�����
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
	 * ���ֲʺ����н�����
	 * @param red  �߸�����(1ע) �ҽ�����
	 * @param open �߸�����(1ע) ��������
	 * @return ��ͬ�������
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
	 * ���ֲ�������н�����
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
	 * �����ַ����������ֲʽ���
	 * @param s
	 * @return
	 */
	public static Award createQLCprize(String s,String lottery_number){
		Award award = null;
		String[] names = {"һ�Ƚ�","���Ƚ�","���Ƚ�","�ĵȽ�","��Ƚ�","���Ƚ�","�ߵȽ�"};
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
	 * ��3d��Ʊ 1DͶע
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3new_1D(String[]code,String[] open){
		if(sameNumbers(code, open)!=1)return null;
		
		Award award = new Award();
		award.setLottery_name("D3");
		award.setAward_name("1D�н�");
		award.setAward_code("4");
		award.setLottery_number(open[0]+":"+open[1]+":"+open[2]);
		return award;
	}
	
	/**
	 * ��3d��Ʊ 2DͶע
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3new_2D(String[]code,String[] open){
		if(sameNumbers(code, open)!=2)return null;
		
		Award award = new Award();
		award.setLottery_name("D3");
		award.setAward_name("2D�н�");
		award.setAward_code("5");
		award.setLottery_number(open[0]+":"+open[1]+":"+open[2]);
		return award;
	}
	
	/**
	 * 3d��Ʊ ֱѡͶע�н��ж�
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3_ֱѡ(String[] code,String[] open){
		if(code.length != 3 || code.length != open.length)
			return null;
		
		for(int i=0;i<3;i++)
			if(!open[i].equals(code[i]))
				return null;
		
		Award award = new Award();
		award.setLottery_name("D3");
		award.setAward_name("ֱѡ�н�");
		award.setAward_code("1");
		award.setLottery_number(open[0]+":"+open[1]+":"+open[2]);
		return award;
	}
	
	/**
	 * 3d��Ʊ ��ѡ3Ͷע�н��ж�
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3_��ѡ3(String[] code,String[] open){
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
				award.setAward_name("��ѡ3�н�");
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
	 * 3d��Ʊ ��ѡ6Ͷע�н��ж�
	 * @param code
	 * @param open
	 * @return
	 */
	public static Award prizewinning_D3_��ѡ6(String[] code,String[] open){
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
				award.setAward_name("��ѡ6�н�");
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
	 * �õ�3������������ (Nѡ3)
	 * @param arr ����ΪN������
	 * @return Nѡ3 ��������� ����
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
	 * �õ�3������������
	 * @param num ��:�Ÿ������ַ���  ����(1:2:3)
	 * @return ������ļ��� ����(1:2:3 , 1:3:2 , 2:1:3 , 2:3:1 , 3:1:2 , 3:2:1)
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
	 * �õ�3������������(�� ��Ӧ arr��������е� ch �滻�� 0-9)
	 * @param arr 3��������� ����([1] [*] [2])
	 * @param f �滻���ַ�
	 * @return [*:*:*],... ��������ϵļ���
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
	 * �õ�3������������
	 * @param num  ��ֵ
	 * @return 3������ӵ��� num ����λ������
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
	 * �õ�3������������
	 * @param num  ��� (1~9)
	 * @return 3���� ������ �C ��С���� = ���
	 */
	public static Set<String> permutation3_���(int num){
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
	 * �õ�3������������
	 * @return ȫ������270ע
	 */
	public static Set<String> permutation3_ȫ����(){
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
	 * �õ�3������������
	 * @param arr �������������Ķ�λ���� ����
	 * 
	 * <br> ��λ2�����루0��1��
	 * <br> ʮλ3�����루2��3��4��
	 * <br> ��λ2�����루5��6��
	 * @return ���������ļ���
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
	 * �õ�3������������
	 * @param d ����         ѡ��d��0<d<=2����������Ϊ����
	 * @param t ����         ѡ��t��t+d>=3��t>=2�������ظ����Ҳ������ڵ����е�������Ϊ����
	 * @return
	 */
	public static Set<String> permutation3_ֱѡ(String[]d,String[]t){
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
	 * �õ�3������������
	 * @param d ����         ѡ��d��0<d<=2����������Ϊ����
	 * @param t ����         ѡ��t��t+d>=3��t>=2�������ظ����Ҳ������ڵ����е�������Ϊ����
	 * @return
	 */
	public static Set<String> permutation3_��ѡ(String[]d,String[]t){
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
	 * ����
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
	 * �Ƿ��н���
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
	 * ��ý���
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
		  * ��������
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
		  * �Ƿ����ظ�ֵ
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
		  * val��arr���ظ��ĸ���
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
	 * ���һ��k3��ֵ����	 
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
		 * ��3�н��ж�
		 * @param code
		 * @param open
		 * @param k3 ��������
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
		case K3_��ֵ:
			switch(sum){
			case 4:
			case 17:
				if(sum1==sum){
						award = new Award();
						award.setAward_code("1");
						award.setAward_name("��ֵ4��17");
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
						award.setAward_name("��ֵ5��16");
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
						award.setAward_name("��ֵ6��15");
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
						award.setAward_name("��ֵ7��14");
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
						award.setAward_name("��ֵ8��13");
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
						award.setAward_name("��ֵ9��12");
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
						award.setAward_name("��ֵ10��11");
						award.setLottery_name("K3");
						award.setLottery_number(open[0]+","+open[1]+","+open[2]);
						return award;	
				}
				break;
			}				
		break;
		case K3_��ͬ��ͨѡ:
			if(same==3 && same1==3 && code[0].equals("0")){
				award = new Award();
				award.setAward_code("8");
				award.setAward_name("��ͬ��ͨѡ");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;	
			}	
			break;
		case K3_��ͬ����ѡ��ʽ:
			if(same==3 && same1==3 && code[0].equals(open[0])){
				award = new Award();
				award.setAward_code("9");
				award.setAward_name("��ͬ�ŵ�ѡ");
				award.setLottery_name("K3");
				award.setLottery_number(code[0]+","+code[1]+","+code[2]);
				return award;	
			}				
			break;
		case K3_��ͬ����ѡ��ʽ:
			List<String> list = new ArrayList<String>();
			for(String s : code)
				list.add(s);
			Collections.sort(list);
			list.toArray(code);
			if(same>=2 && same1==2 && code[0].equals("0") && intersection(code, open).length==2){
				award = new Award();
				award.setAward_code("10");
				award.setAward_name("��ͬ�Ÿ�ѡ");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;
			}
			break;
		case K3_��ͬ����ѡ��ʽ:
			if(same==2 && same1==2 && arrayEquals(code, open)){
				award = new Award();
				award.setAward_code("11");
				award.setAward_name("��ͬ�ŵ�ѡ");
				award.setLottery_name("K3");
				award.setLottery_number(code[0]+","+code[1]+","+code[2]);
				return award;	
			}
			break;
		case K3_����ͬ����ѡ:
			if(same==1 && same1==1 && arrayEquals(code, open)){
				award = new Award();
				award.setAward_code("12");
				award.setAward_name("����ͬ��");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;	
			}
			break;
		case K3_����ͬ����ѡ:
			List<String> listx = new ArrayList<String>();
			for(String s : code)
				listx.add(s);
			Collections.sort(listx);
			listx.toArray(code);
			if(same1==1 && code[0].equals("0") && intersection(code, open).length==2){
				award = new Award();
				award.setAward_code("13");
				award.setAward_name("����ͬ��");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;
			}
			break;
		case K3_������ͨѡ:
			if(same1==3 && code[0].equals("0") && isContinuation(open)){
				award = new Award();
				award.setAward_code("14");
				award.setAward_name("������ͨѡ");
				award.setLottery_name("K3");
				award.setLottery_number(open[0]+","+open[1]+","+open[2]);
				return award;	
			}
			break;
			
			
		}
		
		
		return award;
	}
	
	/**
	 * �����Ƿ���ͬ
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
	 * ��������ͬ���ĸ���(ֻ���ж�3����������)
	 * @param num
	 * @return ����1��û�ж�����ͬ
	 */
	@Deprecated
	public static int sameNumCount(String[] num){
		Set<String> set = new HashSet<String>();
		for(String s : num)
			set.add(s);	
		return num.length - set.size() + 1;
	}
	
	/**
	 * �Ƿ��Ǻ�������������
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

		 public static final int K3_��ֵ = 1;		 
		 public static final int K3_��ͬ����ѡ��ʽ = 2;
		 public static final int K3_��ͬ��ͨѡ = 3;
		 public static final int K3_����ͬ����ѡ = 6;
		 public static final int K3_��ͬ����ѡ��ʽ = 4;
		 public static final int K3_��ͬ����ѡ��ʽ = 5;
		 public static final int K3_����ͬ����ѡ = 7;
		 public static final int K3_������ͨѡ = 8;

}