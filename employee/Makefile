build:
	mvn package -Pdebug -DskipTests=true && mvn package -Prelease -DskipTests=true
	docker image build -t employee-api -f Dockerfile ${PWD}

clean:
	mvn clean

start:
	docker container run -dit -p 8082:8080 --rm --name employee-api employee-api
	@echo "Check health: http://localhost:8080/actuator/health"

stop:
	docker container stop employee-api