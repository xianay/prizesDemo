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
 * 3d 直选胆拖
 * <br>例： 1,2:3,5,6 (胆码可以重复且选择1~2个，拖码不可重复且不能和胆码相同,选择至少2个以上)
 * @author anyi
 *
 */
public class D3ZXDTAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*")){			
			String[] s = lottery_number.split(":");
			String[][] arr = new String[2][];
			for(int i=0;i<2;i++)
				arr[i] = s[i].split(",");	
			Set<String> set = AwardUtils.permutation3_直选(arr[0], arr[1]);
			for (String string : set) {
				//System.out.println(string);
				Award award = AwardUtils.prizewinning_D3_直选(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			//System.out.println(set.size());
			return list;
		}
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*");
	}
	


}
