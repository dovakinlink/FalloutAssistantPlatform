package com.platform.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * ClassName：DynamicDataSource
 * Description：动态切换数据源
 * @author: 刘焕超
 * @date: 2015-5-19 下午4:48:20
 * note:
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceHolder.getCustomerType();  
    }
    
}
