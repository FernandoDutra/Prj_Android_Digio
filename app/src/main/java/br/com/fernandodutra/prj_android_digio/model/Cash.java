package br.com.fernandodutra.prj_android_digio.model;

import org.json.JSONObject;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 16/05/2019
 * Time: 21:39
 * Prj_Android_Digio
 */
public class Cash {

    public static final String TITLE = "title";
    public static final String BANNERURL = "bannerURL";
    public static final String DESCRIPTION = "description";

    private String title;
    private String bannerURL;
    private String description;

    public Cash() {
        this.setTitle("");
        this.setBannerURL("");
        this.setDescription("");
    }

    public Cash(String title, String bannerURL, String description) {
        this.setTitle(title);
        this.setBannerURL(bannerURL);
        this.setDescription(description);
    }

    public Cash(JSONObject jsonObject) {
        try {
            this.setTitle(jsonObject.getString(TITLE));
            this.setBannerURL(jsonObject.getString(BANNERURL));
            this.setDescription(jsonObject.getString(DESCRIPTION));
        } catch (Exception e) {
            this.setTitle("");
            this.setBannerURL("");
            this.setDescription("");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
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
            jsonObject.put(TITLE, title);
            jsonObject.put(BANNERURL, bannerURL);
            jsonObject.put(DESCRIPTION, description);

            return jsonObject;
        } catch (Exception e) {
            return null;
        }
    }
}
