import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;

public class LibraryManagementTest {

	@Test
	public void testBoundary() throws Exception{
		Book testBook1 = new Book(100,"Boundary val 1");
		Book testBook2 = new Book(999,"Boundary val 2");
		assertEquals(100,testBook1.getId());
		assertEquals(999,testBook2.getId());
	}
	
	@Test
	public void testInvalid() {
		try {
			new Book(90,"Invalid1");
			fail("Error  , ID less than 100");
			
		}catch (Exception e) {
			assertEquals("Please Give an ID number between 100 and 999!",e.getMessage());
		}
	
	try {
		new Book(1100,"Invalid2");
		fail("Please Give an ID number between 100 and 999!");
	
	}catch(Exception e) {
		assertEquals("Please Give an ID number between 100 and 999!",e.getMessage());}
	}
	
	@Test
	public void testMessage() throws Exception {
		Book bookTest = null;
		try {
			bookTest = new Book(100, "Science");
		
		}catch (Exception e) {
			fail("Unsucessful");
		}
		assertTrue(bookTest.isValidId(100));
		assertTrue(bookTest.isValidId(999));
		assertFalse(bookTest.isValidId(99));
		assertFalse(bookTest.isValidId(1000));
		
	

}
	@Test
	public void testBorrowReturn() throws Exception {
		Book book = new Book (500, "Paleontology");
		Member member = new Member(5, "Raj");
		assertTrue(book.isAvailable());
		
		Transaction transaction = Transaction.getTransaction();
		boolean bookAvailable = transaction.borrowBook(book, member);
		assertTrue(bookAvailable);
		assertFalse(book.isAvailable());
		
		boolean checkUnavailable = transaction.borrowBook(book, member);
		assertFalse(checkUnavailable);
		
		boolean returnBook = transaction.returnBook(book, member);
		assertTrue (returnBook);
		assertTrue(book.isAvailable());
		
		boolean checkReturn = transaction.returnBook(book, member);
		assertFalse(checkReturn);
		

	
}
	@Test
	public void testSingletonTransaction() throws Exception {
		try {
			Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
			int modifier= constructor.getModifiers();
			assertEquals(2,modifier);
			
		}catch (Exception e) {
			throw new Exception("Error! Specified constructor was not found");
		}
	}
}

