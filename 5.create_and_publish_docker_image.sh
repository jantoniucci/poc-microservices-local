cd implementation/java/implementation
mvn clean package docker:build 
gcloud docker push eu.gcr.io/gke-demo-1111/impl
cd ../../..