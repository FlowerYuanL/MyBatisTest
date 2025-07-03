package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    private int jobId;
    private String userName;
    private String jobName;
    private boolean del;
}
