package com.tyc.lottery.award.prizes.common;

import java.util.HashMap;
import java.util.Map;
import com.tyc.lottery.award.prizes.proc.AwardProcesser;

/**
 * 兑奖工厂
 * @author anyi
 *
 */
@SuppressWarnings("static-access")
public class AwardFactory {

	private final static Map<String,Object> map = new HashMap<String, Object>();
	
	static{
		Map<Object,Object> map = PropertiesUtils.CONFIG.getMap();
		for(Map.Entry<Object, Object> entry : map.entrySet()){
			String key = (String) entry.getKey();
			Class<?> cls = null;
			Object obj = null;
			try {
				cls = cls.forName(entry.getValue().toString());		
				obj = cls.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			AwardFactory.map.put(key, obj);
		}
	}
	
	/**
	 * 获得对应的兑奖类
	 * 
	 * 游戏	代码	投注方式（名称）	投注格式规定
<br>SSQ	0	单式	01,02,03,04,05,06:07
<br>	1	复式	01,02,03,04,05,06:07,08
<br>	2	胆拖	01:02,03,04,05,06,07:04
<br>3D	0	直选投注	1:2:3
<br>	2	包号投注(胆拖)	ff2 or  f23
<br>	5	组选投注	1:2:3   or   1:2:2
<br>	6	单选复式	3,4:3,5:3,6
<br>	7	组三复式	1,2,3,4
<br>	8	组六复式	1,2,3,4
<br>	9	直选包胆	ff2   or   f22
<br>	10	直选包点	5
<br>	11	直选包串	5,6,7,8
<br>	12	组选包胆	ff2   f22
<br>	13	组选包点	2
<br>	14	直选胆拖投注	2:3,5
<br>	15	组选胆拖投注	2:3,5
<br>	16	跨度直选投注	2
<br>	17	跨度组选三投注	2
<br>	18	跨度组选六投注	2
<br>	19	直选全组三投注	all
<br>	20	直选组六复式投注	1,2,3,4(号码个数必须大于等于4)
<br>QLC	0	单式	01,02,03,04,05,06,07
<br>	1	复试	01,02,03,04,05,06,07,08
<br>	2	胆拖	01,02,03,04,05,06:11,12
<br>K3	31	快3和值	10( 4-17三个号码的和值)
<br>	32.0	快3三同号自选单式	3,3,3(号码相同,豹子号)
<br>	32.1	快3三同号通选	0,0,0(固定号码)
<br>	33.0	快3二同号自选单式	2,2,3(号码用逗号相隔,前2个号码相同,后一个号码不同,且是升序)
<br>	33.1	快3二同号自选复式	2,2(号码用逗号相隔,且号码相同)
<br>	34	快3三不同号自选	1,2,6(号码用逗号相隔,且号码不同,升序排列)
<br>	35	快3二不同号自选	1,6(号码用逗号相隔,且号码不同)
<br>	36	快3三连号通选	0,0,0(固定号码)
<br>	
<br>	
<br>	
<br>	****************************************************************
<br>	新3D玩法
<br>	****************************************************************

<br>	21	1D投注  2:f:f f:2:f f:f:2	
<br>	22	2D投注  2:1:f f:2:1 2:f:1	
<br>
<br>
<br>
<br>


	 * @param name  例：SSQ.0   
	 * @return
	 */
	public static AwardProcesser getAwardProcesser(String name){
		return (AwardProcesser) map.get(name);
	}
	
	
}
