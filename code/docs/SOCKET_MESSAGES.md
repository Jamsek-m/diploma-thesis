# Socket Messages

## Socket registration

### Request

```json
{
    "type": "REGISTRATION_REQUEST",
    "applicationName": "string"
}
```

### Response

```json
{
    "type": "REGISTRATION_RESPONSE",
    "sessionId": "string"
}
```

## Session

### Mouse track
```json
{
    "type": "SESSION",
    "sessionType": "MOUSE_TRACK",
    "coordinates": [
        {
            "pageX": "number",
            "pageY": "number"
        }
    ]
}
```