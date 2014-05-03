package org.zk.view;

//import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Immutable
@Table( name = "view_test" )
public class TestEntityAggregate implements Serializable {
    private static final long serialVersionUID = 6583871023213001867L;
    
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

    public Long getId() {
        return id;
    }
    
    

    public TestEntity getDay() {
		return day;
	}

	public TestEntity getMonth() {
		return month;
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
