package br.com.fernandodutra.prj_android_digio.model;

import org.json.JSONObject;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 16/05/2019
 * Time: 21:38
 * Prj_Android_Digio
 */
public class Spotlight {

    public static final String NAME = "name";
    public static final String BANNERURL = "bannerURL";
    public static final String DESCRIPTION = "description";

    private String name;
    private String bannerURL;
    private String description;

    public Spotlight() {
        this.setName("");
        this.setBannerURL("");
        this.setDescription("");
    }

    public Spotlight(String name, String bannerURL, String description) {
        this.setName(name);
        this.setBannerURL(bannerURL);
        this.setDescription(description);
    }

    public Spotlight(JSONObject jsonObject) {
        try {
            this.setName(jsonObject.getString(NAME));
            this.setBannerURL(jsonObject.getString(BANNERURL));
            this.setDescription(jsonObject.getString(DESCRIPTION));
        } catch (Exception e) {
            this.setName("");
            this.setBannerURL("");
            this.setDescription("");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            jsonObject.put(NAME, name);
            jsonObject.put(BANNERURL, bannerURL);
            jsonObject.put(DESCRIPTION, description);

            return jsonObject;
        } catch (Exception e) {
            return null;
        }
    }
}
