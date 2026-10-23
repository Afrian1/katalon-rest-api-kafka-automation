
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// Send POST request
def response = WS.sendRequest(
    ObjectRepository.findTestObject('Object Repository/POST_CreatePost')
)

// Validate HTTP status
WS.verifyResponseStatusCode(response, 201)

// Parse response JSON
def json = new JsonSlurper().parseText(response.getResponseText())

// Validate response data
assert json.title == 'Katalon Automation'
assert json.body == 'REST API technical test'
assert json.userId == 1
assert json.id != null

println('PASS: REST API POST request and response validation')
println('Response: ' + response.getResponseText())
