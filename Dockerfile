# selenium_testcases/Dockerfile

# 1. Use a Maven image with Chrome & ChromeDriver baked in
FROM markhobson/maven-chrome:latest

# 2. Create app directory
WORKDIR /usr/src/app

# 3. Copy only pom.xml first — so the layer that downloads deps is cached until your POM changes
COPY pom.xml .

# 4. Pre-fetch all Maven dependencies (speeds up rebuilds)
RUN mvn dependency:go-offline -B

# 5. Copy the rest of your test code into the container
COPY src ./src

# 6. Run the full Selenium test suite by default
CMD ["mvn", "-B", "clean", "test"]
