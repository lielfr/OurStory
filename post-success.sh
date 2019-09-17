#!/usr/bin/env bash

if [[ ( "$TRAVIS_BRANCH" = "master"  ) ]]; then
    cp app/build/outputs/apk/debug/apk-debug.apk ./`echo $TRAVIS_COMMIT`-debug.apk
    pip install -r requirements.txt
    python uploader.py `echo $TRAVIS_COMMIT`-debug.apk
fi