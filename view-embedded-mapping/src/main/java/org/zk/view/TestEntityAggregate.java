package org.zk.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zkadragic
 * Date: 11/11/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Immutable
@Table( name = "view_test" )
public class TestEntityAggregate implements Serializable {


    @Id
    private Integer id;

    public void setDay(TestEntity day) {
        this.day = day;
    }

    public void setMonth(TestEntity month) {
        this.month = month;
    }

    @Embedded
    private TestEntity day;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="prvi", column=@Column(name="prva_month")),
            @AttributeOverride(name="drugi", column=@Column(name="druga_month")),
    })
    private TestEntity month;


        public TestEntityAggregate() {
            // this form used by Hibernate
        }

        public Integer getId() {
            return id;
        }

        private void setId(Integer id) {
            this.id = id;
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
