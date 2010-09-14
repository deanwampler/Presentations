require "pressie/content" 
require 'yaml'

S5_HEAD = %{<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>&title;</title>
<!-- metadata -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="S5" />
<meta name="version" content="S5 1.1" />
<meta name="date" content="&date;" />
<meta name="author" content="&author;" />
<meta name="company" content="&company;" />
<meta name="copyright" content="&copyright;" />
<!-- configuration parameters -->
<meta name="defaultView" content="slideshow" />
<meta name="controlVis" content="hidden" />
<!-- style sheet links -->
<link rel="stylesheet" href="../ui/default/slides.css" type="text/css" media="projection" id="slideProj" />
<link rel="stylesheet" href="../ui/default/outline.css" type="text/css" media="screen" id="outlineStyle" />
<link rel="stylesheet" href="../lib/stylesheets/print.css" type="text/css" media="print" id="slidePrint" />
<link rel="stylesheet" href="../ui/default/opera.css" type="text/css" media="projection" id="operaFix" />

<link rel="stylesheet" href="../lib/stylesheets/pressie.css" type="text/css" />

<!-- S5 JS -->
<script src="../ui/default/slides.js" type="text/javascript"></script>

<!-- Syntax Highlighter -->
<!-- <link rel="stylesheet"  href="../lib/stylesheets/SyntaxHighlighter.css"></link> -->
<link type="text/css" rel="stylesheet" href="../lib/stylesheets/shCore.css"/>
<link type="text/css" rel="stylesheet" href="../lib/stylesheets/shThemeDefault.css"/>


</head>
<body>

<div class="layout">
<div id="controls"><!-- DO NOT EDIT --></div>
<div id="currentSlide"><!-- DO NOT EDIT --></div>
<div id="header"></div>
<div id="footer">
<h2>Copyright &copy; &copyright;</h2>
<h2>&date;</h2>
</div>

</div>


<div class="presentation">
}

S5_TAIL = %{
  <!-- Syntax Highlighter -->
  <script language="javascript" type="text/javascript" src="../lib/scripts/shCore.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushBash.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushCpp.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushCSharp.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushCss.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushDelphi.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushJava.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushJScript.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushPhp.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushPlain.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushPython.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushRuby.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushScala.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushSql.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushVb.js"></script>
  <script language="javascript" type="text/javascript" src="../lib/scripts/shBrushXml.js"></script>
  <script language="javascript">
    SyntaxHighlighter.config.clipboardSwf = 'scripts/clipboard.swf';
    SyntaxHighlighter.all();
  </script>
</div>
</body>
</html>
}


class Pressie
  
  def self.process
    new.process
  end
  
  def process    
    metadata_name = ARGV.shift || usage("Missing metadata file name")
    load_metadata(metadata_name)
    input_name = ARGV.shift || usage("Missing input file name")
    content = Content.new(File.read(input_name)) rescue usage($!.message)  
    header = substitute_metadata_into(S5_HEAD)
    puts header, content.to_html, S5_TAIL
  end
  
  
  private
  
  def usage(msg = nil)
    STDERR.puts "pressie.rb  <metadatafile> <inputfile>"
    if msg
      STDERR.puts
      STDERR.puts msg
    end
    exit 1
  end                                     
  
  def load_metadata(file_name)
    @metadata = YAML.load_file(file_name)
  end          
  
  def substitute_metadata_into(text)
    text = text.dup
    @metadata.each_key do |key|
      text.gsub!(/&#{key};/, @metadata[key])
    end
    text
  end
      
end