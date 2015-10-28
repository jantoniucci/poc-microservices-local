echo - Creando el cluster
gcloud container clusters create gke-demo-cluster1 --num-nodes 1 --machine-type g1-small

echo - Listando clusters
gcloud container clusters list

echo - Listando instancias
gcloud compute instances list

echo - Creando el pod
kubectl run impl --image=eu.gcr.io/gke-demo-1111/impl --port=8080

echo - Creando el replica controller
kubectl expose rc impl --create-external-load-balancer=true

echo Ejecutar: kubectl get services impl