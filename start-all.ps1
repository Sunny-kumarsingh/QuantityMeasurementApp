Write-Host "Loading environment variables from .env..."
Get-Content .env -ErrorAction SilentlyContinue | ForEach-Object {
    if ($_ -match '^\s*([^#\s]+)\s*=\s*(.*)\s*$') {
        [Environment]::SetEnvironmentVariable($matches[1], $matches[2], 'Process')
    }
}

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
