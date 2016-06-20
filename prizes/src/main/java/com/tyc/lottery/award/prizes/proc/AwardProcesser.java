package com.tyc.lottery.award.prizes.proc;

import java.util.List;
import java.util.Set;

import com.tyc.lottery.award.prizes.common.Award;
import com.tyc.lottery.award.prizes.exception.AwardException;


/**
 * ²ÊÆ±Ëã½±½Ó¿Ú
 * @author anyi
 *
 */
public interface AwardProcesser {
	
	/**
	 * Ð£ÑéÍ¶×¢ºÅÂë
	 * @param lottery_number
	 * @return
	 */
	public boolean validation(String lottery_number);
	
	/**
	 * ¶Ò½±
	 * @param openNum ¿ª½±ºÅÂë
	 * @param lottery_number ¶Ò½±ºÅÂë
	 * @return
	 */
	public List<Award> prize(String openNum,String lottery_number) throws AwardException;

	/**
	 * ¶Ò½±×¢Êý
	 * @return
	 */
	public Integer getTicketAmount();
	
	public Set<String> getTickets();
	
}
