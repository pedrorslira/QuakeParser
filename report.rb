require 'json'
require 'pp'

json = File.read('response.json')
hash = JSON.parse(json)

puts "===HASH==="
pp hash

hash.each do |h|
  puts "\n===RANKING GAME #{h["id"]}===\n" 
  h["players"].each_with_index do |p,index|
    puts "Nickname: #{h["players"][index]["nickname"]}" 
    puts "Total Kills: #{h["players"][index]["totalKills"]}"
  end
end




