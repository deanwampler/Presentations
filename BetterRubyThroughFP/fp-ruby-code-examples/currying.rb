mod = lambda {|m, n| n % m}
p mod.(2,5)  # ==> 1

modcurry = mod.curry
mod2 = modcurry.(2)
p mod2.(5)   # ==> 1

mod3 = modcurry.(3)
p mod3.(5)   # ==> 2
p mod3.class

p mod35 = modcurry.(3,5)  # ==> 2
p mod35.class