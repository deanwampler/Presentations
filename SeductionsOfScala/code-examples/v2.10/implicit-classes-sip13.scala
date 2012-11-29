// Implicit classes
// New to Scala 2.10 (SIP-13)

// Before 2.10, you had to write the following.
// Note that with 2.10, you get this warning now for this
// older construct:
// 	<console>:8: warning: implicit conversion method toOldMirrorable should be enabled
// 	by making the implicit value language.implicitConversions visible.
// 	This can be achieved by adding the import clause 'import scala.language.implicitConversions'
// 	or by setting the compiler option -language:implicitConversions.
// 	See the Scala docs for value scala.language.implicitConversions for a discussion
// 	why the feature should be explicitly enabled.
// 	       implicit def toOldMirrorable(s: String) = new OldMirror(s)
// That's why we added the following import:
import scala.language.implicitConversions
class OldMirror(s: String) {
	def oldmirror: String = s+(s.reverse)
}
implicit def toOldMirrorable(s: String) = new OldMirror(s)

println ("1: Now is the time. ".oldmirror)

implicit class Mirror(s: String) {
	def mirror: String = s+(s.reverse)
}

println ("2: Now is the time. ".mirror)
