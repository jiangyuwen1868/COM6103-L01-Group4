package com.ruoyi;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.framework.datasource.DynamicDataSource;

@SpringBootTest
public class UserJdbcBatchInsertTest {
	
	// 常见的手机号段（前 3 位）
    private static final String[] PREFIXES = {
            "130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
            "145", "147",
            "150", "151", "152", "153", "155", "156", "157", "158", "159",
            "166",
            "170", "171", "172", "173", "174", "175", "176", "177", "178", "179",
            "180", "181", "182", "183", "184", "185", "186", "187", "188", "189",
            "198", "199"
    };

    private static final SecureRandom RANDOM = new SecureRandom();
    
    @Autowired
    private ApplicationContext applicationContext;

	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DataSource dataSource;

    @Test
    public void testBatchInsertByJdbcTemplate() throws Exception {
        final int total = 20_0000;               // 需要插入的总条数
        final int batchSize = 3000;
        List<UserRow> rows = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            rows.add(new UserRow("user_" + i,
            		"user_" + i,
            		"user_" + i + "@163.com",
            		randomPhoneNumber(),RANDOM.nextInt(100)));
        }

        String sql = "INSERT INTO sys_user (dept_id,login_name, user_name,user_type, email,phonenumber,sex,age,password,salt,status,create_by,create_time, remark) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,sysdate(),?)";

        long start = System.currentTimeMillis();
        int[][] counts = jdbcTemplate.batchUpdate(sql, rows, batchSize, new ParameterizedPreparedStatementSetter<UserRow>() {

			@Override
			public void setValues(PreparedStatement ps, UserRow r) throws SQLException {
				ps.setString(1, r.dept_id);
                ps.setString(2, r.login_name);
                ps.setString(3, r.user_name);
                ps.setString(4, r.user_type);
                ps.setString(5, r.email);
                ps.setString(6, r.phonenumber);
                ps.setString(7, r.sex);
                ps.setInt(8, r.age);
                ps.setString(9, r.password);
                ps.setString(10, r.salt);
                ps.setString(11, r.status);
                ps.setString(12, r.create_by);
                ps.setString(13, r.remark);
			}
		});
//        int[] counts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                UserRow r = rows.get(i);
//                ps.setString(1, r.dept_id);
//                ps.setString(2, r.login_name);
//                ps.setString(3, r.user_name);
//                ps.setString(4, r.user_type);
//                ps.setString(5, r.email);
//                ps.setString(6, r.phonenumber);
//                ps.setString(7, r.sex);
//                ps.setInt(8, r.age);
//                ps.setString(9, r.password);
//                ps.setString(10, r.salt);
//                ps.setString(11, r.status);
//                ps.setString(12, r.create_by);
//                ps.setString(13, r.remark);
//            }
//
//            @Override
//            public int getBatchSize() {
//                return rows.size();
//            }
//            
//        });
        long elapsed = System.currentTimeMillis() - start;
        System.err.println("JdbcTemplate batchInsert " + total + ", counts " + counts.length * batchSize + " rows 耗时(ms): " + elapsed);
//        System.err.println(JSONObject.toJSONString(counts));
        // 简单校验：返回的更新计数数组长度应等于 rows.size()
        assert counts.length * batchSize == rows.size();
    }
    
    @Test
    public void testBatchInsertByJdbcTemplate2() throws Exception {
        final int total = 20_0000;               // 需要插入的总条数
        final int batchSize = 3000;
        List<UserRow> rows = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            rows.add(new UserRow("user_" + i,
            		"user_" + i,
            		"user_" + i + "@163.com",
            		randomPhoneNumber(),RANDOM.nextInt(100)));
        }

        String sql = "INSERT INTO sys_user_online (sessionId,login_name,dept_name,ipaddr,login_location,browser,os,status,start_timestamp,last_access_time,expire_time) VALUES (?,?,?,?,?,?,?,?,sysdate(),sysdate(),?)";

        long start = System.currentTimeMillis();
        int[][] counts = jdbcTemplate.batchUpdate(sql, rows, batchSize, new ParameterizedPreparedStatementSetter<UserRow>() {

			@Override
			public void setValues(PreparedStatement ps, UserRow r) throws SQLException {
				ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2, r.login_name);
                ps.setString(3, "研发部门");
                ps.setString(4, "127.0.0.1");
                ps.setString(5, "内网IP");
                ps.setString(6, "Chrome 12");
                ps.setString(7, "Windows 10");
                ps.setString(8, "on_line");
                ps.setString(9, "1800000");
			}
		});
        long elapsed = System.currentTimeMillis() - start;
        System.err.println("JdbcTemplate batchInsert " + total + ", counts " + counts.length * batchSize + " rows 耗时(ms): " + elapsed);
        assert counts.length * batchSize == rows.size();
    }
    
    @Test
    public void testQuery() { //select max(age) from sys_user
    	for(int i=1;i<=2;i++) {
	    	long start = System.currentTimeMillis();
			List<Map<String, Object>> results = jdbcTemplate
					.queryForList("select user_id from sys_user where user_id=101"/* "select phonenumber from sys_user where phonenumber='13399138544'" */);
	    	long end = System.currentTimeMillis();
	    	System.err.println(i + " Query costTime: " + (end-start) + "ms JdbcTemplate Query count: " + results.size());
	    	System.err.println(JSONObject.toJSONString(results));
    	}
    }
    
    @Test
    public void testScopeAgeQuery() {
    	String sql = "select * from (select user_name from (select user_name from sys_user WHERE age >= 22) t) y ";
//    	sql = "select * from sys_user where phonenumber between 17999999999 and 18999999999";
    	List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
    	System.err.println("JdbcTemplate scopeAgeQuery count: " + results.size());
    	System.err.println(JSONObject.toJSONString(results));
//    	Executors.newScheduledThreadPool(10);
//    	Executors.newSingleThreadExecutor();
//    	Executors.newFixedThreadPool(10);
//    	ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//    	scheduler.setPoolSize(10);
//    	scheduler.setThreadNamePrefix("HsmTransMonitorTaskSchedulerThreadPool");
//    	scheduler.initialize();
//    	scheduler.getScheduledExecutor().scheduleAtFixedRate(()->{
//    		System.err.println("-----");
//    	}, 0, 3, TimeUnit.MILLISECONDS);
    }
    
    @Test
    public void testLikeQuery() {
    	List<Map<String, Object>> results = null;
    	results = jdbcTemplate.queryForList("select user_name,phonenumber from sys_user WHERE user_name like '%pengdy%'");
//    	results = jdbcTemplate.queryForList("SELECT user_name,phonenumber FROM sys_user u LEFT JOIN sys_dept d ON u.dept_id = d.dept_id WHERE u.del_flag = '0' AND u.login_name LIKE concat('%', 'peng', '%')");
    	System.err.println("JdbcTemplate LikeQuery count: " + results.size());
    	System.err.println(JSONObject.toJSONString(results));
    }
    
    @Test
    public void testUpdate() {
    	String sql = "update sys_user set phone = '" + randomPhoneNumber() + "'";
    	int ret = jdbcTemplate.update(sql);
    	System.err.println("update rows:" + ret);
    }
    
    private final ExecutorService pool = Executors.newFixedThreadPool(100);
    @Test
    public void testNonFunctional() {
    	int queryTPS = 500;
    	int updateTPS = 150;
    	for(int i=0;i<queryTPS;i++) {
    		pool.submit(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						String userName = "pengdy";
						String sql = "select user_name,phonenumber,email,age from sys_user WHERE user_name ='"+userName+"'";
//						String sql = "select login_name from sys_user where login_name='"+userName+"'";
						List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
						String username = (String) results.get(0).get("user_name");
						if(!userName.equals(username))
							System.err.println("---------------------" + username);
					}
				}
			});
    	}
    	for(int i=0;i<updateTPS;i++) {
    		pool.execute(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						String userName = "user_t1";
						String sql = "update sys_user set user_name = 'user_1', login_name = 'user_t11' WHERE user_name ='"+userName+"'";
//						String sql = "update sys_user set login_name = 'user_1' WHERE login_name ='"+userName+"'";
						int ret = jdbcTemplate.update(sql);
						//System.err.println("---------------------" + ret);
					}
				}
			});
    	}
    	
    	
    	synchronized (UserJdbcBatchInsertTest.class) {
			while(true) {
				try {
					UserJdbcBatchInsertTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    @Test
    public void testPrintDS() throws SQLException {
    	// 获取所有DataSource类型的Bean
//        Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
//        dataSourceMap.forEach((beanName, ds) -> {
//        	System.out.println("---------" + beanName);
//        	
//        	if (ds instanceof DruidDataSource) {
//                DruidDataSource druidDataSource = (DruidDataSource) ds;
//                System.out.println("---------" + druidDataSource.hashCode());
//                printDSInfo(druidDataSource);
//            }
//        	if(ds instanceof DynamicDataSource) {
//        		DynamicDataSource dynamicDataSource = (DynamicDataSource) ds;
//        		DruidDataSource currentDataSource = (DruidDataSource)dynamicDataSource.getResolvedDefaultDataSource();
//        		System.out.println("---------" + currentDataSource.hashCode());
//        		printDSInfo(currentDataSource);
//        	}
//        	
//        });
        
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl("jdbc:shardingsphere:classpath:sharding.yaml");
//        dataSource.setUsername("edbtest");
//        dataSource.setPassword("edbtest@123");
//        dataSource.setDriverClassName("com.ydxsafe.dedb.driver.ShardingSphereDriver");
//        dataSource.setInitialSize(5);
//        dataSource.setMaxActive(20);
//        dataSource.getConnection();
    	
    	 // 获取动态数据源
        //DynamicDataSource dynamicDataSource = (DynamicDataSource) applicationContext.getBean("dynamicDataSource");
        
        // 获取当前使用的数据源
        //DruidDataSource currentDataSource = (DruidDataSource) dynamicDataSource.getResolvedDefaultDataSource();
    	
        
        Set<DruidDataSource> dsList = DruidDataSourceStatManager.getDruidDataSourceInstances();
        
        for (DruidDataSource ds : dsList) {
        	System.out.println("===================================");
        	System.out.println("DataSource id = " + ds.getID());
            System.out.println("DataSource name = " + ds.getName());
            printDSInfo(ds);
        }
    }
    
    private synchronized void printDSInfo(DruidDataSource druidDataSource) {
    	System.out.printf("initialSize=%s\n", druidDataSource.getInitialSize());
        System.out.printf("minIdle=%s\n", druidDataSource.getMinIdle());
        System.out.printf("maxActive=%s\n", druidDataSource.getMaxActive());
        System.out.printf("maxWait=%s\n", druidDataSource.getMaxWait());
        System.out.printf("timeBetweenEvictionRunsMillis=%s\n", druidDataSource.getTimeBetweenEvictionRunsMillis());
        System.out.printf("minEvictableIdleTimeMillis=%s\n", druidDataSource.getMinEvictableIdleTimeMillis());
        System.out.printf("maxEvictableIdleTimeMillis=%s\n", druidDataSource.getMaxEvictableIdleTimeMillis());
        System.out.printf("validationQuery=%s\n", druidDataSource.getValidationQuery());
        System.out.printf("testWhileIdle=%s\n", druidDataSource.isTestWhileIdle());
        System.out.printf("testOnBorrow=%s\n", druidDataSource.isTestOnBorrow());
        System.out.printf("testOnReturn=%s\n", druidDataSource.isTestOnReturn());
        
        
        System.out.printf("activeCount=%s\n", druidDataSource.getActiveCount());
        System.out.printf("poolingCount=%s\n", druidDataSource.getPoolingCount());
        System.out.printf("url=%s\n", druidDataSource.getUrl());
        System.out.printf("username=%s\n", druidDataSource.getUsername());
        
        
        System.out.printf("activePeak=%s\n", druidDataSource.getActivePeak());
        System.out.printf("poolingPeak=%s\n", druidDataSource.getPoolingPeak());
        System.out.printf("connectCount=%s\n", druidDataSource.getConnectCount());
        System.out.printf("closeCount=%s\n", druidDataSource.getCloseCount());
        System.out.printf("commitCount=%s\n", druidDataSource.getCommitCount());
        System.out.printf("rollbackCount=%s\n", druidDataSource.getRollbackCount());
        System.out.printf("errorCount=%s\n", druidDataSource.getErrorCount());
        System.out.printf("dbType=%s\n", druidDataSource.getDbType());
        
        JdbcTemplate jdbc = new JdbcTemplate(druidDataSource);
    	String userName = "pengdy";
		String sql = "select user_name,phonenumber,email,age from sys_user WHERE user_name ='"+userName+"'";
        
        List<Map<String, Object>> results = jdbc.queryForList(sql);
        System.out.println(JSONObject.toJSONString(results, SerializerFeature.WriteMapNullValue));
		String username = (String) results.get(0).get("user_name");
		if(!userName.equals(username))
			System.err.println("---------------------" + username);
    }
    
    private static class UserRow {
        
         String dept_id    = "100"  ;  
         String login_name    ; 
         String  user_name    ;  
        final String user_type    = "00";  
         String email         ; 
         String phonenumber  ;  
        final String sex      = "0"      ;
        int age	= 30		;
        final String password   = "29c67a30398638269fe600f73a054934"    ;
        final String salt   = "111111"        ;
        final String status     = "0"    ;
        final String create_by  = "admin"    ;
        final String remark    = "BatchInsert"     ;


        UserRow(String login_name, String user_name, String email, String phonenumber, int age) {
            this.login_name = login_name;
            this.user_name = user_name;
            this.email = email;
            this.phonenumber = phonenumber;
            this.age = age;
        }
    }
    
 

    /**
     * 生成一个随机手机号
     *
     * @return 11 位手机号字符串
     */
    public static String randomPhoneNumber() {
        // 1️⃣ 随机选取一个号段
        String prefix = PREFIXES[RANDOM.nextInt(PREFIXES.length)];

        // 2️⃣ 生成后 8 位数字（0\~9）
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
    
    
    private static class MultithreadingExecutor {
    	private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    	public static void shutdown() {
    		executorService.shutdown();
    	}

    	public static void addTask(Runnable logTask) {
    		executorService.execute(logTask);
    	}
    }
}


