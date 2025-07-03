package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String userName;
    private String email;
    private int password;
    @Builder.Default
    private boolean del=false;
    @Builder.Default
    private int id=0;

    private Job job;

}
