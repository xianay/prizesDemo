package com.tyc.lottery.award.prizes.proc.d3new;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardFactory;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.D3AwardProcesser;

/**
 * 3d 1D投注
 * <br>
    1D投注是指在投注时，从000至999中任意选一个3位自然数的百位、十位或个位数字作为投注号码进行的单注投注。1D投注只设置单选投注，没有组选投注。
“1D”投注的单注投注金额为2元，单注奖金为10元。
 * @author anyi
 *
 */
public class D31DAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	
	public static void main(String[] args) throws AwardException {
		AwardProcesser awardProcesser = AwardFactory.getAwardProcesser("D3.21");
		System.out.println(awardProcesser.prize("1:2:2", "f:f:2#1:f:f"));
	}	
	
	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("开奖号码有误.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("((([0-9]:f:f)|(f:f:[0-9])|(f:[0-9]:f))#){0,4}(([0-9]:f:f)|(f:f:[0-9])|(f:[0-9]:f))")){			
			String lotteryNums[] = lottery_number.split("#");
			for (String num : lotteryNums) {
				
				String[] arr = num.split(":");
				Award award = null;				
				award = AwardUtils.prizewinning_D3new_1D(arr, open);				
				if (award != null)
					list.add(award);
			}

			return list;
		}
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		return lottery_number.matches("((([0-9]:f:f)|(f:f:[0-9])|(f:[0-9]:f))#){0,4}(([0-9]:f:f)|(f:f:[0-9])|(f:[0-9]:f))");
	}
	
	

}
