package com.tyc.lottery.award.prizes.proc.deprecated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;



/**
 * 3d 直选投注兑奖
 * <br>例： 
 * <br>1.直选投注  1:2:3#2:3:4#3:4:5
 * <br>2.直选包号  ff2
 * <br>3.直选报点  5
 * <br>4.直选复式  1,2:3,4:5,6 (百，十，个)
 * <br>5.直选包胆  dd2
 * <br>6.直选报串  1,2,3,4,5 (2~7个数)
 * <br>7.直选胆拖  1,2:3,5,6 (胆码可以重复且选择1~2个，拖码不可重复且不能和胆码相同,选择至少2个以上)
 * <br>8.直选跨度  ~2 (跨度：该注号码中最大号码 – 最小号码 = 跨度) 范围 1~9
 * <br>9.直选全组三  all (所有组三的号码  共270注)
 * <br>10.直选组六复式  @1,2,3,4 (选择的号码个数 4~8)
 * @author anyi
 *
 */
@Deprecated
public class D3ZhiXAwardProcesser extends AwardProcesserImpl implements AwardProcesser {


	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9][:][0-9][:][0-9]")){
				Award award = AwardUtils.prizewinning_D3_直选(lottery_number.split(":"), open);
				if (award != null)
					list.add(award);
			return list;
		}
		
		if(lottery_number.contains("#")){
			String lotteryNums[] = lottery_number.split("#");
			if(lotteryNums.length>5)
				throw new AwardException("不能超过5注");
			for (String num : lotteryNums) {
				Award award = AwardUtils.prizewinning_D3_直选(num.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[0-9f][0-9f][0-9f]")&&!lottery_number.matches("[0-9]+")){			
			String[] s = lottery_number.trim().split("");
			String[] ss = new String[3];
			System.arraycopy(s, 1, ss, 0, 3);
			
			Set<String> set = AwardUtils.permutation3(ss, "f");
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[~]{1}[0-9]+")){
			int num = Integer.parseInt(lottery_number.replace("~", ""));
			if(num<1 || num>9)
				throw new AwardException("号码跨度数值不正确(1~9)" + " --> " + num);
			Set<String> set = AwardUtils.permutation3_跨度(num);
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[0-9]+")){
			int num = Integer.parseInt(lottery_number);
			if(num<0 || num>18)
				throw new AwardException("号码包点数值不正确(0~18)" + " --> " + num);
			Set<String> set = AwardUtils.permutation3(num);
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*")){
			String[] s = lottery_number.split(":");
			String[][] arr = new String[s.length][];
			for(int i=0;i<arr.length;i++)
				arr[i] = s[i].split(",");	
			Set<String> set = AwardUtils.permutation3(arr);
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[0-9d][0-9d][0-9d]")&&!lottery_number.matches("[0-9]+")){			
			String[] s = lottery_number.trim().split("");
			String[] ss = new String[3];
			System.arraycopy(s, 1, ss, 0, 3);
			Set<String> set = new HashSet<String>();
			Set<String> codes = AwardUtils.permutation3(ss[0]+":"+ss[1]+":"+ss[2]);
			for (String string : codes) 
				set.addAll(AwardUtils.permutation3(string.split(":"), "d"));
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("([0-9][,]){1,6}[0-9]")){			
			String[] s = lottery_number.split(",");
			String[][] arr = new String[3][];
			for(int i=0;i<arr.length;i++)
				arr[i] = s;	
			Set<String> set = AwardUtils.permutation3(arr);
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[@]([0-9][,]){3,7}[0-9]")){
			lottery_number = lottery_number.replace("@", "");
			String[] s = lottery_number.split(",");
			Set<String> set = new HashSet<String>();
			Set<String> set1 = new HashSet<String>();
			AwardUtils.assemble3(s, set1);		
			for (String string : set1) 
				set.addAll(AwardUtils.permutation3(string.split(":")));
			for(String string : set){
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*")){			
			String[] s = lottery_number.split(":");
			String[][] arr = new String[2][];
			for(int i=0;i<2;i++)
				arr[i] = s[i].split(",");	
			Set<String> set = AwardUtils.permutation3_直选(arr[0], arr[1]);
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if("all".equals(lottery_number)){			
			Set<String> set = AwardUtils.permutation3_全组三();
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		throw new AwardException(this.getClass().getName() + " --> 号码格式不匹配。");
	}

	@Deprecated
	@Override
	public boolean validation(String lottery_number) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
