## Java Assessment Overview

To complete this assessment, clone this git repository and follow the instructions provided below. Please spend no more
than one hour total on this assignment — you don't need to complete every step, but you should be prepared to discuss
any steps that you do not complete. After you're done, please push your code to a public git repository and share a
link to the repository with your UL contact.


### Resources

* You will need to build the project with gradle, and compile it with Java 8+
    * Gradle: Run either `./gradlew` or `.\gradlew.bat` in the project root directory to build, then the appropriate `gradlew run` will run the application
    * An IDE like Intellij or Eclipse may make running/debugging this project easier
* Please feel free to use any online references, including api documentation and StackOverflow, to help solve these problems
* You may also add any libraries/dependencies that you find useful, provided that they can be easily installed with the gradle build
* Please reach out to your UL contact if you have any questions or need help running the application


### Message Problem

This is a basic Producer/Consumer problem involving the transfer and logging of messages. Note that the MessageFactory class should be viewed as a black box and should not be modified.

Try to achieve the following objectives, however do not waste all your time on one objective:
1. Improve the format of the message logged to std out. The output should include the message priority, a human-readable timestamp, and the message text.
2. The consumer currently logs all messages immediately to std out. Implement a buffering mechanism that will buffer a batch of 10 messages, sort them according to Priority (High to Low), and then output the batch to std out.
3. Instead of printing to std out, output the messages in the same format, but to a text file.
4. The program should run to completion, such that the Java VM terminates correctly. Occasionally, this does not happen correctly. See if you can debug this situation.
5. Consider any improvements that you would make – you do not need to implement these improvements, these will form part of the discussion after the exercise.
