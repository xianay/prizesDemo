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
 * 3d 组选3投注兑奖
 * <br>例：
 * <br>1.组三复式  1,2,3,4 从[0-9]中任选(2~10)个号码进行组选三的投注。
 * <br>2.跨度组选三 ~2 所有跨度相同的组三。
 * @author anyi
 */
@Deprecated
public class D3ZuX3AwardProcesser extends AwardProcesserImpl implements AwardProcesser {


	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		String[]open = openNum.split(":");
	
		
		if(lottery_number.matches("[~]{1}[0-9]+")){
			int num = Integer.parseInt(lottery_number.replace("~", ""));
			if(num<1 || num>9)
				throw new AwardException("号码跨度数值不正确(1~9)" + " --> " + num);
			Set<String> set = AwardUtils.permutation3_跨度(num);
			for (String string : set) {
				String[] arr = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr)){
				case AwardUtils.D3_组选3:
					award = AwardUtils.prizewinning_D3_组选3(arr, open);
					break;
				}
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("([0-9][,]){1,9}[0-9]")){			
			String[] s = lottery_number.split(",");
			if(AwardUtils.hasDuplicate(s))
				throw new AwardException("组3复式投注 号码不能有重复值");
			String[][] arr = new String[3][];
			for(int i=0;i<arr.length;i++)
				arr[i] = s;	
			Set<String> set = AwardUtils.permutation3(arr);
			AwardUtils.D3RemoveDuplicate(set);
			for (String string : set) {
				String[] arr1 = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr1)){
				case AwardUtils.D3_组选3:
					award = AwardUtils.prizewinning_D3_组选3(arr1, open);
					break;
				}
				if (award != null)
					list.add(award);
			}
			return list;
		}

		throw new AwardException(this.getClass().getName() + " --> 号码格式不匹配。");
	}

	@Override
	public boolean validation(String lottery_number) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
