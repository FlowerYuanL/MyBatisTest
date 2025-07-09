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

    @Test
    public void testUpdateJobById() {
        int jobId = 1;
        String jobName = "teacher";
        Job originJob = jobMapper.findById(jobId);
        System.out.println("Origin job of " + originJob.getUserName()+":"+originJob.getJobName());
        jobMapper.updateJobById(jobId, jobName);
        sqlSession.commit();
        Job finalJob = jobMapper.findById(jobId);
        System.out.println("Final job of " + finalJob.getUserName()+":"+finalJob.getJobName());
    }

    @After
    public void tearDown() {
        sqlSession.close();
    }
}