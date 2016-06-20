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
 * 3d ֱѡ������ʽ
 * <br>����1,2,3,4 (ѡ��ĺ������ 4~8)
 * @author anyi
 *
 */
public class D3ZXZ6FSAwardProcesser  extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("������������.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("([0-9][,]){3,7}[0-9]")){
			lottery_number = lottery_number.replace("@", "");
			String[] s = lottery_number.split(",");
			Set<String> set = new HashSet<String>();
			Set<String> set1 = new HashSet<String>();
			AwardUtils.assemble3(s, set1);		
			for (String string : set1) 
				set.addAll(AwardUtils.permutation3(string.split(":")));
			for(String string : set){
				Award award = AwardUtils.prizewinning_D3_ֱѡ(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		throw new AwardException("�����ʽ��ƥ�� ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {

		return lottery_number.matches("([0-9][,]){3,7}[0-9]");
	}

}
