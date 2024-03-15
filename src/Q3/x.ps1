$question = "Q3"
echo "Question $question"
javac src/$question/*.java -d WEB-INF/classes
jar cvf WAR/$question".war" WEB-INF/classes/$question WEB-INF/web.xml index.html
asadmin deploy --force WAR/$question".war"