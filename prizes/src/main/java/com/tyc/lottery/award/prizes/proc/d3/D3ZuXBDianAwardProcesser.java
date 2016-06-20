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
 * 3d ��ѡ����
 * <br>����2 �Ѻ�ֵΪ��һ���������Ϸ��࣬��������ͬ����Ϊֱѡ����������ͬ����Ϊ������������������ͬ����Ϊ������
 * @author anyi
 *
 */
public class D3ZuXBDianAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("������������.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9]+")){
			int num = Integer.parseInt(lottery_number);
			if(num<0 || num>27)
				throw new AwardException("���������ֵ����ȷ(0~27)" + " --> " + num);
			Set<String> set = AwardUtils.permutation3(num);
			AwardUtils.D3RemoveDuplicate(set);
			for (String string : set) {
				String[] arr = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr)){
//				case AwardUtils.D3_ֱѡ:
//					award = AwardUtils.prizewinning_D3_ֱѡ(arr, open);
//					break;
				case AwardUtils.D3_��ѡ3:
					award = AwardUtils.prizewinning_D3_��ѡ3(arr, open);
					System.out.println("��ѡ3 -> "+string);
					break;
				case AwardUtils.D3_��ѡ6:
					award = AwardUtils.prizewinning_D3_��ѡ6(arr, open);
					System.out.println("��ѡ6 -> "+string);
					break;
				}
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		throw new AwardException("�����ʽ��ƥ�� ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("[0-9]+");
	}

}
