#!/usr/bin/env bash

if [[ ( "$TRAVIS_BRANCH" = "master"  ) && ( "$TRAVIS_PULL_REQUEST" = "false" ) ]]; then
    cp app/build/outputs/apk/debug/app-debug.apk ./`echo $TRAVIS_COMMIT`-debug.apk
    curl -F file=@`echo $TRAVIS_COMMIT`-debug.apk -F channels="#ourstory-apks" -H "Authorization: Bearer `echo $SLACK_API_TOKEN`" https://slack.com/api/files.upload
fi