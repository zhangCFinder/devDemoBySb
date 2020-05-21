package com.zcFinder.common.util.MultipleDataSource;

/**
 * Created by zhangc on 2020/5/8.
 */
public class DataSourceContextHolder {

	private static ThreadLocal<String> datasourceContext = new ThreadLocal<>();

	public static void switchDataSource(String datasource) {
		datasourceContext.set(datasource);
	}

	public static String getDataSource() {
		String dataSourceName = datasourceContext.get();
		System.out.println("切换数据源到---->" + dataSourceName);
		return dataSourceName;
	}

	public static void clear() {
		datasourceContext.remove();
	}
}
