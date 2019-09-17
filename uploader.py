#!/usr/bin/env python
import os
import slack
import sys

client = slack.WebClient(token=os.environ['SLACK_API_TOKEN'])
response = client.files_upload(
    channels='#ourstory-apks',
    file=sys.argv[1])
