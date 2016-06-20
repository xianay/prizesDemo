package com.tyc.lottery.award.prizes.proc.k3;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.K3AwardProcesser;


/**
 * 快3和值(4~17)
 * @author Administrator
 *
 */
public class K3HZAwardProcesser extends AwardProcesserImpl implements K3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();
		String[]open = openNum.split(",");
		
		if(open.length!=3)
			throw new AwardException("开奖号码有误.");
		if(lottery_number.matches("(([4-9]|[1][0-7])[#]){0,4}([4-9]|[1][0-7])")){
			String lotteryNums[] = lottery_number.split("#");
			for (String string : lotteryNums) {
				int num = Integer.parseInt(string);
				if(num>17 || num<4)
					throw new AwardException("快3 和值超出范围 (4~17)");
				
				open = AwardUtils.sort(open);
				Award award = AwardUtils.prizewinning_K3(AwardUtils.k3NumBySum(num), open, AwardUtils.K3_和值);
				
				if (award != null)
					list.add(award);
			}
			
			return list;
		}
		throw new AwardException("号码格式不匹配");
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("(([4-9]|[1][0-7])[#]){0,4}([4-9]|[1][0-7])");
	}
	

	
	
	
	

}
