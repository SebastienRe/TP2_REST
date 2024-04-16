$question = "Q12"
echo "Question $question"
javac src/$question/*.java -d WEB-INF/classes
jar cvf WAR/"TP2"$question".war" WEB-INF/classes/$question WEB-INF/web.xml nouveau.html index.html WEB-INF/carnet.xml
asadmin deploy --force WAR/"TP2"$question".war"