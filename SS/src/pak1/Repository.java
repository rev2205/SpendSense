/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pak1;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author revan
 */
//this class is used as data base and it is a Single ton repository
//single ton means it has only one object
public class Repository {
    public List<Expense> expList = new ArrayList();
    public List<Category> catList = new ArrayList();
    private static Repository repository;
    private Repository(){
    }
    public static Repository getRepository(){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }
}