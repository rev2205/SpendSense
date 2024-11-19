/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pak1;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author revan
 */
public class Home {
    private Repository repo = Repository.getRepository();
    ReportService reportservice = new ReportService();
    private Scanner sc = new Scanner(System.in);
    private int choice;
    
    public void Menu(){
        do{
            MenuOptions();
            choice = sc.nextInt();
            switch(choice){
                case 1 : 
                    onAddCategory();
                    pressAnyKeyToContinue();
                    break;
                case 2 :
                    onCategoryList();
                    pressAnyKeyToContinue();
                    break;
                case 3 :
                    onExpenseEntry();
                    pressAnyKeyToContinue();
                    break;
                case 4 :
                    onExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 5 :
                    onMonthlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 6 :
                    onYearlyExpenseList();
                    pressAnyKeyToContinue();
                    break;    
                case 7 :
                    onCategoryExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 0 :
                    Exit();
                    break;
            }
        }while(true);

    }
    
    public void MenuOptions(){
        System.out.println("----------MENU----------");
        System.out.println("1.Add Category");
        System.out.println("2.Category List");
        System.out.println("3.Expense Entry");
        System.out.println("4.Expense List");
        System.out.println("5.Monthly Expense List");
        System.out.println("6.Yearly Expense List");
        System.out.println("7.Categorized Expense List");
        System.out.println("0.Exit");
        System.out.println("------------------------");
        System.out.print("Enter Your Choice:");
    }
    public void onAddCategory(){
        sc.nextLine();//new line char is read here which is already present in stream and it is not in use for now
        System.out.print("Enter category name : ");
        String catName = sc.nextLine();
        Category cat = new Category(catName);
        repo.catList.add(cat);
        System.out.println("Category added Successfully");
    }
    public void onCategoryList(){
        System.out.println("Category List");
        List<Category> clist = repo.catList;
        for(int i = 0; i<clist.size();i++){
            Category c = clist.get(i);
            System.out.println((i+1)+". "+ c.getName()+", "+c.getCategoryID());
        }
    }
    public void onExpenseEntry() {
        System.out.println("Choose the category to make an entry..");
        onCategoryList();
        System.out.print("Choose Category: ");
        int catChoice = sc.nextInt();
        Category selectedCat = repo.catList.get(catChoice-1);
        System.out.println("You have chosen : "+catChoice+". "+selectedCat.getName());
        System.out.print("Enter Amount : ");
        float amount = sc.nextFloat();
        System.out.print("Enter Remark : ");
        sc.nextLine();
        String remark = sc.nextLine();
        System.out.print("Enter date(DD/MM/YYYY):");
        String dateAsString = sc.nextLine();
        Date date = DateUtil.stringToDate(dateAsString);
        
        //Add expense details in Expense object
        Expense exp = new Expense();
        exp.setCategoryID(selectedCat.getCategoryID());
        exp.setAmount(amount);
        exp.setRemark(remark);
        exp.setDate(date);
        //store expese details in repository
        repo.expList.add(exp);
        System.out.println("Expense added successfully...");
    }
    public void onExpenseList() {
        System.out.println("Expense List");
        List<Expense> expList = repo.expList;
        for(int i=0; i<expList.size(); i++){
            Expense exp = expList.get(i);
            String catName = reportservice.getCategoryNameById(exp.getCategoryID());
            String dateString = DateUtil.dateToString(exp.getDate());
            System.out.println((i+1)+". "+catName+", "+exp.getAmount()+", "+exp.getRemark()+", "+dateString);
        }
    }
    public void onMonthlyExpenseList() {
        System.out.println("Monthly List :");
        Map<String,Float> resultMap = reportservice.MonthlyTotal();
        Set<String> keys = resultMap.keySet();
        for(String yearMonth : keys){
            String[] arr = yearMonth.split(",");
            String year = arr[0];
            Integer monthNo = new Integer(arr[1]);
            String monthName = DateUtil.getMonthName(monthNo);
            System.out.println(year+", "+monthName+" : "+resultMap.get(yearMonth));
        }
    }
    public void onYearlyExpenseList() {
        System.out.println("Yearly Total.. :");
        Map<Integer,Float> resultMap = reportservice.YearlyTotal();
        Set<Integer> years = resultMap.keySet();
        Float total = 0.0f;
        for(Integer year : years){
            Float exp = resultMap.get(year);
            total += exp;
            System.out.println(year+" : "+exp);
        }
        System.out.println("----------------------");
        System.out.println("Total amount spent : "+total);
    }
    public void onCategoryExpenseList() {
        System.out.println("Category Total... :");
        Map<String,Float> resultMap = reportservice.CategoryTotal();
        Set<String> categories = resultMap.keySet();
        Float Total = 0.0f;
        for(String categoryName : categories){
            Float catTotal = resultMap.get(categoryName);
            Total += catTotal;
            System.out.println(categoryName+" : "+catTotal);
        }
        System.out.println("--------------------------------");
        System.out.println("Total amount spent : "+Total);
    }
    public void Exit(){
        System.out.println("Exiting!!!, Thanks for using SpensSense");
        System.exit(0);
    }
    public void pressAnyKeyToContinue(){
        try{
            System.out.println("Press Enter key to continue... ");
            System.in.read();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    
}
