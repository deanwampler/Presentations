# Uses MenTaLguY's Concurrent Gem
# Runs on MRI (1.8)
# Example adapted from http://groups.google.com/group/ruby-talk-google/browse_thread/thread/ec442079705d168a

require 'rubygems'
require 'concurrent/actors'
require 'case'

require 'shapes'

include Concurrent::Actors 

Message = Case::Struct.new :reply_to, :body

def make_message body
  # getting "mailbox" address of the current actor 
  current = Actor.current 
  Message.new current, body
end

display = Actor.spawn do 
  loop do 
    # guarded receive (does case-like matching via #===) 
    Actor.receive do |msg| 
      msg.when Message[Object, Shape] do |m| 
        s = m.body.draw
        m.reply_to << "drew: #{s}"
      end 
      msg.when Message[Object, :exit] do |m|
        m.reply_to << "exiting"
      end
      msg.when Message[Object, Object] do |m|
        m.reply_to << "Error! #{m.body}"
      end 
    end
  end
end 

# sending shapes to display.
display << make_message(Rectangle.new)
display << make_message(Circle.new)
# sending other stuff...
display << make_message("Hello Display!")
display << make_message(:exit)

loop do
  Actor.receive do |msg| 
    msg.when "exiting" do
      p "exiting..."
      exit
    end 
    msg.when String do |s|
      p "reply: #{s}"
    end
  end  
end
