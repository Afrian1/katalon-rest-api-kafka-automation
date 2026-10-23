
import groovy.json.JsonSlurper
import java.util.concurrent.TimeUnit

// Start Kafka Console Consumer
def process = new ProcessBuilder(
    "C:\\Users\\ramda\\kafka\\bin\\windows\\kafka-console-consumer.bat",
    "--bootstrap-server", "localhost:9092",
    "--topic", "qa-events",
    "--group", "katalon-qa-test-" + System.currentTimeMillis(),
	"--consumer-property", "auto.offset.reset=earliest"
).redirectErrorStream(true).start()

try {
    // Read one message from Kafka
    def reader = process.inputStream.newReader("UTF-8")
    String message = null
    long deadline = System.currentTimeMillis() + 20000

    while (System.currentTimeMillis() < deadline) {
        if (reader.ready()) {
            String line = reader.readLine()
            if (line != null && line.trim().startsWith("{")) {
                message = line.trim()
                break
            }
        } else {
            Thread.sleep(200)
        }
    }

    assert message != null : "No JSON message received from Kafka topic qa-events"

    // Parse JSON message
    def event = new JsonSlurper().parseText(message)

    // Validate event fields
    assert event.eventType == "OrderCreated" :
        "Unexpected eventType: ${event.eventType}"

    assert event.orderId == "1001" :
        "Unexpected orderId: ${event.orderId}"

    assert event.status == "CREATED" :
        "Unexpected status: ${event.status}"

    println("PASS: Kafka event validated successfully")
    println("Message: " + message)

} finally {
    process.destroyForcibly()
    process.waitFor(5, TimeUnit.SECONDS)
}
