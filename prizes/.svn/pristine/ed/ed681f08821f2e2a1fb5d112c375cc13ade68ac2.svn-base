package com.tyc.lottery.award.prizes.proc.deprecated;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;



/**
 * 3d 组选投注兑奖
 * <br>例：
 * <br>1.组选投注  1:2:3#2:3:4#1:2:2 (组三，组六混合投注)
 * <br>2.组选报点  2 把和值为这一点的所有组合分类，三个数相同的填为直选，两个数相同的填为组三，三个数各不相同的填为组六。
 * <br>3.组选包胆  ff2 把包含这一个或两个数的所有组合分类，三个数相同的填为直选，两个数相同的填为组三，三个数各不相同的填为组六。
 * <br>4.组选胆拖  2:3,5 组选胆拖投注是投注者从0~9个选择M（0<M<=2）个数字作为胆码，选择N（N+M>=3且N>=2）个不重复，且不包括在胆码中的数字作为拖码，并对这些数字所能组成的所有含胆码的组选号码组合进行全包投注，三个数相同的填为直选，两个数相同的填为组三，三个数各不相同的填为组六。
 * @author anyi
 *
 */
@Deprecated
public class D3ZuXAwardProcesser extends AwardProcesserImpl implements AwardProcesser{


	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {		
		List<Award> list = new ArrayList<Award>();
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9][:][0-9][:][0-9]")){
			String[] arr = lottery_number.split(":");
			Award award = null;				
			switch(AwardUtils.D3BettingFormat(arr)){
			case AwardUtils.D3_直选:
				throw new AwardException("组选不能有豹子号投注 ");
			case AwardUtils.D3_组选3:
				award = AwardUtils.prizewinning_D3_组选3(arr, open);
				break;
			case AwardUtils.D3_组选6:
				award = AwardUtils.prizewinning_D3_组选6(arr, open);
				break;
			}
			if (award != null)
				list.add(award);
		return list;
	}
	
	if(lottery_number.contains("#")){
		String lotteryNums[] = lottery_number.split("#");
		if(lotteryNums.length>5)
			throw new AwardException("不能超过5注");
		for (String num : lotteryNums) {
			String[] arr = num.split(":");
			Award award = null;				
			switch(AwardUtils.D3BettingFormat(arr)){
			case AwardUtils.D3_直选:
				throw new AwardException("组选3 不能有豹子号投注 ");
			case AwardUtils.D3_组选3:
				award = AwardUtils.prizewinning_D3_组选3(arr, open);
				break;
			case AwardUtils.D3_组选6:
				award = AwardUtils.prizewinning_D3_组选6(arr, open);
				break;
			}
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
			String[] arr = string.split(":");
			Award award = null;				
			switch(AwardUtils.D3BettingFormat(arr)){
			case AwardUtils.D3_直选:
				award = AwardUtils.prizewinning_D3_直选(arr, open);
				break;
			case AwardUtils.D3_组选3:
				award = AwardUtils.prizewinning_D3_组选3(arr, open);
				break;
			case AwardUtils.D3_组选6:
				award = AwardUtils.prizewinning_D3_组选6(arr, open);
				break;
			}
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
		AwardUtils.D3RemoveDuplicate(set);
		for (String string : set) {
			String[] arr1 = string.split(":");
			Award award = null;				
			switch(AwardUtils.D3BettingFormat(arr1)){
			case AwardUtils.D3_直选:
				award = AwardUtils.prizewinning_D3_直选(arr1, open);
				break;
			case AwardUtils.D3_组选3:
				award = AwardUtils.prizewinning_D3_组选3(arr1, open);
				break;
			case AwardUtils.D3_组选6:
				award = AwardUtils.prizewinning_D3_组选6(arr1, open);
				break;
			}
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
		Set<String> set = AwardUtils.permutation3_组选(arr[0], arr[1]);
		for (String string : set) {
			String[] arr1 = string.split(":");
			Award award = null;				
			switch(AwardUtils.D3BettingFormat(arr1)){
			case AwardUtils.D3_直选:
				award = AwardUtils.prizewinning_D3_直选(arr1, open);
				break;
			case AwardUtils.D3_组选3:
				award = AwardUtils.prizewinning_D3_组选3(arr1, open);
				break;
			case AwardUtils.D3_组选6:
				award = AwardUtils.prizewinning_D3_组选6(arr1, open);
				break;
			}
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
