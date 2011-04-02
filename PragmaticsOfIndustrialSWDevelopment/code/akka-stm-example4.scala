def receive = {
  case EitherOr(left, right, action) =>
    atomic {
      either {
        if (left.ok == false)
          retry
        else
          action(left)
      } orElse {
        if (right.ok == false)
          retry
        else
          action(right)        
      }
    }
}
