# COMP251 Final Project
The goal of this program is, given voter information, calculate the minimum number of votes required for the challenging candidate (Biden) to win the presidency over the incumbent (Trump), if it is possible.

We are provided with the following information for each state:
* Number of delegates for the state
* Number of decided Trump voters in the state
* Number of decided Biden voters in the state
* Number of undicided voters in the state

The program returns `-1` if the incumbent wins, otherwise it returns the minimum number of votes required for the challenger to win.

## Use
The program reads in a text file with the following structure:

> [x number of states]
>
> [state 1 delegates] [state 1 biden voters] [state 1 trump voters] [state 1 undicided voters]
>
> [state 2 delegates] [state 2 biden voters] [state 2 trump voters] [state 2 undicided voters]
>
> .
>
> .
>
> [state x delegates] [state x biden voters] [state x trump voters] [state x undicided voters]
