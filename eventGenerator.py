import json
import uuid
import random
import time
from datetime import datetime, timezone

event_types = ["playback_start", "playback_error", "buffering"]
error_codes = ["E1001", "E1023", "E2045", None]
tv_models = ["Bravia-XR", "Bravia-4K", "Bravia-8K"]

def generate_event():
    event_type = random.choice(event_types)
    
    event = {
        "event_id": str(uuid.uuid4()),
        "device_id": f"tv_{random.randint(1, 5)}",
        "event_type": event_type,
        "timestamp": datetime.now(timezone.utc).isoformat(),
        "app_version": "1.0.5",
        "firmware_version": "5.2.1",
        "region": "UK",
        "content_id": f"movie_{random.randint(1, 50)}",
        "error_code": random.choice(error_codes) if event_type == "playback_error" else None,
        "buffering_time_ms": random.randint(100, 3000) if event_type == "buffering" else 0,
        "model": random.choice(tv_models)
    }
    
    return event

# Generate sample events
# for _ in range(5):
#     print(json.dumps(generate_event(), indent=2))

# Continuous telemetry stream
for device_id in range(1, 10):
    event = generate_event()
    event["device_id"] = f"tv_{device_id}"  # Simulate multiple devices
    print(json.dumps(event))



# Sample event JSON structure:{
#   "eventId": "evt-1001",
#   "deviceId": "tv-001",
#   "eventType": "playback_error",
#   "timestamp": "2026-05-10T10:00:00Z",
#   "appVersion": "3.4.0",
#   "firmwareVersion": "5.2.1",
#   "region": "UK",
#   "contentId": "movie-789"
#   "errorCode": "E1023",
#   "bufferingTimeMs": 1200,
#   "model": "Bravia-XR",
# }