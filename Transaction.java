import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
	private static Transaction instance;
	private List<String> transactionHistory;
	private Transaction() {
		transactionHistory = new ArrayList<>();
		
	}
	public static Transaction getTransaction() {
		if(instance == null) {
			instance = new Transaction();
		}
		return instance;
	}
   public void displayTransactionHistory() {
	   try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
	        String transaction;
	        while ((transaction = reader.readLine()) != null) {
	            System.out.println(transaction);
	        }
	    } catch (IOException e) {
	        
	    }
	        
	        
	    }
	

	    
	    
	    

	private void saveTransaction(String transactionDetails) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"))) {
			writer.write(transactionDetails);
			writer.newLine();
		}catch (IOException e) {
		
		}
	}
	

    // Perform the borrowing of a book
    public  boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            transactionHistory.add(transactionDetails);
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public  void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            transactionHistory.add(transactionDetails);
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }
   

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
   
}
