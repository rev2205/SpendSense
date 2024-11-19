/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pak1;

/**
 *
 * @author revan
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Spend Sense Application!!\nThis is the place where you can track your expenditure");
        System.out.println("Note : Before choosing other options, please add category and make an expense entry");
        Home service = new Home();
        service.Menu();
    }
    
}
