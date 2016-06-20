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
 * 3d 组选胆拖
 * <br>例： 2:3,5 组选胆拖投注是投注者从0~9个选择M（0<M<=2）个数字作为胆码，选择N（N+M>=3且N>=2）个不重复，且不包括在胆码中的数字作为拖码，并对这些数字所能组成的所有含胆码的组选号码组合进行全包投注，三个数相同的填为直选，两个数相同的填为组三，三个数各不相同的填为组六。
 * @author anyi
 *
 */
public class D3ZuXDTAwardProcesser  extends AwardProcesserImpl implements D3AwardProcesser{

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
			Set<String> set = AwardUtils.permutation3_组选(arr[0], arr[1]);
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
		return lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*");
	}

}
