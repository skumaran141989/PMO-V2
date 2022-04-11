package pmo.project.models;

public class ParsedDate {
   private int _year;
   private int _month;
   private int _date;
   
   public ParsedDate(String date) {
	   String[] prasedDate = date.split("-");
	   
	   _date = Integer.parseInt(prasedDate[0]);
	   _month = Integer.parseInt(prasedDate[1]);
	   _date = Integer.parseInt(prasedDate[2]);
   }
   
   public int getYear() {
	   return _year;
   }
   
   public int getMonth() {
	   return _month;
   }
   
   public int getDate() {
	   return _date;
   }
}
