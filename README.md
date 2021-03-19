# TFLTrainTracker

<p align="center">
  <img src="https://www.nicepng.com/png/full/854-8546612_java-ee-java-ee-logo-svg.png" width="150">   ,            
  <img src="https://upload.wikimedia.org/wikipedia/en/e/ee/MySQL_Logo.png" width="130">
</p>

A three tier application that queries, processes and displays information for TFL trains.

Entity Relationship Diagram:

<img src="https://github.com/MalikS789/TFLTrainTracker/blob/master/Untitled.bmp">

<h1>How to use it</h1>

navagating to ./trains will produce a JSON file that contrains all the trains which their unique ID and the trainline they belong to. due to poor documentation of the TFL API,
aswell as the inconsistency of the API data, the train ID syntax is as follows:
* X0YYY
  * X represents the line the train is on.
    * X = 0 for bakerloo
    * X = 1 for central
    * X = 2 for district
    * X = 3 for hammersmith-city
    * X = 4 for jubilee
    * X = 5 for metropolitan
    * X = 7 for northern
    * X = 8 for piccadilly
    * X = 9 for tram
    * x = 10 for victoria
  * YYY is the vehicleid as according to the "vehicleid" feild of the TFL API.
 
 navigating to ./paths will produce a json for all trains' recorded activity. As a result this is a large JSON file. for format for this JSON file is that each entry
 represents a activity (trainID associated with it, platform name the train was on, the trains reported location according to the API, the direction is it heading and at 
 what time this information was captured.
 
 nagivating to ./<i>trainlines</i>/<i>trainID<i> will print a page that should all the activity of the train with that ID.
 * NOTE the trainID used here is NOT the same as the X0YYY format, rather it is a simple YYY format. 
 
<img src="https://github.com/MalikS789/TFLTrainTracker/blob/master/Untitled3.bmp">

 Alternatively, you can simply use the index page to choose the train from the dropdown list, which does nothing more then 
 automatically enter the URL for you.
 
 <img src="https://github.com/MalikS789/TFLTrainTracker/blob/master/Untitled2.bmp">
 
 The <i>Update database</i> button simply queries the TFL API for all trains on all lines. The reason for this instead of having
 this happen automatically on page load was due to the time is takes (approx. 1 minute!).
