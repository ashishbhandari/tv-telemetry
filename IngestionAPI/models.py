from pydantic import BaseModel
from typing import Optional
from datetime import datetime

class TelemetryEvent(BaseModel):
    event_id: str
    device_id: str
    event_type: str
    timestamp: datetime
    app_version: str
    firmware_version: str
    region: str
    content_id: Optional[str]
    error_code: Optional[str]
    buffering_time_ms: Optional[int]