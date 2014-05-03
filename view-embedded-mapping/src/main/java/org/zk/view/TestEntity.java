package org.zk.view;

//import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TestEntity implements Serializable {
    private static final long serialVersionUID = -3793347872874713865L;
    
    @Column(name = "prva_day", insertable=false, updatable=false)
    private String prvi;
    @Column(name = "druga_day", insertable=false, updatable=false)
    private String drugi;
    
    // an example of Hibernate specific feature 
    //@Formula("prva_day || druga_day")
    //String combined;

    public TestEntity() {

    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "prvi='" + prvi + '\'' +
                ", drugi='" + drugi + '\'' +
                //", combined='" + combined + '\'' +
                '}';
    }

    public String getPrvi() {
        return prvi;
    }

    public void setPrvi(String prvi) {
        this.prvi = prvi;
    }

    public String getDrugi() {
        return drugi;
    }

    public void setDrugi(String drugi) {
        this.drugi = drugi;
    }

    //public String getCombined() {
    //    return combined;
    //}
}
