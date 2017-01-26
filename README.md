# ServerTrials

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

[ServerTrials Bukkit Plugin Page] (https://dev.bukkit.org/projects/servertrials)

ServerTrials was made for [this plugin request] (http://forums.bukkit.org/threads/trial-for-servers.286049/). ServerTrials' main goal is to give players a trial for an amount of time on the server. It is meant for the "pay to play" sense. When a player donates to the server, if they are banned by the plugin, then once they donate the donating system will run the commands "/trial unban <player>" and "/trial exclude <player>". Those commands will unban the player and make them excluded from the trial. Once their donating money has expired, then the donating system will run "/trial unexclude <player>" and they will be on another trial until they donate again. The donating system is not included.

<h2>Donating System Suggestions</h2>
DonationCraft

Buycraft

<h2>Permissions</h2>
ServerTrials.bypass - The players with this permission bypass the trial counter.

ServerTrials.Admin - The players with this permission will be able to run the command /trial.

<h2>Commands</h2>
./trial - Open help menu.

./trial exclude (player) - Exclude this player from the trial.

./trial unban (player) - Unban the player who was banned because of their trial ending.

./trial unexclude (player) - Unexclude this player from the trial.

<h2>Config</h2>
```YAML
Trial-Type: 'MINUTE'     # Types: DAY, HOUR, MINUTE
TimeAllowed: 1 # Depending on the Trial Type this is the amount of that you want them to be allowed on the server.
Messages:
  BannedMessage: '&4Your trial has finished! &2Pay @ &e[Link] &2to join again!'
  KickMessage: '&4Your trial has ended! &2Pay @ &e[Link] &2to join again!'
Excluded-Players: []
Banned-Players: []
```
