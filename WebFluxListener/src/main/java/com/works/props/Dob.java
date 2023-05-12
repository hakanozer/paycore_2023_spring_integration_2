package com.works.props;

import java.time.OffsetDateTime;

@lombok.Data
public class Dob {
    private OffsetDateTime date;
    private Long age;
}
