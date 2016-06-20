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
 * 3d 组选包胆
 * <br>例： ff2 把包含这一个或两个数的所有组合分类，三个数相同的填为直选，两个数相同的填为组三，三个数各不相同的填为组六。
 * @author anyi
 *
 */
public class D3ZuXBDanAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9f][0-9f][0-9f]")&&!lottery_number.matches("[0-9]+")){			
			String[] s = lottery_number.trim().split("");
			String[] ss = new String[3];
			System.arraycopy(s, 1, ss, 0, 3);
			if(AwardUtils.hasDuplicate(ss,"f")>1)
				throw new AwardException("组选包胆 最多2个f ---> " + lottery_number);
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
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("[0-9f][0-9f][0-9f]")&&!lottery_number.matches("[0-9]+");
	}

}
