package com.ruoyi.framework.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.encrypt.api.config.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.config.rule.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.config.rule.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.config.properties.DruidProperties;
import com.ruoyi.framework.datasource.DynamicDataSource;

/**
 * druid 配置多数据源
 * 
 * @author ruoyi
 */
@Configuration
public class DruidConfig
{
//	private final DriverDataSourceCache dataSourceCache = new DriverDataSourceCache();
	
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    	
        
//        Yaml yaml = new Yaml();
//        try ( InputStream in = getClass().getClassLoader().getResourceAsStream("sharding.yaml");) {
//
//            if (in != null) {
//                YamlYDXConfiguration yamlYDXConfiguration = yaml.loadAs(in, YamlYDXConfiguration.class);
//                //YamlYDXConfiguration yamlYDXConfiguration = yaml.load(in);
//                DataSource dataSource = dataSourceCache.getName("ds", yamlYDXConfiguration);
//                
//                return dataSource;
//
//            } else {
//                System.out.println("File not found in resources");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource) throws SQLException
    {
    	
    	
    	// 配置加密算法
        Map<String, AlgorithmConfiguration> encryptors = new HashMap<>();
        Properties aesProps = new Properties();
        aesProps.setProperty("aes-key-value", "hkiqAXU6Ur5fixGHaO4Lb2V2ggausYwW");
        encryptors.put("encryptor_aes", new AlgorithmConfiguration("AES", aesProps));
        
//        Properties likeProps = new Properties();
//        encryptors.put("like_encryptor", new AlgorithmConfiguration("CHAR_DIGEST_LIKE", likeProps));
        
        // 配置sys_user表的加密列
        List<EncryptColumnRuleConfiguration> sysUserColumns = new ArrayList<>();
//        sysUserColumns.add(new EncryptColumnRuleConfiguration("user_name", new EncryptColumnItemRuleConfiguration("user_name", "encryptor_aes")));
//        sysUserColumns.add(new EncryptColumnRuleConfiguration("email", new EncryptColumnItemRuleConfiguration("email", "encryptor_aes")));
//        
//        EncryptColumnItemRuleConfiguration cipherColumn = new EncryptColumnItemRuleConfiguration("phonenumber", "encryptor_aes");
//        EncryptColumnItemRuleConfiguration assistedQueryColumn = new EncryptColumnItemRuleConfiguration("phonenumber_like", "like_encryptor");
//        EncryptColumnRuleConfiguration phonenumberColumn = new EncryptColumnRuleConfiguration("phonenumber", cipherColumn);
//        phonenumberColumn.setAssistedQuery(assistedQueryColumn);
//        phonenumberColumn.setLikeQuery(assistedQueryColumn);
//        sysUserColumns.add(phonenumberColumn);
        //sysUserColumns.add(new EncryptColumnRuleConfiguration("login_name", "login_name", "", "login_name", "encryptor_aes", true));
        sysUserColumns.add(new EncryptColumnRuleConfiguration("user_name", "user_name", "", "", "encryptor_aes", true));
        sysUserColumns.add( new EncryptColumnRuleConfiguration("email", "email", "", "","encryptor_aes", true));
        sysUserColumns.add(new EncryptColumnRuleConfiguration("phonenumber", "phonenumber", "", "", "encryptor_aes", true));
        
     // **关键优化**：为 phonenumber 添加辅助查询列
     // 1. 通过构造函数或 Builder 设置辅助查询列属性
     // 2. 使用 encryptor_like 进行加密
     // 3. 启用 assistedQueryColumn，ShardingSphere 会自动生成一个列名为 phonenumber_assisted
//     sysUserColumns.add(
//         new EncryptColumnRuleConfiguration(
//             "phonenumber",               // 逻辑列名
//             "phonenumber",             // 密文字段
//             "phonenumber_like",         // 辅助查询列
//             "",             // 明文文字段
//             "encryptor_aes",           // 主加密器
//             "encryptor_aes",                      // 是否查询返回明文（这里不需要返回明文）
//             false                      // **开启辅助查询列**（关键）
//         )
//     );
     
        
        // 配置water_meter_device表的加密列
        List<EncryptColumnRuleConfiguration> waterMeterDeviceColumns = new ArrayList<>();
//        waterMeterDeviceColumns.add(new EncryptColumnRuleConfiguration("user_name", new EncryptColumnItemRuleConfiguration("user_name", "encryptor_aes")));
//        waterMeterDeviceColumns.add(new EncryptColumnRuleConfiguration("user_phone", new EncryptColumnItemRuleConfiguration("user_phone", "encryptor_aes")));
//        waterMeterDeviceColumns.add(new EncryptColumnRuleConfiguration("user_id_card", new EncryptColumnItemRuleConfiguration("user_id_card", "encryptor_aes")));
        waterMeterDeviceColumns.add(new EncryptColumnRuleConfiguration("user_name", "user_name", "", "", "encryptor_aes", true));
        waterMeterDeviceColumns.add( new EncryptColumnRuleConfiguration("user_phone", "user_phone", "", "", "encryptor_aes", true));
        waterMeterDeviceColumns.add( new EncryptColumnRuleConfiguration("user_id_card", "user_id_card", "", "","encryptor_aes", true));
        
        // 配置加密表
        List<EncryptTableRuleConfiguration> tables = new ArrayList<>();
        tables.add(new EncryptTableRuleConfiguration("sys_user", sysUserColumns, true));
        tables.add(new EncryptTableRuleConfiguration("water_meter_device", waterMeterDeviceColumns, true));
        
        // 创建加密规则配置
        EncryptRuleConfiguration encryptRuleConfig = new EncryptRuleConfiguration(tables, encryptors);
        List<RuleConfiguration> ruleConfigurations = new ArrayList<>();
        ruleConfigurations.add(encryptRuleConfig);
        
        masterDataSource = ShardingSphereDataSourceFactory.createDataSource(masterDataSource, ruleConfigurations, aesProps);
    	
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * 设置数据源
     * 
     * @param targetDataSources 备选数据源集合
     * @param sourceName 数据源名称
     * @param beanName bean名称
     */
    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName)
    {
        try
        {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        }
        catch (Exception e)
        {
        }
    }

    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties)
    {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = new Filter()
        {
            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException
            {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException
            {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }

            @Override
            public void destroy()
            {
            }
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
    
    
}
