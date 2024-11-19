/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pak1;

import java.util.Date;

/**
 *
 * @author revan
 */
public class Expense {
    private Long expenseID = System.currentTimeMillis();
    private Long categoryID;
    private Float amount;
    private Date date;
    private String remark; //description of the expense

    public Expense() {
    }
    public Expense(Long categoryID, Float amount, Date date, String remark){
        this.categoryID = categoryID;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
        
    }
    public Long getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(Long expenseID) {
        this.expenseID = expenseID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark(){
        return remark;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    
}
