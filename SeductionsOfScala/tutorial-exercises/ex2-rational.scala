// Exercise 2: A Rational Number Class
// Create a class for Rational Numbers, which represent division of one integer
// by another exactly, rather than computing the actual floating point value, 
// which is subject to round-off errors, etc.
// For example, 22/7 would be the value "new Rational(2,7)", rather than
// 3.142857142857143. (I picked this example because 22/7 is famously close to
// the value of Pi.) Here, 22 is the numerator and 7 is the denominator, in this case.
// Here are the rules for +, -, *, /, where r1 = n1/d2 and r2 = n2/d2:
//   r1 + r2    (n1*d2 + n2*d1)/d1*d2
//   r1 - r2    (n1*d2 - n2*d1)/d1*d2
//   r1 * r2    n1*n2/d1*d2
//   r1 / r2    n1*d2/d1*n2
//
// Create an immutable Rational class.
// Add methods for +, -, toString.
// Bonus: Add method * and /. 
// Test with examples.
