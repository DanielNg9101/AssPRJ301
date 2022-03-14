/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.plant;

import java.io.Serializable;

/**
 *
 * @author Daniel NG
 */
public class PlantDTO implements Serializable {

    private int id;
    private String name;
    private int price;
    private String imgPath;
    private String description;
    private int status;
    private int cateid;
    private String catename;

    public PlantDTO() {
    }

    public PlantDTO(int id, String name, int price, String imgPath, String description, int status, int cateid, String catename) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.cateid = cateid;
        this.catename = catename;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath the imgPath to set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the cateid
     */
    public int getCateid() {
        return cateid;
    }

    /**
     * @param cateid the cateid to set
     */
    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    /**
     * @return the catename
     */
    public String getCatename() {
        return catename;
    }

    /**
     * @param catename the catename to set
     */
    public void setCatename(String catename) {
        this.catename = catename;
    }
    
    
}
