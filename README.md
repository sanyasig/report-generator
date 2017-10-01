# Report Generator
This is used to Generate Reports for trades provided based on certain Criteia

Most Recent Jar Has been added to the repository to try. It is located in the root of repo called "report-generator-all-1.0.jar"

## Smple Usage

 *********    Java -jar report-generator-all-1.0.jar Path-to-CSV   *********
 
 ## Sample Output
 
##### ****Incoming Amount**** <br />
  
| Date        | 	Amount(USD)   |
| ------------- |:-------------:|
|10 Jan 2016      | 30789.0  | 
|11 Jan 2016	    |994999.5  | 

 
##### ****OutGoing Amount**** <br />
  
| Date        | 	Amount(USD)   |
| ------------- |:-------------:|
|04 Jan 2016	    | 10025.0  |


##### ****Buy/Sell Entity Rank**** <br />
  
| Type        | 	Entities High-to-Low) |
| ------------- |:-------------:|
|Buy Orders	      | [foo]  |
|Sell Orders	    |[foobar, bar]  |

 ## Gradle Build
 #### Top build a jar with all Depenednies
 Run: gradle clean fatjar
 
 #### Run All Unit Tests (add --debug to see the detailed results)
 Run: gradle clean test
 
