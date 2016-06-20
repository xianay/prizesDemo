package com.tyc.lottery.award.prizes.proc.d3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.D3AwardProcesser;

/**
 * 3d 直选包胆
 * <br>例：直选包胆  ff2
 * @author anyi
 *
 */
public class D3ZXBDanAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

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
				throw new AwardException("直选包胆 最多2个f ---> " + lottery_number);
			Set<String> set = new HashSet<String>();
			Set<String> codes = AwardUtils.permutation3(ss[0]+":"+ss[1]+":"+ss[2]);
			for (String string : codes) 
				set.addAll(AwardUtils.permutation3(string.split(":"), "f"));
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
		
		return lottery_number.matches("[0-9f][0-9f][0-9f]")&&!lottery_number.matches("[0-9]+");
	}

}
