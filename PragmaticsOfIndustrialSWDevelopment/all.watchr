watch( '^slides.*/*\.slides' )  { |m| rake_all  m[0] }
watch( '^(lib|ui).*/*\.(css|js)' )  { |m| rake_all  m[0] }

# --------------------------------------------------
# Helpers
# --------------------------------------------------
def rake_all(*paths)
  system("rake all")
end
