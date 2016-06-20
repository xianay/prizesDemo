package com.tyc.lottery.award.prizes.proc.k3;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.K3AwardProcesser;


/**
 * 快3三同号通选
 * @author Administrator
 *
 */
public class K33THTXAwardProcesser extends AwardProcesserImpl implements K3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();
		
		if(!openNum.matches("([0-9][,]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		String[]open = openNum.split(",");
		
		if(lottery_number.matches("([0][,]){2}[0]")){									
			open = AwardUtils.sort(open);
			Award award = AwardUtils.prizewinning_K3(lottery_number.split(","), open, AwardUtils.K3_三同号通选);		
			if (award != null)
				list.add(award);
			return list;
		}
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("([0][,]){2}[0]");
	}

	
	
	
	
	
	

}
