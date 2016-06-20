package com.tyc.lottery.award.prizes.proc.d3;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.D3AwardProcesser;

/**
 * 3d 组选投注
 * <br>例： 组选投注  1:2:3#2:3:4#1:2:2 (组三，组六混合投注)
 * @author anyi
 *
 */
public class D3ZuXTZAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("(([0-9][:]){2}[0-9][#]){0,4}([0-9][:]){2}[0-9]")){
			String lotteryNums[] = lottery_number.split("#");
			for (String num : lotteryNums) {
				String[] arr = num.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr)){
				case AwardUtils.D3_直选:
					throw new AwardException("组选混投 不能有豹子号投注 ");
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
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("(([0-9][:]){2}[0-9][#]){0,4}([0-9][:]){2}[0-9]");
	}

}
