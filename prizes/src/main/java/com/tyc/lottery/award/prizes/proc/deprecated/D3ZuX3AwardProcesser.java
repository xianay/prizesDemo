package com.tyc.lottery.award.prizes.proc.deprecated;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardUtils;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;
import com.tyc.lottery.award.prizes.proc.AwardProcesserImpl;


/**
 * 3d ��ѡ3Ͷע�ҽ�
 * <br>����
 * <br>1.������ʽ  1,2,3,4 ��[0-9]����ѡ(2~10)�����������ѡ����Ͷע��
 * <br>2.�����ѡ�� ~2 ���п����ͬ��������
 * @author anyi
 */
@Deprecated
public class D3ZuX3AwardProcesser extends AwardProcesserImpl implements AwardProcesser {


	public List<Award> prize(String openNum, String lottery_number) throws AwardException {
		List<Award> list = new ArrayList<Award>();
		String[]open = openNum.split(":");
	
		
		if(lottery_number.matches("[~]{1}[0-9]+")){
			int num = Integer.parseInt(lottery_number.replace("~", ""));
			if(num<1 || num>9)
				throw new AwardException("��������ֵ����ȷ(1~9)" + " --> " + num);
			Set<String> set = AwardUtils.permutation3_���(num);
			for (String string : set) {
				String[] arr = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr)){
				case AwardUtils.D3_��ѡ3:
					award = AwardUtils.prizewinning_D3_��ѡ3(arr, open);
					break;
				}
				if (award != null)
					list.add(award);
			}
			return list;
		}
		
		if(lottery_number.matches("([0-9][,]){1,9}[0-9]")){			
			String[] s = lottery_number.split(",");
			if(AwardUtils.hasDuplicate(s))
				throw new AwardException("��3��ʽͶע ���벻�����ظ�ֵ");
			String[][] arr = new String[3][];
			for(int i=0;i<arr.length;i++)
				arr[i] = s;	
			Set<String> set = AwardUtils.permutation3(arr);
			AwardUtils.D3RemoveDuplicate(set);
			for (String string : set) {
				String[] arr1 = string.split(":");
				Award award = null;				
				switch(AwardUtils.D3BettingFormat(arr1)){
				case AwardUtils.D3_��ѡ3:
					award = AwardUtils.prizewinning_D3_��ѡ3(arr1, open);
					break;
				}
				if (award != null)
					list.add(award);
			}
			return list;
		}

		throw new AwardException(this.getClass().getName() + " --> �����ʽ��ƥ�䡣");
	}

	@Override
	public boolean validation(String lottery_number) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
