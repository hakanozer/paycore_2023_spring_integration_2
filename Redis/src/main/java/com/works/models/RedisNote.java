package com.works.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("Note")
@Data
public class RedisNote {

    @Id
    @JsonIgnore
    private String id;

    private long nid;
    private String title;
    private String detail;



}
