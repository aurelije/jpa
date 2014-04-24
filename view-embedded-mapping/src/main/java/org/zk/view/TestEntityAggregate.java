package org.zk.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table( name = "view_test" )
public class TestEntityAggregate implements Serializable {


    @Id
    private Long id;
    @Embedded
    private TestEntity day;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "prvi", column = @Column(name = "prva_month")),
            @AttributeOverride(name = "drugi", column = @Column(name = "druga_month")),
    })
    private TestEntity month;

    public TestEntityAggregate() {
        // this form used by Hibernate
    }

    public void setDay(TestEntity day) {
        this.day = day;
    }

    public void setMonth(TestEntity month) {
        this.month = month;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TestEntityAggregate{" +
                "id=" + id +
                ", day=" + day +
                ", month=" + month +
                '}';
    }
}
