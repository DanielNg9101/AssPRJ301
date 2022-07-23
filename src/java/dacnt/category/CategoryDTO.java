/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.category;

import java.io.Serializable;

/**
 *
 * @author Daniel NG
 */
public class CategoryDTO implements Serializable {
    private int cateID;
    private String cateName;

    public CategoryDTO() {
    }

    public CategoryDTO(int cateID, String cateName) {
        this.cateID = cateID;
        this.cateName = cateName;
    }

    /**
     * @return the cateID
     */
    public int getCateID() {
        return cateID;
    }

    /**
     * @param cateID the cateID to set
     */
    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    /**
     * @return the cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * @param cateName the cateName to set
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    
    
}
