package com.example.mapper;

import com.example.pojo.Job;
import com.example.pojo.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class UserAndJobMapperTest {

    UserAndJobMapper userAndJobMapper;
    SqlSession sqlSession=null;

    @Before
    public void setUp() throws Exception {
        //1.获取资源核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //2.创建SqlSessionFactory工厂对象（由SqlSessionFactoryBuilder()创建）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //3.创建SqlSession对象
        sqlSession = sqlSessionFactory.openSession();

        //4.获取接口的动态代理对象
        //执行方法时，要找哪一条SQL语句来执行？sql语句的唯一标识是由namespace+statementId
        userAndJobMapper = sqlSession.getMapper(UserAndJobMapper.class);

    }

    @Test
    public void findAll() {
        List<User> userAndJob = userAndJobMapper.findAll();

        for (User user : userAndJob) {
            if (!user.isDel()){
                System.out.println("用户ID: " + user.getId());
                System.out.println("用户名: " + user.getUserName());
                System.out.println("邮箱: " + user.getEmail());
                System.out.println("密码: " + user.getPassword());

                Job job = user.getJob();
                if (job != null) {
                    System.out.println("  └─ 岗位ID: " + job.getJobId());
                    System.out.println("  └─ 岗位名称: " + job.getJobName());
                }
                System.out.println("--------------------------------------------------");
            }

        }
    }


    @After
    public void tearDown() throws Exception {
        sqlSession.commit();
        sqlSession.close();
    }


}