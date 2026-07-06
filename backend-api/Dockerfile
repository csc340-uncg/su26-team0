# ===========================================================================================================================================================
# Stage 1: Build the application using JDK 25
# ===========================================================================================================================================================

FROM eclipse-temurin:25-jdk-alpine AS build
# starts a multi-stage Docker build
# - FROM: Defines the base image for your environment
# - eclipse-temurin:25-jdk: Contains the compiler and development tools to build your application (not just run it).
# - alpine: An ultra-lightweight Linux distribution used to keep the final Docker image size very small
# - AS build: Assigns the name build to this step. You can later reference this stage in a a different command.

WORKDIR /app
# - Sets the working directory inside the Docker container to /app.
# - If the /app directory does not exist yet, Docker automatically creates it.
# - Every following command in the Dockerfile (like COPY, RUN, CMD, or ENTRYPOINT) will execute from inside this folder.

COPY . .
# Copy everything from the current directory (host computer) into the Docker container.
# - First dot (.): Represents the source path on the host machine (usually the project's root folder where the Dockerfile lives).
# - Second dot (.): Represents the destination path inside the container. Because of the previous WORKDIR /app command, this resolves directly to /app

RUN chmod +x mvnw
# Change the file permissions of the Maven Wrapper script (mvnw) inside the container to make it executable.
# - RUN: Tells Docker to execute a command inside the container shell during the image build process.
# - chmod +x: The Linux command that grants execute (x) permissions to a file.
# - mvnw: The Maven Wrapper script

RUN ./mvnw clean package -DskipTests
# compiles and packages your Java application using the Maven Wrapper, while skipping the test suite to speed up the build.
# - RUN: Executes the command inside the container shell during the image build process.
# - ./mvnw: Runs the Maven Wrapper script located in the current /app folder.
# - clean: Deletes any previously compiled files and target directories to ensure a fresh, clean build.
# - package: Compiles your source code, runs checks, and bundles the compiled code into its final distributable format (a .jar inside the target/ directory)
# - -DskipTests: A Maven flag that tells it not to run unit tests. This saves significant time during container builds.

# =================================================================================================================================================================
# Stage 2: Create a lightweight runtime image using JRE 25
# =================================================================================================================================================================

FROM eclipse-temurin:25-jre-alpine
# Starts the second stage of the multi-stage Docker build, switching to a lightweight, runtime-only environment to run the packaged application.
# - FROM: Begins a brand new, clean build stage, discarding the heavy tools from the previous build stage.
# - eclipse-temurin:25-jre-alpine: Specifies the runtime environment.
# - jre: Contains the Java Runtime Environment. It includes the Java Virtual Machine (JVM) to run code, but excludes heavy developer tools like the compiler (javac)
# - alpine: Keeps the base operating system extremely small (usually under 5–10 MB)

WORKDIR /app
# Sets the working directory inside this new, second stage container to /app
# - All subsequent commands in this stage will execute from inside this folder.

COPY --from=build /app/target/*.jar FitMatch.jar
# Extracts only the final compiled Java file from your first build stage and drops it into your clean, lightweight runtime stage, renaming it for simplicity.
# - COPY: Copies files into the current image layer.
# - --from=build: Intercepts the normal behavior (which copies from the host machine) and tells Docker to look inside the filesystem of the previous stage named build.
# - /app/target/*.jar: The source path inside that build stage where Maven placed the compiled artifact.
# - FitMatch.jar: The destination path and new name inside the current production stage's /app folder

EXPOSE 8080
# Expose the port on which the application will run in the container.

ENTRYPOINT ["java", "-jar", "FitMatch.jar"]
# Defines the default command that runs automatically when the Docker container starts up, launching the Java application.
# - ENTRYPOINT: Sets the executable that will always run when the container is containerized. It makes your container behave like a standalone executable program.
# - ["java", "-jar", "FitMatch.jar"]: This uses the exec form (parsed as a JSON array) to run the exact command: java -jar FitMatch.jar.
# - java: Calls the Java runtime launcher.
# - -jar: Tells Java to execute a packaged JAR file.
# - FitMatch.jar: Points to the target file that was copied in the previous step.