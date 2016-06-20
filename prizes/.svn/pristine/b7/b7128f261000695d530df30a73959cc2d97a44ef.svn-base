package com.tyc.lottery.award.prizes.proc.d3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.D3AwardProcesser;

/**
 * 3d 直选包号
 * <br>例： ff2
 * @author anyi
 *
 */
public class D3ZXBHAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

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
				throw new AwardException("直选包号 最多2个f ---> " + lottery_number);
			Set<String> set = AwardUtils.permutation3(ss, "f");
			for (String string : set) {
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}
	
	public static void main(String[] args) throws AwardException {
		AwardProcesser awardProcesser = new D3ZXBHAwardProcesser();
		List<Award> awards = awardProcesser.prize("1:2:3", "f2f");
		System.out.println(awards);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("[0-9f][0-9f][0-9f]")&&!lottery_number.matches("[0-9]+");
	}
	

}
