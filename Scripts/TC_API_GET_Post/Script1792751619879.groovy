
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

// Send GET request
def response = WS.sendRequest(findTestObject('Object Repository/GET_Post'))

// Validate status code
WS.verifyResponseStatusCode(response, 200)

// Parse JSON response
def json = new groovy.json.JsonSlurper().parseText(response.getResponseText())

// Validate response fields
assert json.id == 1
assert json.userId == 1
assert json.title != null

println("PASS: GET Post API validation")
println("Response: " + response.getResponseText())
