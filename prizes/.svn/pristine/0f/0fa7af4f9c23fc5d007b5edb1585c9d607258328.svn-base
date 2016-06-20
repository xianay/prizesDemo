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
 * 3d 组选报点
 * <br>例：2 把和值为这一点的所有组合分类，三个数相同的填为直选，两个数相同的填为组三，三个数各不相同的填为组六。
 * @author anyi
 *
 */
public class D3ZuXBDianAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9]+")){
			int num = Integer.parseInt(lottery_number);
			if(num<0 || num>27)
				throw new AwardException("号码包点数值不正确(0~27)" + " --> " + num);
			Set<String> set = AwardUtils.permutation3(num);
			AwardUtils.D3RemoveDuplicate(set);
			for (String string : set) {
				String[] arr = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr)){
//				case AwardUtils.D3_直选:
//					award = AwardUtils.prizewinning_D3_直选(arr, open);
//					break;
				case AwardUtils.D3_组选3:
					award = AwardUtils.prizewinning_D3_组选3(arr, open);
					System.out.println("组选3 -> "+string);
					break;
				case AwardUtils.D3_组选6:
					award = AwardUtils.prizewinning_D3_组选6(arr, open);
					System.out.println("组选6 -> "+string);
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
		
		return lottery_number.matches("[0-9]+");
	}

}
