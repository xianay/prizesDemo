package com.tyc.lottery.award.prizes.common;

import java.io.Serializable;

/**
 * �� bean
 * @author anyi
 *
 */
@SuppressWarnings("serial")
public  class Award implements Serializable{
	
	/**����*/private String lottery_name;
	/**��������*/private String award_name;
	/**���ȱ��*/private String award_code;
	/**�н�����*/private String lottery_number;
	/**����*/private String money;

	
	/**
	 * ���ȱ��
	 * @return
	 */
	public String getAward_code() {
		return award_code;
	}

	public void setAward_code(String award_code) {
		this.award_code = award_code;
	}

	/**
	 * �н�����
	 * @return
	 */
	public String getLottery_number() {
		return lottery_number;
	}

	public void setLottery_number(String lottery_number) {
		this.lottery_number = lottery_number;
	}
	/**
	 * ����
	 * @return
	 */
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	/**
	 * ����
	 * @return
	 */
	public String getLottery_name() {
		return lottery_name;
	}

	public void setLottery_name(String lottery_name) {
		this.lottery_name = lottery_name;
	}
	
	/**
	 * ���� �� �н���������
	 * @return
	 */
	public String getAward_name() {
		return award_name;
	}

	public void setAward_name(String award_name) {
		this.award_name = award_name;
	}

	@Override
	public String toString() {
		return "Award [lottery_name=" + lottery_name + ", award_name="
				+ award_name + ", award_code=" + award_code
				+ ", lottery_number=" + lottery_number + ", money=" + money
				+ "]";
	}

	

	

	
}

