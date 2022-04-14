package pmo.project.repo.models;

public class ParsedDate {
   private int _year;
   private int _month;
   private int _date;
   
   public ParsedDate(String date) {
	   String[] prasedDate = date.split("-");
	   
	   this._date = Integer.parseInt(prasedDate[0]);
	   this._month = Integer.parseInt(prasedDate[1]);
	   this._date = Integer.parseInt(prasedDate[2]);
   }
   
   public int getYear() {
	   return this._year;
   }
   
   public int getMonth() {
	   return this._month;
   }
   
   public int getDate() {
	   return this._date;
   }
}
