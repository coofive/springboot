package com.util.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 6/2/2019 1:11 PM
 */
@Data
@Accessors(chain = true)
public class Dept {
    private Integer id;
    private String deptName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dept dept = (Dept) o;
        return Objects.equals(id, dept.id) &&
                Objects.equals(deptName, dept.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptName);
    }
}
