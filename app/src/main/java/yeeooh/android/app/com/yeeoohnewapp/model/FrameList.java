package yeeooh.android.app.com.yeeoohnewapp.model;

import java.util.List;

/**
 * Created by Narendra on 5/18/2017.
 */

public class FrameList {

    private String image_title;
    private Integer image_id;
    private List<FrameList> listWalls;

    public String getImage_title() {
        return image_title;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public List<FrameList> getListWalls() {
        return listWalls;
    }

    public void setListWalls(List<FrameList> listWalls) {
        this.listWalls = listWalls;
    }

    public void setImage_title(String android_version_name) {
        this.image_title = android_version_name;

    }

    public Integer getImage_ID() {
        return image_id;
    }

    public void setImage_ID(Integer android_image_url) {
        this.image_id = android_image_url;
    }
}