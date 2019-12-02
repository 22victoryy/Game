
######################

1. For the authentication system for this phase 2 project, we have used Firebase to interact
   to creating, authenticating, and store the user accounts. A json file is required for
   google services, which is already included in this repo (google-services.json in app
   level directory). Therefore, the programme should be runnable after cloning.

2.
 - Due to a mistake on git, one commit author does not match the UTORid of one of our members.
   All the commits made by vchanyoung <dk90801@gmail.com> are by ChanYoung Cho,
   with UTORid chochan2 <victorchanyoung.cho@mail.utoronto.ca>.
 - We have drawn the UML diagrams for each parts we were responsible for, which will also be
   presented to the TAs.

3. How to play the games

  -Level 1 is self-explanatory. However, once the player starts the game, there is an easter
  egg in the corner. Clicking the easter egg leads to a bonus round. In this bonus round, the player
  clicks a button to generate a random number. Whatever number they get is how many bonus points
  they earn. After the bonus round, they can proceed to level 2. Thus, the easter egg presents a way
  to bypass level 1.

  - Level 2 should be playable by selecting a difficulty level and adjusting the speed of the
  obstacles generation, and dragging around the square block to avoid it. Playing the hard mode
  will grant bonus points, whereas playing the normal mode will not. Another way of earning the
  bonus points is finding the bonus game, which is accessed by clicking on the easter egg at the
  start of the game.

  - extragamelevel is a bonus game. Players press the button start to start the game. Player should
  press the correct button as required. In order to win the bonus game, the player have to press
  the correct button for 5 times as well as not pressing the wrong buttons for more than 3 times.


  - Level 3 should be mostly self-explanatory, except for knowing how bonus points are earned
  and how to access the bonus game. The bonus points are calculated using the formula
    bonus points = 10*(Number of tree obstacles passed)/(Number of glides or taps to the screen)
  where the number of taps is not zero and the number of tree obstacles passed is greater than five.
  Basically if the player can maximize the number of trees passed while minimizing the number of
  taps, they maximize the number of bonus points earned. The other way of earning bonus points is by
  accessing and playing the bonus game, which is accessed by clicking on the coloured oval
  Easter egg on the bottom left corner in the start screen.


