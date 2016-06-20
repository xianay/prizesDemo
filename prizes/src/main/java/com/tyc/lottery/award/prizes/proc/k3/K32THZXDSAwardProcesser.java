package com.tyc.lottery.award.prizes.proc.k3;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.K3AwardProcesser;


/**
 * 快3二同号自选单式
 * @author Administrator
 *
 */
public class K32THZXDSAwardProcesser extends AwardProcesserImpl implements K3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();
		
		if(!openNum.matches("([0-9][,]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		String[]open = openNum.split(",");
		
		if(lottery_number.matches("(([0-9][,]){2}[0-9][#]){0,4}([0-9][,]){2}[0-9]")){									
			open = AwardUtils.sort(open);
			String lotteryNums[] = lottery_number.split("#");
			for (String string : lotteryNums) {
				Award award = AwardUtils.prizewinning_K3(string.split(","), open, AwardUtils.K3_二同号自选单式);		
				if (award != null)
					list.add(award);
			}
			return list;
		}
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
	
		return lottery_number.matches("(([0-9][,]){2}[0-9][#]){0,4}([0-9][,]){2}[0-9]");
	}
	
	
	
	
	
	
	

}
