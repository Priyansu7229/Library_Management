import java.util.*;
class Book {
	int id;
	String title;
	String author;
	String genre;
	String availability;
	
	public Book(int id, String title, String author, String genre, String availability) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.availability = availability;
	}
	
	public void display() {
		System.out.println("Book ID: "+id+ "\nTitle: "+title+ "\nAuthor: "+author+ "\nGenre: "+genre+ "\nAvailability: "+availability);
	}
}

class Management {
	private static ArrayList<Book> bookobj = new ArrayList<Book>();
	private static HashSet<Integer> bookid = new HashSet<Integer>();
	
	//Add Book
	public static void add_book(int id, String title, String author, String genre, String availability) {
		//Book Id Unique Check
		if(bookid.contains(id)) {
			System.out.println("Entered Book ID - "+id+  " already available");
			return;
		}
		//Title and Author Non Empty Strings Check
		if(((title == null) || (title.trim().isEmpty())) || ((author == null) || (author.trim().isEmpty()))) {
			System.out.println("Title and Author should be non empty strings");
			return;
		}
		//To check availability status is either Available or Checked Out 
		if(!(availability.equalsIgnoreCase("Available")  || (availability.equalsIgnoreCase("Checked out")))) {
			System.out.println("Availability status should be entered either Available or Checked Out");
			return;
		}	
		
		bookobj.add(new Book(id, title, author, genre, availability));
		System.out.println("Succesfully added book record");
	}
	
	//View Books
	public static void view_book() {
		if(bookobj.isEmpty()) {
			System.out.println("Book Details is Empty");
			return;
		}
		for(Book b: bookobj) {
			b.display();
		}
	}
	
	//Search Book by Book Id
	public static void search_book(int id) {
		for(Book b: bookobj) {
			if(b.id==id) {
				b.display();
			}
		}
	}
	
	//Search Book by Title
	public static void search_book(String title) {
		for(Book b: bookobj) {
			if(b.title==title) {
				b.display();
			}
		}
	}
	
	//Update Book Title or Author
	public static void update_book(String upd) {
	    Scanner scan = new Scanner(System.in);
		String newupdate;
		for(Book b: bookobj) {
			if(b.title.equals(upd)) {
				System.out.print("Enter the new title - ");
				newupdate = scan.nextLine();
				b.title = newupdate;
				System.out.println("Successfully Updated Title");
				return;
			}
			if(b.author.equals(upd)) {
				System.out.print("Enter the new Author - ");
				newupdate = scan.nextLine();
				b.author = newupdate;
				System.out.println("Successfully Updated Author");
				return;
			}
				
		}
		System.out.println("Title or Author not found cannnot be updated ");
	
}
	
	//Update Availability Status
	public static void update_book(int id) {
	    Scanner scan = new Scanner(System.in);
		String status;
		for(Book b: bookobj) {
			if(b.id==id) {
				System.out.println("Enter Available or Checked Out");
				status = scan.next();
				//To check availability status is either Available or Checked Out 
		        if(status.equalsIgnoreCase("Available") || (status.equalsIgnoreCase("Checked out"))) {
					System.out.println("Availability status should be entered either Available or Checked Out");
					return;
				}
				b.title = status;
				System.out.println("Successfully Updated Availability Status");
				return;
			}
		}
		System.out.println("Title not found cannnot be updated ");
		return;
	}
	
	//Delete Book Record
	public static void delete_book(int id) {
		for(Book b: bookobj) {
			if(b.id == id) {
				bookobj.remove(bookobj.indexOf(b));
				System.out.println("Successfully Deleted Book Record");
				return;
			}
		}
		System.out.println("Book Id not found to be delted ");
	}
}

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int select;
        int choice;
		
		do {
			System.out.println(" Welcome To The Library Management System ");
			System.out.println("Press 1 To Add Book Detail");
			System.out.println("Press 2 To View All Book Records");
			System.out.println("Press 3 To Search Book Deatils by Id or Title");
			System.out.println("Press 4 To Update Book Title, Author, Availability Status");
			System.out.println("Press 5 To Delete Book Record by ID");
			System.out.println("Press 0 To Exit from the Application");
			System.out.print("Enter ");
			select = scan.nextInt();
			
			switch(select) {
				case 1:
					System.out.print("Enter Book ID - ");
					int id = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter Book Title - ");
					String title = scan.nextLine();
					System.out.print("Enter Book Author - ");
					String author = scan.nextLine();
					System.out.print("Enter Book Genre - ");
					String genre = scan.nextLine();
					System.out.print("Enter Book Availability(Available or Checked Out) - ");
					String avail = scan.nextLine();
					Management.add_book(id, title, author, genre, avail);
					break;
				case 2:
					Management.view_book();
					break;
				case 3:
				    System.out.print("Enter 1 to seach by ID and 2 to search by title   ---  ");
					choice = scan.nextInt();
					scan.nextLine();
					if(choice==1) {
					System.out.print("\nEnter Id - ");
					int search_id = scan.nextInt();
					Management.search_book(search_id);
					}
					if(choice==2) {
						System.out.print("\nEnter Title - ");
						String search_title = scan.nextLine();
						Management.search_book(search_title);
					}
					break;
				case 4:
					System.out.print("Enter 1 to update Title or Author, 2 to update Availabilty Status   ---  ");
					choice = scan.nextInt();
					scan.nextLine();
					if(choice==1) {
					System.out.print("Enter the Title or Author to be updated - ");
					String update = scan.nextLine();
					Management.update_book(update);
					}
					if(choice==2) {
						System.out.print("Enter Id for which you want to change Availability Status - ");
						int book_status = scan.nextInt();
						Management.update_book(book_status);
					}
					break;
				case 5:
					System.out.print("\nEnter Id to delete the book record - 	");
					int id_delete = scan.nextInt();
					Management.delete_book(id_delete);
					break;
				case 0:
					System.out. println("Successfully Exit");
					break;
				default:
					System.out.println("Please enter the number given in the menu list");
			}
		}
			while(select!=0);
		}
	}