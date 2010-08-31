require 'redcloth'

class Content
  
  # Wrap the parameters to :code
  #
  #  :code file.rb[:part class=xxx]
  class Descriptor
    attr_reader :file_name, :part
    attr_reader :css_class
    attr_reader :lang
    
    def initialize(string)
      @css_class = "code-normal"
      @lang = "scala"

      if string && string =~ /(.*?)\[(.*)\]/
        @file_name = $1
        parse_params($2.dup)
      else
        @file_name = string
      end
    end
    
    def parse_params(params)
      params.split.each do |param|
        if param =~ /(.*?)=(.*)/
          case $1
          when "class"
            @css_class = $2
          when "lang"
            @lang = $2
          else
            fail "Unknown parameter #{$1} in '#{params}'"
          end
        else
          @part = param
        end
      end
    end
        
  end
  
  START_SLIDE = %{<div class="slide">\n}
  END_SLIDE   = %{</div>\n}
  BETWEEN_SLIDES = END_SLIDE + "\n" + START_SLIDE

  CODE_PATTERN = /^:code\s+(.+)/

  # Temporarily prepended to lines of incldued code to stop them being processed
  # as Textfile
  
  INCLUDED_CODE_PREFIX = "XXX\001"     
  INCLUDED_CODE_PREFIX_REGEXP = /#{INCLUDED_CODE_PREFIX}/mo

  
  def initialize(original)
    @original = original.sub(/__END__.*/m, '').gsub(/__SKIP__.*?__ENDSKIP__/m, '')
  end
  
  def to_html
    textile = preprocess_inlinecode(@original)
    textile = preprocess_code(textile)
    content = split_into_slides(textile)
    html = RedCloth.new(content).to_html
    remove_code_escaping_from(html)
  end
  
  # look for :code filename[part] and substitute in the appropriate part
  # of the given file. Do in two steps because the regexp engine isn;t reentrant
  def preprocess_code(text)
    chunks = {}
    text.scan(CODE_PATTERN) do |file,|
      desc = Descriptor.new(file)
      chunks[file] = find_content_from(desc)
    end
    text.gsub(CODE_PATTERN) { file = $1.dup; format_code(Descriptor.new(file), chunks[file]) }
  end
  
  # Look for :inlinecode /.../:endinlinecode and substitute in as if it came from a file
  def preprocess_inlinecode(text)  
    state = :copying
    result = []
    text.split(/\n/).each do |line|
      case state
      when :copying    
        if line =~ /^:inlinecode(.*)/
          args = $1.strip
          desc = Descriptor.new("--[#{args}]")
          result << %{<div class="#{desc.css_class}">\n}
          result << %{\n<pre name="code" id="code" class="brush: #{desc.lang};">\n}
          # result << %{\n<pre name="code" id="code" class="#{desc.lang}:nogutter:nocontrols">\n}
          state = :incode
        else
          result << line
        end             
      when :incode
        if line =~ /^:endinlinecode/
          result << "</pre></div>\n\n"
          state = :copying
        else                                  
          result << INCLUDED_CODE_PREFIX + line
        end
      end
    end
                     
    result.join("\n")
  end
  
  def split_into_slides(textile)
    result = []
    slides = textile.split(/^h1/).each do |slide|
      unless slide.empty?
        result << START_SLIDE << "\nh1" << slide << END_SLIDE
      end
    end    
    result.join
  end
  
  def find_content_from(desc)
    begin
      content = File.read(desc.file_name)
    rescue   Exception => e
      STDERR.puts e.message
      exit 2
    end
    
   find_part_in(content, desc.part)
  end
  
  def find_part_in(content, part_name)
    result = []
    state = part_name ? :skipping : :normal
    content.each_line do |line|
      if line.sub!(/(START|END):(\w+)/, '')
        if $2 == part_name
          if $1 == "START"
            state = :normal
          else
            state = :skipping
          end
        end
        next
      end
      result << line unless state == :skipping
    end
    result.join
  end
  
  def format_code(desc, code) 
    code = code.gsub(/^/m, INCLUDED_CODE_PREFIX)
    %{<div class="#{desc.css_class}">\n} +
    %{\n<pre name="code" id="code" class="brush: #{desc.lang};">#{code}} +
#    %{\n<pre name="code" id="code" class="#{desc.lang}:nogutter:nocontrols">#{code}} +
    %{</pre></div>} +
    %{<div class="codeurl"><a href="txmt://open?url=file://#{File.expand_path(desc.file_name)}">#{desc.file_name}</a></div>\n\n}
  end  
  
  def remove_code_escaping_from(html)
    html.gsub(INCLUDED_CODE_PREFIX_REGEXP, '')
  end
end