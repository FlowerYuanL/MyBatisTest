package com.example.mapper;

import com.example.pojo.Job;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {

    Job findById(int id);

    void updateJobById(@Param("job_id") int jobId,@Param("job_name") String jobName);
}
