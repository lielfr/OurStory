#!/usr/bin/env bash

rm app/google-services.json || true
curl -F token="`echo $SLACK_OAUTH`" -F channel=CNKGGFX8F https://slack.com/api/files.list | python2 -c "import sys, json; print json.load(sys.stdin)['files'][0]['url_private_download']" | xargs -n1 curl -H "Authorization: Bearer `echo $SLACK_OAUTH`" -o app/google-services.json
