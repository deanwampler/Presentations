// Implicit classes
// New to Scala 2.10 (SIP-13)
// https://docs.scala-lang.org/sips/pending/implicit-classes.html

// Before 2.10, you had to write the boilerplate we'll show first.
// We'll explain a few details after the declaration.

import scala.language.implicitConversions
class OldMirror(s: String) {
	def oldmirror: String = s+(s.reverse)
}
implicit def toOldMirrorable(s: String) = new OldMirror(s)

println ("1: Now is the time. ".oldmirror)

// When you compile this older-style syntax with scala v2.10, you get this
// warning WITHOUT the "import scala.language.implicitConversions":
//  <console>:8: warning: implicit conversion method toOldMirrorable should be enabled
//  by making the implicit value language.implicitConversions visible.
//  This can be achieved by adding the import clause 'import scala.language.implicitConversions'
//  or by setting the compiler option -language:implicitConversions.
//  See the Scala docs for value scala.language.implicitConversions for a discussion
//  why the feature should be explicitly enabled.
//         implicit def toOldMirrorable(s: String) = new OldMirror(s)
// That's why we added the import.
// This scala.language package is another new feature of 2.10, 
// "SIP 18: Modularizing Language Features", where select language features are 
// enabled by default, but only if you import the appropriate scala.language.X
// trait (which is what they are) or use a compiler flag, like 
// -language:implicitConversions. As for all imports, "import scala.language._" or
// the flag --language:_ will enable all such features. 
// See http://docs.scala-lang.org/sips/pending/modularizing-language-features.html
// for more details.

implicit class Mirror(s: String) {
	def mirror: String = s+(s.reverse)
}

println ("2: Now is the time. ".mirror)
