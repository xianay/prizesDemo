package com.tyc.lottery.award.prizes.proc.qlc;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.QlcAwardProcesser;



/**
 * ÆßÀÖ²Êµ¨ÍÏ¶Ò½±
 * <br>Àý£º
 * <br>01,02,03:04,05,06,07,08,09,10
 * @author anyi
 *
 */
public class QlcDTAwardProcesser extends AwardProcesserImpl implements QlcAwardProcesser {


	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		if(!openNum.matches("((0\\d|[1-2]\\d|30),){7}(0\\d|[1-2]\\d|30)"))
			throw new AwardException("¿ª½±ºÅÂëÓÐÎó.");
		String[]open = new String[7];
		System.arraycopy(openNum.split(","), 0, open, 0, 7);
		String special = openNum.split(",")[7];

		if(lottery_number.matches("((0\\d|[1-2]\\d|30),){0,5}(0\\d|[1-2]\\d|30):((0\\d|[1-2]\\d|30),){1,28}(0\\d|[1-2]\\d|30)")){
			String[] s = lottery_number.split(":");
			String[] dRed = s[0].split(",");
			String[] tRed = s[1].split(",");
			if(dRed.length + tRed.length <7)
				throw new AwardException("ºìÇòÊýÁ¿²»×ã  µ¨Âë+ÍÏÂë²»ÄÜÐ¡ÓÚ7!   ---> "+(dRed.length + tRed.length));
			List<String>reds = new ArrayList<String>();
			AwardUtils.assemble7(dRed, (s[0]+","+s[1]).split(","), reds);
			setTicketAmount(reds.size());
			for(String sr : reds){			
				int r = AwardUtils.compare_QLC_RED(sr.split(","), open);
				int b = AwardUtils.compare_QLC_SPECIAL(sr.split(","), special);
				Award award = AwardUtils.createQLCprize(r + "+" + b,sr);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		throw new AwardException("ºÅÂë¸ñÊ½²»Æ¥Åä ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("((0\\d|[1-2]\\d|30),){0,5}(0\\d|[1-2]\\d|30):((0\\d|[1-2]\\d|30),){1,28}(0\\d|[1-2]\\d|30)");
	}
	
	


	

}
