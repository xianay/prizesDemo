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
 * 3d 直选跨度
 * <br>例：2 (跨度：该注号码中最大号码 – 最小号码 = 跨度) 范围 1~9
 * @author anyi
 *
 */
public class D3ZXKDAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9]+")){
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
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("[0-9]+");
	}

}
