package com.works.props;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Message implements Serializable {

    private String uuid;
    private String title;
    private String detail;

}
