package br.com.fernandodutra.prj_android_digio.model;

import org.json.JSONObject;
import org.w3c.dom.NamedNodeMap;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 16/05/2019
 * Time: 21:38
 * Prj_Android_Digio
 */
public class Products {

    public static final String NAME = "name";
    public static final String IMAGEURL = "imageURL";
    public static final String DESCRIPTION = "description";

    private String name;
    private String imageURL;
    private String description;

    public Products() {
        this.setName("");
        this.setImageURL("");
        this.setDescription("");
    }

    public Products(String name, String imageURL, String description) {
        this.setName(name);
        this.setImageURL(imageURL);
        this.setDescription(description);
    }

    public Products(JSONObject jsonObject) {
        try {
            this.setName(jsonObject.getString(NAME));
            this.setImageURL(jsonObject.getString(IMAGEURL));
            this.setDescription(jsonObject.getString(DESCRIPTION));
        } catch (Exception e) {
            this.setName("");
            this.setImageURL("");
            this.setDescription("");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject toJSONObject() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(NAME, name);
            jsonObject.put(IMAGEURL, imageURL);
            jsonObject.put(DESCRIPTION, description);

            return jsonObject;
        } catch (Exception e) {
            return null;
        }
    }

}
