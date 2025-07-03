package com.example.mapper;

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

public class UserMapperTest{

    UserMapper userMapper = null;
    SqlSession sqlSession = null;

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
        userMapper = sqlSession.getMapper(UserMapper.class);

    }

    @Test
    public void testFindById() {
        User user = userMapper.findById(3);
        System.out.println(user.toString());
    }

    @Test
    public void testFindTotal() {
        int sum_of_users = userMapper.findTotal();
        System.out.println("The sum of users is "+sum_of_users);
    }

    /*
    * 删除用户信息
    * 实现：del设置为1
    * */
    @Test
    public void testDeleteById() {
        userMapper.deleteById(3);
        List<User> user = userMapper.findAll();
        System.out.println(user.toString());
        sqlSession.commit();
    }

    @After
    public void tearDown(){
        sqlSession.close();
    }

    @Test
    public void insert() {
        User user = User.builder()
                .userName("lsxp")
                .email("lsxp311@gmail.com")
                .password(8528)
                .build();
        userMapper.insert(user);
        sqlSession.commit();
        System.out.println("Insert Success!");
    }
}