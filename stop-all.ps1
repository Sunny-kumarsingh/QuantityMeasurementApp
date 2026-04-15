$ports = @(8080, 8081, 8082, 8761)
Write-Host "Stopping servers on ports: $($ports -join ', ')..."

foreach ($port in $ports) {
    try {
        $connections = Get-NetTCPConnection -LocalPort $port -State Listen -ErrorAction SilentlyContinue
        if ($connections) {
            foreach ($conn in $connections) {
                # Stop the Java process holding the port
                Stop-Process -Id $conn.OwningProcess -Force -ErrorAction SilentlyContinue
                Write-Host "Successfully stopped process on port $port" -ForegroundColor Green
            }
        }
    } catch {
        Write-Host "No process found running on port $port" -ForegroundColor DarkGray
    }
}

Write-Host "All backend servers have been stopped!" -ForegroundColor Cyan
Start-Sleep -Seconds 3
