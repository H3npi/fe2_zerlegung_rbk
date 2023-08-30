# FE2 disassembly for alarms within the RBK

## Available parameters
### Main-Alarm
key | Parameter number from alarmtext
----------|---------------
`alarm_number` | 1
`externalId` | 1
`v2_id` | 1
`alarm_tier` | 3
`rtw` | 5
`nef` | 5
`sonderrechte` | 6
`district` | 8
`address_number` | 10
`floor` | 12
`abschnitt` | 13
`bma_number` | 16
`einsatzplan` | 17
`building_sub` | 15
default fe2 fields |
`keyword` | 2
`city` | 7
`street` | 9 + 10 + 11
`location_additional` | 11
`building` | 14
`message` | 18

### Pre-Alarm
In case of a pre-alarm following parametes will be set:
- `message`
- `prealarm`
- `keyword`

`message` will be filled with alarm text, `prealarm` will be filled with `true` and `keyword` will be filled with `Voralarm`

## KWF Note
This project is using KWF rescue points.

file: resources/KWF_RP_V2_10_DE.gpx

© KWF-Rettungspunkte , Datenurheber, www.rettungspunkte-forst.de

## Other notes

Build with knowledge from https://alamos-support.atlassian.net/wiki/spaces/documentation/pages/219480670/Eigene+Zerlegung+entwickeln
Usage with (in example): https://alamos-support.atlassian.net/wiki/spaces/documentation/pages/219480409/Alarmtext+eigene+Parameter
