package yeeooh.android.app.com.yeeoohnewapp.model;

import java.util.List;

/**
 * Created by YEEOOH on 5/19/2017.
 */

public class wallmodel {


    private String image_title;
    private Integer image_id;
    private List<CreateList> listWalls;


    public Integer getImage_id() {
        return image_id;
    }

    public List<CreateList> getListWalls() {
        return listWalls;
    }

    public String getImage_title() {
        return image_title;
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

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public void setListWalls(List<CreateList> listWalls) {
        this.listWalls = listWalls;
    }
}
