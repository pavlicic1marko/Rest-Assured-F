--in src/test/java/resources add user.properties file
use sample.user.properties as a template

--application.properties
log=True, will log http request response in allure report

--to run specific Tag in CMD do it with command
mvn clean test -Dgroup="com.student.tags.Regression"

