#!/bin/bash

if [[ $TRAVIS_PULL_REQUEST == "false" ]]; then
    mvn deploy --settings $GPG_DIR/settings.xml -Dtravis-ossrh=true -DskipTests=true
    exit $?
fi
