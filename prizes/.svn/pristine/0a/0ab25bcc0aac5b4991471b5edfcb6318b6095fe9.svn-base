package com.tyc.lottery.award.prizes.proc.qlc;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.QlcAwardProcesser;



/**
 * ÆßÀÖ²Êµ¥Ê½¶Ò½± 
 * <br>Àý£º
 * <br>01,02,03,04,05,06,07#11,02,03,04,05,09,10
 * @author anyi
 *
 */
public class QlcDSAwardProcesser extends AwardProcesserImpl implements QlcAwardProcesser {



	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		
		if(!openNum.matches("((0\\d|[1-2]\\d|30),){7}(0\\d|[1-2]\\d|30)"))
			throw new AwardException("¿ª½±ºÅÂëÓÐÎó.");
		
		String[]open = new String[7];
		System.arraycopy(openNum.split(","), 0, open, 0, 7);
		String special = openNum.split(",")[7];
		if(lottery_number.matches("(((0\\d|[1-2]\\d|30),){6}(0\\d|[1-2]\\d|30)#){0,4}((0\\d|[1-2]\\d|30),){6}(0\\d|[1-2]\\d|30)")){
			String lotteryNums[] = lottery_number.split("#");
			setTicketAmount(lotteryNums.length);

			for (String num : lotteryNums) {
				String[] red = num.split(",");			
				int r = AwardUtils.compare_QLC_RED(red, open);
				int b = AwardUtils.compare_QLC_SPECIAL(red, special);
				Award award = AwardUtils.createQLCprize(r+"+"+b,num);
				if(award!=null)
					list.add(award);
			}
			return list;
		}
		throw new AwardException("ºÅÂë¸ñÊ½²»Æ¥Åä ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("(((0\\d|[1-2]\\d|30),){6}(0\\d|[1-2]\\d|30)#){0,4}((0\\d|[1-2]\\d|30),){6}(0\\d|[1-2]\\d|30)");
	}
	
	



}
