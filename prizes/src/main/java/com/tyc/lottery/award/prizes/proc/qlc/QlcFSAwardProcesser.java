package com.tyc.lottery.award.prizes.proc.qlc;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.QlcAwardProcesser;

/**
 * 七乐彩复式兑奖
 * <br>例：
 * <br>01,02,03,04,05,06,07,08,09,10
 * @author anyi
 *
 */
public class QlcFSAwardProcesser extends AwardProcesserImpl implements QlcAwardProcesser {


	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		if(!openNum.matches("((0\\d|[1-2]\\d|30),){7}(0\\d|[1-2]\\d|30)"))
			throw new AwardException("开奖号码有误.");
		String[]open = new String[7];
		System.arraycopy(openNum.split(","), 0, open, 0, 7);
		String special = openNum.split(",")[7];
		if(lottery_number.matches("((0\\d|[1-2]\\d|30),){6,29}(0\\d|[1-2]\\d|30)")){
			if(lottery_number.matches("((0\\d|[1-2]\\d|30),){6}(0\\d|[1-2]\\d|30)"))
				throw new AwardException("复式投注不可投单注.");
			String[] red = lottery_number.split(",");
			List<String>reds = new ArrayList<String>();
			AwardUtils.assemble7(red, reds);
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
		
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		return lottery_number.matches("((0\\d|[1-2]\\d|30),){6,29}(0\\d|[1-2]\\d|30)");
	}

}
