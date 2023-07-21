package Practical.entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BasicEntity {
    private Date createDate; //등록일
    private Date lastModifiedDate; //수정일

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
