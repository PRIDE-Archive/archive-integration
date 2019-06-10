#!/bin/sh

##### RUN it on the production queue
nohup /nfs/pride/work/java/jdk1.8.0_144/bin/java -cp ${project.build.finalName}.jar uk.ac.ebi.pride.integration.IncomingSubmissionIntegrationApp &