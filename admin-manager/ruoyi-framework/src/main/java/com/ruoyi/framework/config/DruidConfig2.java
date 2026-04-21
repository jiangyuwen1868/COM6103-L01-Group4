//package com.ruoyi.framework.config;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.sql.DataSource;
//
//import org.apache.shardingsphere.encrypt.api.EncryptColumnRuleConfiguration;
//import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;
//import org.apache.shardingsphere.encrypt.api.EncryptTableRuleConfiguration;
//import org.apache.shardingsphere.encrypt.api.EncryptorRuleConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
//import com.alibaba.druid.util.Utils;
//import com.ruoyi.common.enums.DataSourceType;
//import com.ruoyi.common.utils.spring.SpringUtils;
//import com.ruoyi.framework.config.properties.DruidProperties;
//import com.ruoyi.framework.datasource.DynamicDataSource;
//
///**
// * druid 配置多数据源
// * 
// * @author ruoyi
// */
//@Configuration
//public class DruidConfig2
//{
////	private final DriverDataSourceCache dataSourceCache = new DriverDataSourceCache();
//	
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.master")
//    public DataSource masterDataSource(DruidProperties druidProperties)
//    {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        return druidProperties.dataSource(dataSource);
//    	
//        
////        Yaml yaml = new Yaml();
////        try ( InputStream in = getClass().getClassLoader().getResourceAsStream("sharding.yaml");) {
////
////            if (in != null) {
////                YamlYDXConfiguration yamlYDXConfiguration = yaml.loadAs(in, YamlYDXConfiguration.class);
////                //YamlYDXConfiguration yamlYDXConfiguration = yaml.load(in);
////                DataSource dataSource = dataSourceCache.getName("ds", yamlYDXConfiguration);
////                
////                return dataSource;
////
////            } else {
////                System.out.println("File not found in resources");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null;
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.slave")
//    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
//    public DataSource slaveDataSource(DruidProperties druidProperties)
//    {
////        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
////        return druidProperties.dataSource(dataSource);
//    	DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//    	try {
//			return EncryptDataSourceFactory.createDataSource(druidProperties.dataSource(dataSource), getEncryptRuleConfiguration(), new Properties());
//		} catch (SQLException e) {
//			return druidProperties.dataSource(dataSource);
//		}
//    }
//
//    @Bean(name = "dynamicDataSource")
//    @Primary
//    public DynamicDataSource dataSource(DataSource masterDataSource) throws SQLException
//    {
//    	DataSource ds = EncryptDataSourceFactory.createDataSource(masterDataSource, getEncryptRuleConfiguration(), new Properties());
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceType.MASTER.name(), ds);
//        setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
//        return new DynamicDataSource(ds, targetDataSources);
//    }
//
//    /**
//     * 设置数据源
//     * 
//     * @param targetDataSources 备选数据源集合
//     * @param sourceName 数据源名称
//     * @param beanName bean名称
//     */
//    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName)
//    {
//        try
//        {
//            DataSource dataSource = SpringUtils.getBean(beanName);
//            targetDataSources.put(sourceName, dataSource);
//        }
//        catch (Exception e)
//        {
//        }
//    }
//
//    /**
//     * 去除监控页面底部的广告
//     */
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    @Bean
//    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
//    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties)
//    {
//        // 获取web监控页面的参数
//        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
//        // 提取common.js的配置路径
//        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
//        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
//        final String filePath = "support/http/resources/js/common.js";
//        // 创建filter进行过滤
//        Filter filter = new Filter()
//        {
//            @Override
//            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException
//            {
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//                    throws IOException, ServletException
//            {
//                chain.doFilter(request, response);
//                // 重置缓冲区，响应头不会被重置
//                response.resetBuffer();
//                // 获取common.js
//                String text = Utils.readFromResource(filePath);
//                // 正则替换banner, 除去底部的广告信息
//                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
//                text = text.replaceAll("powered.*?shrek.wang</a>", "");
//                response.getWriter().write(text);
//            }
//
//            @Override
//            public void destroy()
//            {
//            }
//        };
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(filter);
//        registrationBean.addUrlPatterns(commonJsPattern);
//        return registrationBean;
//    }
//    
//    
//    private EncryptRuleConfiguration getEncryptRuleConfiguration() {
//		Properties props = new Properties();
//		String aeskey = "hkiqAXU6Ur5fixGHaO4Lb2V2ggausYwW";
//		//自带aes算法需要
//		props.setProperty("aes.key.value", aeskey);
//		EncryptorRuleConfiguration encryptorConfig = new EncryptorRuleConfiguration("AES", props);
//
//		//自定义算法
//		//props.setProperty("qb.finance.aes.key.value", aeskey);
//		//EncryptorRuleConfiguration encryptorConfig = new EncryptorRuleConfiguration("QB-FINANCE-AES", props);
//
//		EncryptRuleConfiguration encryptRuleConfig = new EncryptRuleConfiguration();
//		encryptRuleConfig.getEncryptors().put("aes", encryptorConfig);
//
//		//START: sys_user 表的脱敏配置
//		{
//		    EncryptColumnRuleConfiguration columnConfig1 = new EncryptColumnRuleConfiguration("", "user_name", "", "aes");
//		    EncryptColumnRuleConfiguration columnConfig2 = new EncryptColumnRuleConfiguration("", "email", "", "aes");
//		    EncryptColumnRuleConfiguration columnConfig3 = new EncryptColumnRuleConfiguration("", "phonenumber", "", "aes");
//		    Map<String, EncryptColumnRuleConfiguration> columnConfigMaps = new HashMap<>();
//		    columnConfigMaps.put("user_name", columnConfig1);
//		    columnConfigMaps.put("email", columnConfig2);
//		    columnConfigMaps.put("phonenumber", columnConfig3);
//		    EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(columnConfigMaps);
//		    encryptRuleConfig.getTables().put("sys_user", tableConfig);
//		}
//		//END: sys_user 表的脱敏配置
//
//		//START: water_meter_device 表的脱敏配置
//		{
//		    EncryptColumnRuleConfiguration columnConfig1 = new EncryptColumnRuleConfiguration("", "user_name", "", "aes");
//		    EncryptColumnRuleConfiguration columnConfig2 = new EncryptColumnRuleConfiguration("", "user_phone", "", "aes");
//		    EncryptColumnRuleConfiguration columnConfig3 = new EncryptColumnRuleConfiguration("", "user_id_card", "", "aes");
//		    Map<String, EncryptColumnRuleConfiguration> columnConfigMaps = new HashMap<>();
//		    columnConfigMaps.put("user_name", columnConfig1);
//		    columnConfigMaps.put("user_phone", columnConfig2);
//		    columnConfigMaps.put("user_id_card", columnConfig3);
//		    EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(columnConfigMaps);
//		    encryptRuleConfig.getTables().put("water_meter_device", tableConfig);
//		}
//
//		return encryptRuleConfig;
//	}
//}
