Write-Host "Starting Discovery Server..."
Start-Process powershell -ArgumentList "-NoExit -Command `".\mvnw.cmd spring-boot:run -pl discovery-server`""
Start-Sleep -Seconds 15

Write-Host "Starting User and Measurement Services..."
Start-Process powershell -ArgumentList "-NoExit -Command `".\mvnw.cmd spring-boot:run -pl user-service`""
Start-Process powershell -ArgumentList "-NoExit -Command `".\mvnw.cmd spring-boot:run -pl measurement-service`""
Start-Sleep -Seconds 15

Write-Host "Starting API Gateway..."
Start-Process powershell -ArgumentList "-NoExit -Command `".\mvnw.cmd spring-boot:run -pl api-gateway`""

Write-Host "All 4 microservices have been launched in separate windows!"
