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
 * <br>���� 2:3,5 ��ѡ����Ͷע��Ͷע�ߴ�0~9��ѡ��M��0<M<=2����������Ϊ���룬ѡ��N��N+M>=3��N>=2�������ظ����Ҳ������ڵ����е�������Ϊ���룬������Щ����������ɵ����к��������ѡ������Ͻ���ȫ��Ͷע����������ͬ����Ϊֱѡ����������ͬ����Ϊ������������������ͬ����Ϊ������
 * @author anyi
 *
 */
public class D3ZuXDTAwardProcesser  extends AwardProcesserImpl implements D3AwardProcesser{

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
			Set<String> set = AwardUtils.permutation3_��ѡ(arr[0], arr[1]);
			for (String string : set) {
				String[] arr1 = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr1)){
				case AwardUtils.D3_ֱѡ:
					award = AwardUtils.prizewinning_D3_ֱѡ(arr1, open);
					break;
				case AwardUtils.D3_��ѡ3:
					award = AwardUtils.prizewinning_D3_��ѡ3(arr1, open);
					break;
				case AwardUtils.D3_��ѡ6:
					award = AwardUtils.prizewinning_D3_��ѡ6(arr1, open);
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
		return lottery_number.matches("[0-9]+([,][0-9])*[:]{1}[0-9]+([,][0-9])*");
	}

}
