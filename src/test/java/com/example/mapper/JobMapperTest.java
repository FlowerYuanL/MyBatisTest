package com.example.mapper;

import com.example.pojo.Job;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class JobMapperTest {

    JobMapper jobMapper = null;
    SqlSession sqlSession = null;

    @Before
    public void setUp() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        jobMapper = sqlSession.getMapper(JobMapper.class);
    }

    @Test
    public void testDeleteById() {
        Job job = jobMapper.findById(1);
        System.out.println(job);
    }

    @After
    public void tearDown() {
        sqlSession.commit();
        sqlSession.close();
    }
}