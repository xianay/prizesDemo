package test;

import java.util.List;


import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.common.AwardFactory;
import com.tyc.lottery.award.prizes.exception.AwardException;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;

public class K3Test {
	
	
	public static void main(String[] args) throws AwardException {
		AwardProcesser awardProcesser = AwardFactory.getAwardProcesser("K3.33.1");
		List<Award> awards = awardProcesser.prize("5,5,5", "5,5");
		System.out.println(awards);
		
		//Collection<String> collection = CollectionUtils.emptyCollection();
		
		//ArrayListMultimap<String, Object> arrayListMultimap = ArrayListMultimap.create();
		//arrayListMultimap.put("1", 1);
		//arrayListMultimap.put("1", 2);
		
		
		
		//System.out.println(arrayListMultimap);
	}
}
