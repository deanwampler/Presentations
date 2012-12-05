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
/* ----------------- */

// Create some books and test the results.

val mutableBook = new MutableBook(
  "Charles Dickens", "A Tale of Two Cities", "Victorian Publishing", 1234567890)
println(mutableBook)
println(mutableBook.author)
println(mutableBook.title)
println(mutableBook.publisher)
println(mutableBook.isbn)

mutableBook.author    = "Evelyn Waugh"
mutableBook.title     = "Brideshead Revisited"
mutableBook.publisher = "Interwar Books"
mutableBook.isbn      = 1122334455
println(mutableBook)
println(mutableBook.author)
println(mutableBook.title)
println(mutableBook.publisher)
println(mutableBook.isbn)

val book = new Book(
  "Jane Austin", "Pride and Prejudice", "Edwardian Novels", 1234554321)
println(book)
println(book.author)
println(book.title)
println(book.publisher)
println(book.isbn)
// NOTE: It will fail with a compile error at this point, so comment it out.
book.author = "Emily Bronte"
