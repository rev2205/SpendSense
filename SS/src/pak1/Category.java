/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pak1;

/**
 *
 * @author revan
 */
public class Category {
    private Long categoryID = System.currentTimeMillis();
    private String name;
    public Category(String name)
    {
        this.name = name;
    }
    public Category(Long categoryID, String name){
        this.categoryID = categoryID;
        this.name = name;
    }

    public Category() {
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
