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
 * 3d ֱѡ����
 * <br>���� 1,2:3,5,6 (��������ظ���ѡ��1~2�������벻���ظ��Ҳ��ܺ͵�����ͬ,ѡ������2������)
 * @author anyi
 *
 */
public class D3ZXDTAwardProcesser extends AwardProcesserImpl implements D3AwardProcesser{

	@Override
	public List<Award> prize(String openNum, String lottery_number)
			throws AwardException {
		List<Award> list = new ArrayList<Award>();		
		if(!openNum.matches("([0-9][:]){2}[0-9]"))
			throw new AwardException("������������.");
		
		String[]open = openNum.split(":");
		
		if(lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*")){			
			String[] s = lottery_number.split(":");
			String[][] arr = new String[2][];
			for(int i=0;i<2;i++)
				arr[i] = s[i].split(",");	
			Set<String> set = AwardUtils.permutation3_ֱѡ(arr[0], arr[1]);
			for (String string : set) {
				//System.out.println(string);
				Award award = AwardUtils.prizewinning_D3_ֱѡ(string.split(":"), open);
				if (award != null)
					list.add(award);
			}
			//System.out.println(set.size());
			return list;
		}
		
		throw new AwardException("�����ʽ��ƥ�� ---> "+lottery_number);
	}

	@Override
	public boolean validation(String lottery_number) {
		
		return lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*");
	}
	


}
