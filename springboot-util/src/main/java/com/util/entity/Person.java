package com.util.entity;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 6/2/2019 1:09 PM
 */
@Data
public class Person {
    private Integer id;
    private String name;
    private List<String> groupNames;
    private Dept dept;
    private List<Dept> deptList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(groupNames, person.groupNames) &&
                Objects.equals(dept, person.dept) &&
                Objects.equals(deptList, person.deptList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupNames, dept, deptList);
    }
}
