#!/bin/bash

if [[ $TRAVIS_PULL_REQUEST == "false" ]]; then
    mvn deploy --settings $GPG_DIR/settings.xml -Ptravis-ossrh -DskipTests=true
    exit $?
fi
