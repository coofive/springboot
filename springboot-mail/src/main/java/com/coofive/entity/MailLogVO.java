package com.coofive.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author coofive
 */
@Data
@Accessors(chain = true)
public class MailLogVO {

    private String creator;

    private Timestamp createTime;
}
