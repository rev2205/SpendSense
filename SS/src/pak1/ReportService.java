
package pak1;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author revan
 */
public class ReportService {
    Repository repo = Repository.getRepository();
    public Map<String,Float> MonthlyTotal(){
        Map<String,Float> m = new TreeMap<>();
        for(Expense exp : repo.expList){
            Date expDate = exp.getDate();
            String YearMonth = DateUtil.getYearAndMonth(expDate);
            if(m.containsKey(YearMonth)){
                Float total = m.get(YearMonth);
                total +=exp.getAmount();
                m.put(YearMonth, total);
            }
            else{
                m.put(YearMonth, exp.getAmount());
            }
        }
        return m;
    }
    
    public Map<Integer,Float> YearlyTotal(){
        Map<Integer,Float> m = new TreeMap<>();
        for(Expense exp : repo.expList){
            Date expDate = exp.getDate();
            Integer year = DateUtil.getYear(expDate);
            if(m.containsKey(year)){
                Float total = m.get(year);
                total +=exp.getAmount();
                m.put(year, total);
            }
            else{
                m.put(year, exp.getAmount());
            }
        }
        return m;
    }
    
    public Map<String,Float> CategoryTotal(){
        Map<String,Float> m = new TreeMap<>();
        for(Expense exp : repo.expList){
            Long categoryId = exp.getCategoryID();
            String catName = this.getCategoryNameById(categoryId);
            if(m.containsKey(catName)){
                Float total = m.get(catName);
                total +=exp.getAmount();
                m.put(catName, total);
            }
            else{
                m.put(catName, exp.getAmount());
            }
        }
        return m;
    }
    public String getCategoryNameById(Long categoryId){
        for(Category c : repo.catList){
            if(c.getCategoryID().equals(categoryId)){
                return c.getName();
            }
        }
        return null;
    }
}


