package com.tyc.lottery.award.prizes.proc.d3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.D3AwardProcesser;

/**
 * 3d 组三复式
 * <br>例：1,2,3,4 从[0-9]中任选(2~10)个号码进行组选三的投注。
 * @author anyi
 *
 */
public class D3ZuX3FSAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
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
					//System.out.println(string);
					award = AwardUtils.prizewinning_D3_组选3(arr1, open);
					break;
				}
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		return lottery_number.matches("([0-9][,]){1,9}[0-9]");
	}
	
	

}
