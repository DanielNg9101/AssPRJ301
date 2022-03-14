/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.order;

/**
 *
 * @author dacng
 */
public class OrderDTO {
    private int orderID;
    private String orderDate;
    private String shipDate;
    private int status;
    private int accID;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, String orderDate, String shipDate, int status, int accID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accID = accID;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the shipDate
     */
    public String getShipDate() {
        return shipDate;
    }

    /**
     * @param shipDate the shipDate to set
     */
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
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
     * @return the accID
     */
    public int getAccID() {
        return accID;
    }

    /**
     * @param accID the accID to set
     */
    public void setAccID(int accID) {
        this.accID = accID;
    }
    
    
}
