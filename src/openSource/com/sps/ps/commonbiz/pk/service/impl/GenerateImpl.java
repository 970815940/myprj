package com.sps.ps.commonbiz.pk.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.pk.action.PrimarykeyAction;
import com.sps.ps.commonbiz.pk.entity.Primarykey;
import com.sps.ps.commonbiz.pk.service.Generate;
import com.sps.ps.commonbiz.pk.service.PrimarykeyService;
import com.sps.ps.utils.DateUtil;
import com.sps.ps.utils.StringUtil;

public class GenerateImpl implements Generate {
	private PrimarykeyService primarykeyServiceImpl;
	private static Logger log = Logger.getLogger(GenerateImpl.class);
	public  String getKey(String code) {
		if(code==null||code.equals("")){
			return UUID.randomUUID().toString();
		}else{
			Primarykey pk_=primarykeyServiceImpl.getPrimaryKeyByCode(code);
			return generatePK(pk_);
			
		}
	}
	/**
	 * 创建一个主键字符串
	 * @param pk_
	 * @return
	 */
	public String generatePK(Primarykey pk_){
		String ruleValue=getRuleValue(pk_);//获取序列解析后的值
		int newCurValue=getCurAddInct(pk_);//获取自增后的新值;
		String curValue="";
		if(pk_.getPkSequence()!=null&&pk_.getPkSequence()>0){//如果序列值大于0   则表示使用序列的方式生成
			 curValue=getSequenceValue(pk_.getPkSequence(),newCurValue);
		}else{
			curValue=StringUtil.tostring(newCurValue);
		}
		
		//组装主键字符串 前缀+规则+当前值【是否有序列】+后缀
		log.debug("[生成规则值="+ruleValue+"],[自增后的新值="+newCurValue+"]");
		String key=joinpk(pk_,curValue,ruleValue);
		log.debug("生成的新主键字符串为:"+key);
		pk_.setPkCurrentvalue(newCurValue);
		primarykeyServiceImpl.updatePrimaryKey(pk_);
		return key;
	}
	public String joinpk(Primarykey pk_,String curValue,String ruleValue){
		String key="";
		if(pk_.getPkPrefix()!=null&&!"".equals(pk_.getPkPrefix())){
			key+=pk_.getPkPrefix();
		}
		if(ruleValue!=null&&!"".equals(ruleValue)){
			key+=ruleValue;
		}
		key+=curValue;
		if(pk_.getPkPostfix()!=null&&!"".equals(pk_.getPkPostfix())){
			key+=pk_.getPkPostfix();
		}
		
		return key;
	}
	/**
	 *获取当前值+增量的结果,如果这里的获取的结果大于了maxVlaue(也就是最大值)则抛出异常
	 * @return
	 * @exception RuntimeException
	 */
	public int getCurAddInct(Primarykey pk_){
		int value=0;
		int start=pk_.getPkStartvalue();
		int cur=0;
		if(pk_.getPkCurrentvalue()==null||pk_.getPkCurrentvalue()==0){//第一次使用主键的时候
			cur=start;
			return cur;
		}else{
			cur=pk_.getPkCurrentvalue();
		}
		value=cur+pk_.getPkIncrement();
		if(pk_.getPkMaxvalue()<value&&pk_.getPkMaxvalue()>0){
			throw new RuntimeException("主键已达到上限,超过最大值了,请联系管理员");
		}
		return value;
	}
	/**
	 * 获取规则信息.解析规则,并返回相应的值
	 * @return
	 */
	public String getRuleValue(Primarykey pk_){
		String rule_value=DateUtil.formartRandomDate(DateUtil.getCurDate(), pk_.getPkRule());
		return rule_value;
	}
	/**
	 * 用序列的方式得到一个当前值字符串
	 * @param pk_
	 * @return
	 */
	public String getSequenceValue(int len,int value){
		String str=StringUtil.fillString(StringUtil.tostring(value), '0', len, StringUtil.FILL_LEFT);
		return str;
	}
	public static void main(String[] args) {
		GenerateImpl g=new GenerateImpl();
		String str=g.getSequenceValue(5, 234);
		System.out.println(str);
	}
	public  String getkey() {
		String key=this.getKey(null);
		return key;
	}

	/**
	 * @param primarykeyServiceImpl the primarykeyServiceImpl to set
	 */
	public void setPrimarykeyServiceImpl(PrimarykeyService primarykeyServiceImpl) {
		this.primarykeyServiceImpl = primarykeyServiceImpl;
	}

}
