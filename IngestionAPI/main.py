from fastapi import FastAPI

try:
    from .models import TelemetryEvent
except ImportError:
    from models import TelemetryEvent

app = FastAPI()

# Temporary in-memory store (replace later with DB)
events_store = []

@app.get("/")
def root():
    return {"message": "TV Telemetry API is running"}

@app.post("/telemetry")
def ingest_event(event: TelemetryEvent):
    events_store.append(event.dict())
    
    return {
        "status": "success",
        "event_id": event.event_id
    }