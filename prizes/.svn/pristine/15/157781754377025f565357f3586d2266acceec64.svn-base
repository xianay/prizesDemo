package com.tyc.lottery.award.prizes.proc.ssq;

import java.util.ArrayList;
import java.util.List;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.exception.OpenNumException;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;
import com.tyc.lottery.award.prizes.proc.SsqAwardProcesser;



/**
 * 双色球胆拖兑奖
 * <br>例：
 * <br>01,02,03:04,05,06,07,08:01
 * @author anyi
 *
 */
public class SsqDTAwardProcesser extends AwardProcesserImpl implements SsqAwardProcesser {

	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		if(!openNum.matches("((0\\d|[1-2]\\d|3[0-3])[,]){5}(0\\d|[1-2]\\d|3[0-3])[:]((0\\d|1[0-6]),)?((0\\d|1[0-6]))"))
			throw new OpenNumException("开奖号码有误.");
		String[]open = openNum.split(":");
		String[]openRed = open[0].split(",");
		String openBlue = open[1];
		
		String lucky = null; //幸运二等奖
		if(openBlue.contains(",")){
			lucky = openBlue.split(",")[1];
			openBlue = openBlue.split(",")[0];
		}
		
		if(lottery_number.matches("([0-9]{2}[,]){0,4}[0-9]{2}[:]([0-9]{2}[,]){1,32}[0-9]{2}[:]([0-9]{2}[,]){0,15}[0-9]{2}")){
			String s[] = lottery_number.split(":");
			String[] dRed = s[0].split(",");
			String[] tRed = s[1].split(",");
			String[] blue = s[2].split(",");
			
			if(dRed.length + tRed.length <7)
				throw new AwardException("红球数量不足  胆码+拖码不能小于7!   ---> "+(dRed.length + tRed.length));
			List<String>reds = new ArrayList<String>();
			AwardUtils.assemble6(dRed, (s[0]+","+s[1]).split(","), reds);
			int i = 0;
			for(String sb : blue){
				for(String sr : reds){
					i++;
					int r = AwardUtils.compare_SSQ_RED(sr.split(","), openRed);
					int b = AwardUtils.compare_SSQ_BLUE(new String[]{sb}, new String[] { openBlue });			
					Award award = AwardUtils.createSSQprize(r + ":" + b,sr+":"+sb);
					if(lucky!=null&&award!=null)
						if("2".equals(award.getAward_code())&&award.getLottery_number().endsWith(lucky)){//计算幸运二等奖
							award.setAward_code("7");
							award.setAward_name("幸运二等奖");
						}
					
					if (award != null)
						list.add(award);
				}
			}
			setTicketAmount(i);
			return list;
		}
			
		throw new AwardException("号码格式不匹配 ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("([0-9]{2}[,]){0,4}[0-9]{2}[:]([0-9]{2}[,]){1,32}[0-9]{2}[:]([0-9]{2}[,]){0,15}[0-9]{2}");
	}
	
	

	

}
