package com.jo.demo;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderCodeGenerator {

	// 自增长序列
	private int i = 0;

	// 按照“年-月-日-小时-分钟-秒-自增长序列”的规则生成订单编号
	public String getOrderCode() {
		List list = new ArrayList();
        Set set = new HashSet();
        Map map = new HashMap();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
		return sdf.format(now) + ++i;
	}
}
