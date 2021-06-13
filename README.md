Welcome to EmeraldBank. The Bukkit/Spigot/Paper plugin for banking emeralds. You can:

Make banks
Deposit into them withholding the emeralds/iron or by typing into the command
Uses iron and emeralds as currency
Use an atm command to get a certain amount of emeralds in a bank
See how many emeralds and iron a bank has!
Coming soon:

A way better API
A GUI for selecting a bank
Autopay. Paying a certain amount at a schedule
Paying to other banks
And more!


API:

GetBankPlace:
GetBankPlace.getBankPlace(bankName);
IsInt:
IsInt.isInt(a string);


For a maven repo, please check GitHub packages!

Commands:
/eb help: Opens the help menu
/eb new [name]: Creates a new bank
/eb deposit [name] [optional: emeralds] [optional: iron]: Deposit emeralds or iron to the specified bank. If no amount is specified, then the amount of emeralds/iron you are holding is deposited
/eb atm [name] [emeralds] [optional: iron]: Gives you the specified amount of emeralds/iron from the specified bank. If you don't want emeralds, put 0
/eb amount [name]: Shows the amount of emeralds and iron a bank has

Permissions:
emerald.help: Allows /eb help
emerald.create: Allows /eb create
emerald.deposit: Allows /eb deposit
emerald.atm: Allows /eb atm
emerald.amount: Allows /eb amount

Version:
This was made for Paper 1.16.1, but should work with bukkit and spigot too. If you want me to test a specific version of bukkit/spigot/paper, please write a comment

Making a Minecraft Plugin tutorial plugin for this plugin? Please, tell me that you made it so I can put it here!

bStats:
This project uses bStats for anonymous statistics. If you want to see the statistics go to: [bStats - EmeraldBankMM](https://bstats.org/plugin/bukkit/EmeraldBankMM/10519)
If you want to disable the statistics, go to your plugins folder, then bStats and then edit the file and change the only value to true
