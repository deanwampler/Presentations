// Exercise 1: Create a Book class that holds the title, author, publisher, 
// and ISBN number. Pretend the ISBN can be represented as a Long.
// First, create a MutableBook class with mutable fields. It should satisfy the 
// example code that uses it afterwards.
// Next, create an immutable version, Book. It should satisfy the example code
// that uses it, too.[1]
// Create instances, read the fields, etc. 
// What happens if you attempt to write one of the fields in Book?
// Bonus: add a "toString" method. Note that the "override" keyword
// is required any time you override a concrete method, like toString,
// which is implemented by Java's Object, if not intermediate classes.
//
// [1] Note that using names like Book vs. MutableBook suggests which one we think
//     you should use whenver possible.

/* ----------------- */
/* Define Book here: */

class MutableBook(
  var author: String,
  var title: String,
  var publisher: String,
  var isbn: Long
) {
  override def toString = 
    "MutableBook(author = %s, title = %s, publisher = %s, isbn = %d)".format(
      author, title, publisher, isbn)
}

class Book(
  val author: String,
  val title: String,
  val publisher: String,
  val isbn: Long
) {
  override def toString = 
    "Book(author = %s, title = %s, publisher = %s, isbn = %d)".format(
      author, title, publisher, isbn)
}

/* ----------------- */

// Create some books and test the results.

import CheapTests._

val mutableBook = new MutableBook(
  "Charles Dickens", "A Tale of Two Cities", "Victorian Publishing", 1234567890)

mutableBook.toString is 
    "MutableBook(author = Charles Dickens, title = A Tale of Two Cities, publisher = Victorian Publishing, isbn = 1234567890)"
mutableBook.author is "Charles Dickens"
mutableBook.title is "A Tale of Two Cities"
mutableBook.publisher is "Victorian Publishing"
mutableBook.isbn is 1234567890

mutableBook.author    = "Evelyn Waugh"
mutableBook.title     = "Brideshead Revisited"
mutableBook.publisher = "Interwar Books"
mutableBook.isbn      = 1122334455

mutableBook.toString is 
    "MutableBook(author = Evelyn Waugh, title = Brideshead Revisited, publisher = Interwar Books, isbn = 1122334455)"
mutableBook.author is "Evelyn Waugh"
mutableBook.title is "Brideshead Revisited"
mutableBook.publisher is "Interwar Books"
mutableBook.isbn is 1122334455

val book = new Book(
  "Jane Austin", "Pride and Prejudice", "Edwardian Novels", 1234554321)
book.toString is 
    "Book(author = Jane Austin, title = Pride and Prejudice, publisher = Edwardian Novels, isbn = 1234554321)"
book.author is "Jane Austin"
book.title is "Pride and Prejudice"
book.publisher is "Edwardian Novels"
book.isbn is 1234554321

// NOTE: It will fail with a compile error at this point, so comment out this line:
//book.author = "Emily Bronte"

println("Success!")