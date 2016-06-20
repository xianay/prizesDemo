package com.tyc.lottery.award.prizes.proc;

import java.util.Set;

/**
 *  ¶Ò½±
 * @author anyi
 *
 */
public abstract class AwardProcesserImpl implements AwardProcesser{
	
	private Integer ticketAmount;
	
	private Set<String> tickets;

	public Integer getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Set<String> getTickets() {
		return tickets;
	}

	public void setTickets(Set<String> tickets) {
		this.tickets = tickets;
	}
	
	
	
	
	

}
