#!/usr/bin/env bash

if [[ ( "$TRAVIS_BRANCH" = "master"  ) ]]; then
    chmod +x slack-upload.sh
    cp app/build/outputs/apk/debug/apk-debug.apk ./`echo $TRAVIS_COMMIT`-debug.apk
    ./slack-upload.sh -f ./`echo $TRAVIS_COMMIT`-debug.apk -c '#ourstory-apks' -s `echo $SLACK_API_KEY`
fi