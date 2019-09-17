#!/usr/bin/env bash

if [[ ( "$TRAVIS_BRANCH" = "master"  ) ]]; then
    cp app/build/outputs/apk/debug/app-debug.apk ./`echo $TRAVIS_COMMIT`-debug.apk
    pip3 install -r requirements.txt
    python3 uploader.py `echo $TRAVIS_COMMIT`-debug.apk
fi