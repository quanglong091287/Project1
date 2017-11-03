package longtq2.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ListenGuidelineDTO implements Serializable {

    private Integer listenguidelineId;
    private String title;
    private String image;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifileDate;

    public Integer getListenguidelineId() {
        return listenguidelineId;
    }

    public void setListenguidelineId(Integer listenguidelineId) {
        this.listenguidelineId = listenguidelineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifileDate() {
        return modifileDate;
    }

    public void setModifileDate(Timestamp modifileDate) {
        this.modifileDate = modifileDate;
    }
}
