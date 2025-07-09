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

    @Test
    public void testFindAll() {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
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
    public void testAddUser() {
        User user = User.builder()
                .userName("张三")
                .email("lsxp311@gmail.com")
                .password(8528)
                .build();
        System.out.println("插入之前的id数据："+user.getId());
        int changed_lines = userMapper.addUser(user);
        sqlSession.commit();
        System.out.println("受影响的行的数量为："+changed_lines);
        System.out.println("插入之后的id数据："+user.getId());
        System.out.println("Insert Success!");
    }

    @Test
    public void testSimpleDelete(){
        int id = 389;
        userMapper.simpleDelete(id);
        sqlSession.commit();
        List<User> users = userMapper.findAll();
        for(User user : users){
            System.out.println(user.toString());
        }
    }
}