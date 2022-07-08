Feature:Validating place API's

@AddPlace
Scenario Outline:Verify if the place is being added successfully using AddPlaceAPI

Given Add place payload with "<name>" "<address>" "<language>"
When  user calls "addPlaceAPI" with "POST" http request
Then  the API got success with the success code 200
And   "status" in response body is "OK"
And   Verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
| name       | address           | language |
|Bridge House| restmor way,London| Marathi  |
#|Tridev House| Manchester way,sutton| French  |
#|John House| Lake District way,Manchester| Spanish  |

@DeletePlace
Scenario:Verify if the DeletePlaceAPI is working

Given Delete place payload
When  user calls "deletePlaceAPI" with "POST" http request
Then  the API got success with the success code 200
And   "status" in response body is "OK"
  