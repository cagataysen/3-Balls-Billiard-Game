# 3-Balls-Billiard-Game

Three-cushion billiards game to collision 3 billiard balls by cue stick on the billiard table. The game design according to proper object-oriented hierarchy by using Java. 

Proper Object-Oriented (OO) hierarchy used. There are 3 standard balls defined including one white cue ball (ball1, ball2, ball3 by using Ellipse2D), Billiard Table (Table by using Rectangle2D) and 2 Cue Stick for each player (Stick by using Recangle2D).

The positions of the balls are updated after each stroke, with the if-else statements, balls blocked out from the table, so balls are always in the border of table, also to send the ball to the correct direction added “Math.hypot.(dx,dy)”. 

The Velocity is related with instance of ball to mouse position. Velocity of the balls decrease after strokes from an initial velocity to zero (deceleration). Blocking the permission to hit the ball before it stops added “!isrunning” element in the mouseClicked function. 

Mouse Listener used to control cue stick. When the user click to the table, power changes in direct proportion to the distance of the click location. If the striker ball hits the other two balls, score increases and if the score reaches to 50, game ends.

The program shows the speed and current location of the balls, and shows the score at the same time for each player. Program used Graphics (Java 2D API usage while designing the graphical elements).

Different cue sticks have different attributes. For instance, each player have different colour stick.  Good game experience. The program has 2 player game in one computer mode. As a cheats codes if the user clicks the right button of mouse, the score increase 50 points.  


